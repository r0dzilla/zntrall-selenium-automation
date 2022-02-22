package superAdmin;

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

import browser.OpenBrowser;
import superAdminFunctions.LoginFunctions;
import superAdminInputData.LoginInfoData;
import superAdminXpath.LoginXpath;

public class Login extends OpenBrowser{
	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 1 -- Login";
	String fileName = System.getProperty("user.dir")+"/reports/Super Admin/TestResults-Login.html";

	public static ExtentTest parentTest;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentSparkReporter htmlReports;

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
		htmlReports.config().setReportName("Super Admin - Login");
		htmlReports.config().setTheme(Theme.STANDARD);
		htmlReports.config().setDocumentTitle("Test Report for Super Admin");


	}

	public String getScreenshotPath(String TestcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destPath = "\\home\\b0b\\zntrall-selenium-automation\\screenshots\\"+TestcaseName+".png";
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

		parentTest = extent.createTest("Verifying elements on Login page").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.log(Status.INFO, "Test is started");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.signInTitle)));
		signInTitle.isDisplayed();

		parentTest.log(Status.PASS, MarkupHelper.createLabel("All Elements are present", ExtentColor.BLUE));
	}

	//login with valid username & Password

	@Test(priority = 2)
	public static void verifyLogin() throws InterruptedException {

		parentTest = extent.createTest("login with valid username & Password").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Login with valid credentials");

		LoginFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Successful", ExtentColor.GREEN));
	}

	//login without username

	@Test(priority = 3)
	public void loginWithoutUsername() throws InterruptedException {

		parentTest = extent.createTest("login without username").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Login with invalid credentials");

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
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Successful", ExtentColor.GREEN));
		}
		catch(Exception e) {
			if(username.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath(LoginXpath.userFieldMsg));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
				login.isDisplayed();
				parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Now button is displayed", ExtentColor.GREEN));
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

		parentTest = extent.createTest("login without Password").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Login with invalid credentials");

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
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Successful", ExtentColor.GREEN));
		}
		catch(Exception e) {
			if(password.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath(LoginXpath.userFieldMsg));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(userField.getText(), ExtentColor.RED));
				login.isDisplayed();
				parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Now button is displayed", ExtentColor.GREEN));
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

		parentTest = extent.createTest("login with invalid info").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Login with invalid credentials");

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

		parentTest.log(Status.INFO, MarkupHelper.createLabel(error.getText(), ExtentColor.RED));

		String expectedUrl = "https://dev.zntral.net/session/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		Assert.assertTrue(true);

	}


	@AfterClass
	public void afterClass() {
		extent.flush();
	}

}
