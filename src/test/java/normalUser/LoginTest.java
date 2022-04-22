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

import NormalUserXpath.LoginTestXpath;
import browser.OpenBrowser;
import normalUserInputData.LoginTestInfoData;

public class LoginTest extends OpenBrowser {

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 2 -- Login";
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

	public static String myBrowser = "chrome";
	@BeforeTest
	public void setup() throws MalformedURLException {
		
		driver = start(myBrowser);

	}

	@AfterTest
	public void tearDown() throws Exception {

		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
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

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.signInTitle)));
		WebElement emailTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.emailTitle)));
		WebElement passTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.passTitle)));
		WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.loginButton)));
		signInTitle.isDisplayed();
		emailTitle.isDisplayed();
		passTitle.isDisplayed();
		loginButton.isDisplayed();

	}

	//login with valid username & Password

	@Test(priority = 2)
	public void verifyLogin() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.password)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.login)));

		String user = LoginTestInfoData.user;
		String pass = LoginTestInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);

		String expectedUrl = "https://dev.zntral.net/dashboarda";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//login without username

	@Test(priority = 3)
	public void loginWithoutUsername() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.password)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.login)));

		username.sendKeys(LoginTestInfoData.invaliduser);
		password.sendKeys(LoginTestInfoData.pass);

		try {
			login.click();
			WebElement loginAs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.loginAs)));
			loginAs.click();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			if(username.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath(LoginTestXpath.userField));
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

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.password)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.login)));

		WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.checkbox)));

		username.sendKeys(LoginTestInfoData.user);
		password.sendKeys(LoginTestInfoData.invalidpass);

		checkbox.click();

		try {
			login.click();
			WebElement loginAs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.loginAs)));
			loginAs.click();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			if(password.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath(LoginTestXpath.userField));
				login.isDisplayed();

				String expectedUrl = "https://dev.zntral.net/session/login";
				String actualUrl = driver.getCurrentUrl();
				Assert.assertEquals(actualUrl, expectedUrl);
				Assert.assertTrue(true);

			}
		}

	}

	//login with empty field
	@Test(priority = 5)
	public void loginWithEmptyField() {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.password)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.login)));

		username.sendKeys(LoginTestInfoData.invaliduser);
		password.sendKeys(LoginTestInfoData.invalidpass);

		login.isEnabled();

		String expectedUrl = "https://dev.zntral.net/session/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//Forgot password

	@Test(priority = 6)
	public void forgotPassword() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement forgotPass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.forgotPass)));
		forgotPass.click();
		WebElement emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.emailId)));
		emailId.sendKeys("abc@email.com");
		WebElement sendEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.sendEmail)));
		sendEmail.click();
		Thread.sleep(3000);
		try {
			driver.switchTo().alert().getText();
		}
		catch(Exception e) {
			Assert.assertTrue(true);
		}
	}


	//random order for userid and password

	@Test(priority = 7)
	public void randomInput() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.password)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.login)));

		password.sendKeys(LoginTestInfoData.pass);
		username.sendKeys(LoginTestInfoData.user);

		login.click();
		Thread.sleep(5000);

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@AfterSuite
	public static void afterSuit() {
		System.out.println( testSuiteName + " execution Complete");
	}

}
