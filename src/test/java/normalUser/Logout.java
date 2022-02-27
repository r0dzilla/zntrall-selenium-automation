package normalUser;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import NormalUserXpath.LogoutXpath;
import browser.OpenBrowser;
import normalUserInputData.LogoutInfoData;

public class Logout extends OpenBrowser{

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 10 -- Logout";
	public static WebDriver driver = null;

	@BeforeSuite
	public static void beforeSuit() {

		if (env.equalsIgnoreCase("Test")) {

			System.out.println("Test executes in correct environment where environment= " + env);
			System.out.println("Test Suite name = " + testSuiteName);

		}else {
			System.out.println("Test executes in wrong environment where environment= " + env);

		}
	}

	public static String myBrowser = "MicrosoftEdge";
	@BeforeTest
	public void setup() throws MalformedURLException {
		
		driver = start(myBrowser);

	}


	//Opening browser with the given URL and navigate to Registration Page

	@BeforeMethod
	public void openBrowser() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.get("https://dev.zntral.net/session/login");
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}


	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}

	//login

	@Test(priority = 1)
	public void loginUser() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement username = driver.findElement(By.xpath(LogoutXpath.username));
		WebElement password = driver.findElement(By.xpath(LogoutXpath.password));
		WebElement login = driver.findElement(By.xpath(LogoutXpath.login));

		String user = LogoutInfoData.user;
		String pass = LogoutInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//logout

	@Test(priority = 2)
	public void logout() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement username = driver.findElement(By.xpath(LogoutXpath.username));
		WebElement password = driver.findElement(By.xpath(LogoutXpath.password));
		WebElement login = driver.findElement(By.xpath(LogoutXpath.login));

		String user = LogoutInfoData.user;
		String pass = LogoutInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);

		WebElement threeDotMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LogoutXpath.threeDotMenu)));
		threeDotMenu.click();
		Thread.sleep(2000);

		WebElement clickLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LogoutXpath.clickLogout)));
		clickLogout.click();
		Thread.sleep(3000);

		String expectedUrl = "https://dev.zntral.net/session/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}
}
