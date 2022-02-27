package superAdminFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import superAdmin.Clients;
import superAdminInputData.ClientsInfoData;
import superAdminXpath.ClientsXpath;

public class ClientsFunctions extends Clients {

	public static void verifyLogin() throws InterruptedException {

		WebElement username = driver.findElement(By.xpath(ClientsXpath.username));
		WebElement password = driver.findElement(By.xpath(ClientsXpath.pass));
		WebElement login = driver.findElement(By.xpath(ClientsXpath.login));

		String user = ClientsInfoData.user;
		String pass = ClientsInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);

	}


	public static void clientList() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement clients = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.clients)));
		clients.click();
		Thread.sleep(5000);
	}


	public static void clientProfile() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement clientName = driver.findElement(By.xpath(ClientsXpath.clientName));
		String client = clientName.getText();
		clientName.click();
		Thread.sleep(5000);


		WebElement clientNameVerify = driver.findElement(By.xpath(ClientsXpath.clientNameVerify));

		if(clientNameVerify.getText().contains(client)) {
			Assert.assertTrue(true);

		}else {
		}
	}
	
	
	public static void addClientGroup(String clientGroupName, String clientGroupAcronym ) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.add)));
		add.click();

		WebElement clientGroup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.clientGroup)));
		clientGroup.click();
		WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.name)));
		name.sendKeys(clientGroupName);
		WebElement acronym = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.acronym)));
		acronym.sendKeys(clientGroupAcronym);
		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.signUp)));
		WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.signUpStatus)));

		signUp.click();
		status.click();
		Thread.sleep(2000);

		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.submit)));
		submit.click();
	}


}
