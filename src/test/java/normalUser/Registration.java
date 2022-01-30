package normalUser;
import static org.testng.Assert.assertFalse;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import NormalUserXpath.RegistrationXpath;
import browser.OpenBrowser;
import normalUserInputData.RegistrationInfoData;


public class Registration extends OpenBrowser {

	//Opening browser with the given URL and navigate to Registration Page

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 1 -- Registration";


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

		if (env.equalsIgnoreCase("Test")) {

			System.out.println("Test executes in correct environment where environment= " + env);
			System.out.println("Test Suite name = " + testSuiteName);

		}else {
			System.out.println("Test executes in wrong environment where environment= " + env);

		}
	}

	@BeforeMethod
	public void openBrowser() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.get("https://dev.zntral.net/session/login");
		driver.manage().window().maximize();
		WebElement signUpButton = driver.findElement(By.xpath(RegistrationXpath.signUpButton));
		signUpButton.click(); 
		Thread.sleep(3000);
	}

	//Verifying elements on Registration page

	@Test(priority = 1)
	public void verifyElemntsOnPageTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signUpTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.signUpTitle)));
		signUpTitle.isDisplayed();

	}

	// Registration with all valid data

	@Test(priority = 2)
	public void validRegistrationTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.firstName)));
		firstName.sendKeys(RegistrationInfoData.firstName);

		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.LastName)));
		LastName.sendKeys(RegistrationInfoData.lastName);

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.email)));
		email.sendKeys(RegistrationInfoData.email);

		WebElement verifiedemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.verifiedemail)));
		verifiedemail.sendKeys(RegistrationInfoData.verifiedemail);

		WebElement checkmark = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.checkmark)));
		checkmark.click();
		Thread.sleep(20000);

		WebElement termsOfServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.termsOfServices)));
		termsOfServices.click();
		Thread.sleep(1000);

		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.signUp)));
		signUp.click();
		Thread.sleep(10000);

		String expectedUrl = "https://dev.zntral.net/session/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);


	}

	// Registration - First Name field validation

	@Test(priority = 3)
	public void firstNameValidationTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.firstName)));
		firstName.sendKeys(RegistrationInfoData.invalidfirstName);

		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.LastName)));
		LastName.sendKeys(RegistrationInfoData.lastName);

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.email)));
		email.sendKeys(RegistrationInfoData.email);

		WebElement verifiedemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.verifiedemail)));
		verifiedemail.sendKeys(RegistrationInfoData.verifiedemail);

		WebElement checkmark = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.checkmark2)));
		checkmark.click();
		Thread.sleep(20000);

		WebElement termsOfServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.termsOfServices)));
		termsOfServices.click();
		Thread.sleep(1000);

		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.signUp2)));

		try {
			signUp.click();
			Thread.sleep(5000);
			String expectedUrl = "https://dev.zntral.net/session/login";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl);
		}
		catch(Exception e) {

			if(firstName.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.userField));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);

			}
			else if(firstName.getAttribute("value").length() > 20) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else {

				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
				String expectedURL = "https://dev.zntral.net/session/sign-up";
				String actualURL = driver.getCurrentUrl();
				Assert.assertEquals(actualURL, expectedURL);
			}
		}

	}

	// Registration-- Last Name field validation

	@Test(priority = 4)
	public void lastNameValidationTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.firstName)));
		firstName.sendKeys(RegistrationInfoData.firstName);

		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.LastName)));
		LastName.sendKeys(RegistrationInfoData.invalidlastName);

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.email)));
		email.sendKeys(RegistrationInfoData.email);

		WebElement verifiedemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.verifiedemail)));
		verifiedemail.sendKeys(RegistrationInfoData.verifiedemail);

		WebElement checkmark = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.checkmark2)));
		checkmark.click();
		Thread.sleep(20000);

		WebElement termsOfServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.termsOfServices)));
		termsOfServices.click();
		Thread.sleep(1000);

		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.signUp2)));
		Thread.sleep(2000);

		try {
			signUp.click();
			Thread.sleep(5000);
			String expectedUrl = "https://dev.zntral.net/session/login";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl);
		}
		catch(Exception e) {

			if(LastName.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.userField));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);

			}
			else if(LastName.getAttribute("value").length() > 20) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else {

				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
				String expectedURL = "https://dev.zntral.net/session/sign-up";
				String actualURL = driver.getCurrentUrl();
				Assert.assertEquals(actualURL, expectedURL);
			}
		}


	}

	// Registration -- user email field validation

	@Test(priority = 5)
	public void emailValidationTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.firstName)));
		firstName.sendKeys(RegistrationInfoData.firstName);

		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.LastName)));
		LastName.sendKeys(RegistrationInfoData.lastName);

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.email)));
		email.sendKeys(RegistrationInfoData.invalidemail);

		WebElement verifiedemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.verifiedemail)));
		verifiedemail.sendKeys(RegistrationInfoData.verifiedemail);

		WebElement checkmark = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.checkmark2)));
		checkmark.click();
		Thread.sleep(20000);

		WebElement termsOfServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.termsOfServices)));
		termsOfServices.click();
		Thread.sleep(1000);

		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.signUp2)));
		Thread.sleep(2000);

		try {
			signUp.click();
			Thread.sleep(5000);
			String expectedUrl = "https://dev.zntral.net/session/login";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl);
		}
		catch(Exception e) {

			if(email.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.userField));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);

			}
			else if(email.getAttribute("value").startsWith("@")) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else if(email.getAttribute("value") != verifiedemail.getAttribute("value")) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else {

				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
				String expectedURL = "https://dev.zntral.net/session/sign-up";
				String actualURL = driver.getCurrentUrl();
				Assert.assertEquals(actualURL, expectedURL);
			}
		}
	}


	//Registration as Default user

	@Test(priority = 6)
	public void defaultUser() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.firstName)));
		firstName.sendKeys(RegistrationInfoData.firstName);

		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.LastName)));
		LastName.sendKeys(RegistrationInfoData.lastName);

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.email)));
		email.sendKeys(RegistrationInfoData.email);

		WebElement verifiedemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.verifiedemail)));
		verifiedemail.sendKeys(RegistrationInfoData.verifiedemail);

		WebElement termsOfServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.termsOfServices)));
		termsOfServices.click();
		Thread.sleep(15000);

		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.signUp2)));

		signUp.click();
		Thread.sleep(2000);

		WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
		System.out.println(userField.getText());
		signUp.isDisplayed();
		Assert.assertTrue(true);
		Thread.sleep(2000);
	}


	//Registration as random input serial

	@Test(priority = 7)
	public void randomInputSerial() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.LastName)));
		LastName.sendKeys(RegistrationInfoData.lastName);

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.firstName)));
		firstName.sendKeys(RegistrationInfoData.firstName);

		WebElement verifiedemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.verifiedemail)));
		verifiedemail.sendKeys(RegistrationInfoData.verifiedemail);

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.email)));
		email.sendKeys(RegistrationInfoData.email);

		WebElement checkmark = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.checkmark2)));
		checkmark.click();
		Thread.sleep(20000);

		WebElement termsOfServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.termsOfServices)));
		termsOfServices.click();
		Thread.sleep(1000);

		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.signUp3)));
		signUp.isDisplayed();

		Assert.assertTrue(false);

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
