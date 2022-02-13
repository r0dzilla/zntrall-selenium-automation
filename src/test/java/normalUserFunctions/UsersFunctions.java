package normalUserFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import NormalUserXpath.UsersXpath;
import normalUser.Users;
import normalUserInputData.UsersInfoData;

public class UsersFunctions extends Users{

	static WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

	public static void verifyLogin() throws InterruptedException {

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.password)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.login)));

		username.sendKeys(UsersInfoData.user);
		password.sendKeys(UsersInfoData.pass);

		login.click();
		Thread.sleep(5000);
	}

	public static void usersList() throws InterruptedException {
		WebElement userlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.userList)));
		userlist.click();
		Thread.sleep(3000);
	}

	public static void addUsers(String firstName, String lastName, String EmailId) {

		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.FirstName)));
		FirstName.sendKeys(firstName);		
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.LastName)));
		LastName.sendKeys(lastName);
		WebElement emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.emailId)));
		emailId.sendKeys(EmailId);
	}
}
