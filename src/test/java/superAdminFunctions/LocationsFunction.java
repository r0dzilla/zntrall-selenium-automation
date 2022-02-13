package superAdminFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import superAdmin.Locations;
import superAdminInputData.LocationInfoData;
import superAdminXpath.LocationXpath;

public class LocationsFunction extends Locations {

	public static void verifyLogin() throws InterruptedException {

		WebElement username = driver.findElement(By.xpath(LocationXpath.username));
		WebElement password = driver.findElement(By.xpath(LocationXpath.pass));
		WebElement login = driver.findElement(By.xpath(LocationXpath.login));

		String user = LocationInfoData.user;
		String pass = LocationInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);
	}


	public static void locationList() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement locations = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationXpath.locations)));
		locations.click();
		Thread.sleep(3000);
	}

	public static void searchLocation(String searchLocation) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement search = driver.findElement(By.xpath(LocationXpath.search));
		search.sendKeys(searchLocation);

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Search location", ExtentColor.ORANGE));

		WebElement firstRow = driver.findElement(By.xpath(LocationXpath.firstRow));

		firstRow.click();
		Thread.sleep(3000);
	}


	public static void type() throws InterruptedException {

		WebElement types = driver.findElement(By.xpath(LocationXpath.types));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", types);
		Assert.assertTrue(true);
		Thread.sleep(3000);
	}
}
