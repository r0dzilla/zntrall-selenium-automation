package superAdmin;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import browser.OpenBrowser;
import superAdminInputData.LoginInfoData;
import superAdminXpath.LoginXpath;

public class Login extends OpenBrowser  {

	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 1 -- Login";

	public static WebDriver driver = null;
	@Parameters({"Browser"})
	
	

	@BeforeTest
	public void setup(String Browser) throws MalformedURLException {

		if((Browser.equalsIgnoreCase("chrome"))) {
			driver = start(Browser);
		}

		if((Browser.equalsIgnoreCase("firefox"))) {
			driver = start(Browser);
		}
		
	}

	@BeforeSuite
	public static void beforeSuit() {

		if (env.equalsIgnoreCase("Test for Super Admin")) {

			System.out.println("Test executes in correct environment where environment= " + env);
			System.out.println("Test Suite name = " + testSuiteName);

		}else {
			System.out.println("Test executes in wrong environment where environment= " + env);

		}
	}

	//Opening browser with the given URL and navigate to Registration Page

	@BeforeMethod
	public void openBrowser() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.get("https://dev.zntral.net/session/login");
		driver.manage().window().maximize();
		Thread.sleep(3000);
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
	public void verifyLogin() throws InterruptedException {

		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.pass)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.login)));

		String user = LoginInfoData.user;
		String pass = LoginInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);

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
				System.out.println(userField.getText());
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
				System.out.println(userField.getText());
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
		System.out.println(error.getText());

		String expectedUrl = "https://dev.zntral.net/session/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		Assert.assertTrue(true);

	}

	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}

	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}
}
