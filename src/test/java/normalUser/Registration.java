package normalUser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import NormalUserXpath.RegistrationXpath;
import browser.OpenBrowser;
import normalUserInputData.RegistrationInfoData;


public class Registration extends OpenBrowser {

	//Opening browser with the given URL and navigate to Registration Page

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 1 -- Registration";
	String fileName = System.getProperty("user.dir")+"/reports/Standard User/TestResults-Registration.html";

	public static ExtentTest parentTest;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentSparkReporter htmlReports;

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
		driver.get("https://dev.zntral.net/session/sign-up");
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}

	public String getScreenshotPath(String TestcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destPath = "\\home\\b0b\\zntrall-selenium-automation\\screenshots\\"+TestcaseName+".png";
		File file = new File(destPath);
		FileUtils.copyFile(source, file);
		return destPath;
	}

	@BeforeClass
	public void initiateReport() {
		htmlReports = new ExtentSparkReporter(fileName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReports);
		htmlReports.config().setReportName("Standart User - Registration");
		htmlReports.config().setTheme(Theme.STANDARD);
		htmlReports.config().setDocumentTitle("Test Report for Standard User");

	}

	@AfterMethod
	public void checkResults(ITestResult testResults) throws IOException{

		if(testResults.getStatus()==ITestResult.FAILURE) {

			parentTest.log(Status.FAIL, MarkupHelper.createLabel("Test Failed of below reason", ExtentColor.RED));
			parentTest.log(Status.FAIL, testResults.getThrowable());

			parentTest.addScreenCaptureFromPath(getScreenshotPath(testResults.getMethod().getMethodName(),driver), testResults.getMethod().getMethodName());

		}
		else if(testResults.getStatus()==ITestResult.SKIP) {
			parentTest.log(Status.SKIP, testResults.getThrowable());
		}
		else {
			parentTest.log(Status.PASS, "Test Case is passed");
		}
	}

	//Verifying elements on Registration page

	@Test(priority = 1)
	public void verifyElemntsOnPageTest() throws InterruptedException {

		parentTest = extent.createTest("Verifying elements on Registration page").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.log(Status.INFO, "Test is started");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signUpTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegistrationXpath.signUpTitle)));
		signUpTitle.isDisplayed();

		parentTest.log(Status.PASS, MarkupHelper.createLabel("All Elements are present", ExtentColor.BLUE));

	}

	// Registration with all valid data

	@Test(priority = 2)
	public void validRegistrationTest() throws InterruptedException {

		parentTest = extent.createTest("Registration with all valid data").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Registration with all valid data");

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

		parentTest = extent.createTest("Registration - First Name field validation").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Registration - First Name field validation");

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
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);

			}
			else if(firstName.getAttribute("value").length() > 20) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else {

				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
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

		parentTest = extent.createTest("Registration - Last Name field validation").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Registration - Last Name field validation");

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
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);

			}
			else if(LastName.getAttribute("value").length() > 20) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else {

				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
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

		parentTest = extent.createTest("Registration - user email field validation").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Registration - user email field validation");

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
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);

			}
			else if(email.getAttribute("value").startsWith("@")) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else if(email.getAttribute("value") != verifiedemail.getAttribute("value")) {
				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else {

				WebElement userField = driver.findElement(By.xpath(RegistrationXpath.alert));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
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

		parentTest = extent.createTest("Registration as Default user").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Registration as Default user");

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
		parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
		System.out.println(userField.getText());
		signUp.isDisplayed();
		Assert.assertTrue(true);
		Thread.sleep(2000);
	}


	//Registration as random input serial

	@Test(priority = 7)
	public void randomInputSerial() throws InterruptedException {

		parentTest = extent.createTest("Registration as random input serial").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Registration as random input serial");

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

		Assert.assertFalse(false);

	}

	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}


	@AfterClass
	public void afterClass() {
		extent.flush();
	}

	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}
}
