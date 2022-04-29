package superAdmin;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browser.OpenBrowser;
import superAdminFunctions.LoginFunctions;
import superAdminInputData.LoginInfoData;
import superAdminXpath.LoginXpath;

public class Login extends OpenBrowser{
	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 1 -- Login";
	public static WebDriver driver = null;

	@BeforeSuite
	public static void beforeSuit() {

		if (env.equalsIgnoreCase("Test for Super Admin")) {

			System.out.println("Test executes in correct environment where environment= " + env);
			System.out.println("Test Suite name = " + testSuiteName);

		}else {
			System.out.println("Test executes in Standard User= " + env);

		}
	}
	

	//public static String myBrowser = "iPhone 6";
	@Parameters("myBrowser")
	@BeforeTest
	public void setup(String myBrowser) throws MalformedURLException {

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
	

	//Verifying elements on Login page
	@Test(priority = 1)
	public void verifyElemntsOnPageTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.signInTitle)));
		signInTitle.isDisplayed();

	}
	

	//login with valid username & Password

	@Test(priority = 2)
	public static void verifyLogin() throws InterruptedException {


		LoginFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	

	//login without username

	@Test(priority = 3)
	public void loginWithoutUsername() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.pass)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.login)));

		String user = LoginInfoData.invalidUser;
		String pass = LoginInfoData.validPass;

		username.sendKeys(user);
		password.sendKeys(pass);

		try {
			login.click();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			if(username.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath(LoginXpath.userFieldMsg));
				login.isDisplayed();
				String expectedUrl = "https://dev.zntral.net/session/login";
				String actualUrl = driver.getCurrentUrl();
				Assert.assertEquals(actualUrl, expectedUrl);
				Assert.assertTrue(true);

			}
		}

	}
	

	//login without Password

	@Test(priority = 4)
	public void loginWithoutPassword() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.pass)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.login)));

		WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.checkbox)));

		String user = LoginInfoData.validUser;
		String pass = LoginInfoData.invalidPass;

		username.sendKeys(user);
		password.sendKeys(pass);

		checkbox.click();

		try {
			login.click();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			if(password.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath(LoginXpath.userFieldMsg));
				login.isDisplayed();
				String expectedUrl = "https://dev.zntral.net/session/login";
				String actualUrl = driver.getCurrentUrl();
				Assert.assertEquals(actualUrl, expectedUrl);
				Assert.assertTrue(true);

			}
		}

	}


	//login with invalid info

	@Test(priority = 5)
	public void invalidLoginInfo() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.pass)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.login)));

		String user = LoginInfoData.invalidUserId;
		String pass = LoginInfoData.invalidPassId;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.error)));

		String expectedUrl = "https://dev.zntral.net/session/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		Assert.assertTrue(true);

	}

}
