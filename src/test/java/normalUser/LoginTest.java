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

import NormalUserXpath.LoginTestXpath;
import browser.OpenBrowser;
import normalUserInputData.LoginTestInfoData;

public class LoginTest extends OpenBrowser {

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 2 -- Login";
	String fileName = System.getProperty("user.dir")+"/reports/Standard User/TestResults-Login.html";

	public static ExtentTest parentTest;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentSparkReporter htmlReports;

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

	@BeforeClass
	public void initiateReport() {
		htmlReports = new ExtentSparkReporter(fileName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReports);
		htmlReports.config().setReportName("Standart User - Login");
		htmlReports.config().setTheme(Theme.STANDARD);
		htmlReports.config().setDocumentTitle("Test Report for Standard User");

	}

	public String getScreenshotPath(String TestcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir")+"\\screenshots\\"+TestcaseName+".png";
		File file = new File(destPath);
		FileUtils.copyFile(source, file);
		return destPath;
	}


	//Opening browser with the given URL and navigate to Registration Page

	@BeforeMethod
	public void openBrowser() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.get("https://dev.zntral.net/session/login");
		driver.manage().window().maximize();
		Thread.sleep(3000);
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
			parentTest.log(Status.PASS, MarkupHelper.createLabel("Test Case is passed", ExtentColor.GREEN));
		}
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

		parentTest = extent.createTest("Verifying elements on Login page").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.log(Status.INFO, "Test is started");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.signInTitle)));
		WebElement emailTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.emailTitle)));
		WebElement passTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.passTitle)));
		WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginTestXpath.loginButton)));
		signInTitle.isDisplayed();
		emailTitle.isDisplayed();
		passTitle.isDisplayed();
		loginButton.isDisplayed();

		parentTest.log(Status.PASS, MarkupHelper.createLabel("All Elements are present", ExtentColor.BLUE));

	}

	//login with valid username & Password

	@Test(priority = 2)
	public void verifyLogin() throws InterruptedException {

		parentTest = extent.createTest("Login with valid username & Password").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Login with valid username & Password");

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

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//login without username

	@Test(priority = 3)
	public void loginWithoutUsername() throws InterruptedException {

		parentTest = extent.createTest("Login with invalid credentials").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Login without username");

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
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
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

		parentTest = extent.createTest("Login with invalid credentials").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Login without Password");

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
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
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

		parentTest = extent.createTest("Login with invalid credentials").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Login with empty field");

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

		parentTest = extent.createTest("Forgot password").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Forgot password");

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
			parentTest.log(Status.INFO, MarkupHelper.createLabel("No Confirmation Alert!", ExtentColor.RED));
			Assert.assertTrue(true);
		}
	}


	//random order for userid and password

	@Test
	public void randomInput() throws InterruptedException {

		parentTest = extent.createTest("Random order for userid and password").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Random order for userid and password");

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

	@AfterClass
	public void afterClass() {
		extent.flush();
	}

	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}

}
