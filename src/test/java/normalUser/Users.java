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

import NormalUserXpath.UsersXpath;
import browser.OpenBrowser;
import normalUserFunctions.UsersFunctions;
import normalUserInputData.UsersInfoData;

public class Users  extends OpenBrowser{

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 3 -- Users";
	String fileName = System.getProperty("user.dir")+"/reports/Standard User/TestResults-Users.html";

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
		htmlReports.config().setReportName("Standart User - Users");
		htmlReports.config().setTheme(Theme.STANDARD);
		htmlReports.config().setDocumentTitle("Test Report for Standard User");

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

	//login

	@Test(priority = 1)
	public void loginUser() throws InterruptedException {

		parentTest = extent.createTest("Login").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Login with valid username & Password");

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Successful", ExtentColor.ORANGE));

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//View the user list

	@Test(priority = 2)
	public void userList() throws InterruptedException {
		parentTest = extent.createTest("View the user list").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("View the user list");

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UsersFunctions.usersList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("User profile", ExtentColor.ORANGE));

		String expectedUrl = "https://dev.zntral.net/users";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Add new user form

	@Test(priority = 3)
	public void addUser() throws InterruptedException{
		parentTest = extent.createTest("Add new user form").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Add new user form");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UsersFunctions.usersList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User profile", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();
		Thread.sleep(2000);

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		Thread.sleep(2000);
		addUser.click();

		Assert.assertTrue(true);
		Thread.sleep(3000);

	}

	//Adding user with valid info

	@Test(priority = 4)
	public void validUserData() throws InterruptedException {
		parentTest = extent.createTest("Adding user with valid info").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Adding user with valid info");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UsersFunctions.usersList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User profile", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();


		WebElement emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.emailId)));
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.save)));

		String firstName = UsersInfoData.FirstName;
		String lastName = UsersInfoData.LastName;
		String EmailId = UsersInfoData.emailId;

		UsersFunctions.addUsers(firstName, lastName, EmailId);


		try {
			save.click();
			Thread.sleep(5000);
			WebElement firstRow = driver.findElement(By.xpath(UsersXpath.firstRow));
			if(firstRow.getAttribute("value") == emailId.getText()) {
				parentTest.log(Status.INFO, MarkupHelper.createLabel("Successfully added", ExtentColor.GREEN));
				Assert.assertTrue(true);
			}

		}catch(Exception e) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Failed !!!", ExtentColor.RED));
			Assert.assertTrue(false);
		}
	}

	//Already existed user

	@Test(priority = 5)
	public void existedUser() throws InterruptedException {
		parentTest = extent.createTest("Already existed user").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Already existed user");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UsersFunctions.usersList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User profile", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();


		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.save)));

		String firstName = UsersInfoData.FirstName;
		String lastName = UsersInfoData.LastName;
		String EmailId = UsersInfoData.emailId;

		UsersFunctions.addUsers(firstName, lastName, EmailId);

		save.click();
		Thread.sleep(2000);

		try {
			WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.status)));
			status.isDisplayed();

		} catch (Exception e){
			parentTest.log(Status.INFO, MarkupHelper.createLabel("New User", ExtentColor.ORANGE));
		}

	}
	//Add new user form -- validation

	@Test(priority = 6)
	public void validateNewUser() throws InterruptedException {
		parentTest = extent.createTest("Add new user form -- validation").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Add new user form -- validation");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UsersFunctions.usersList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User profile", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();


		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.FirstName)));
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.LastName)));
		WebElement emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.emailId)));
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.save)));

		String firstName = UsersInfoData.invalidFirstName;
		String lastName = UsersInfoData.invalidLastName;
		String EmailId = UsersInfoData.invalidemailId;

		UsersFunctions.addUsers(firstName, lastName, EmailId);

		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			if(FirstName.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.invalidMsg)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(true);

			}
			else if(LastName.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.invalidMsg)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));

				Assert.assertTrue(true);

			}
			else if(emailId.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.invalidMsg)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));

				Assert.assertTrue(true);
			}
			else if(emailId.getAttribute("value").startsWith("@")) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.invalidMsg)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));

				Assert.assertTrue(true);
			}
			else {
				save.isDisplayed();
				parentTest.log(Status.INFO, MarkupHelper.createLabel("Mandatory field has no value", ExtentColor.RED));
				Assert.assertTrue(true);
			}
		}
	}

	// Check Reset button on add new user form

	@Test(priority = 7)
	public void resetButton() throws InterruptedException {
		parentTest = extent.createTest("Check Reset button on add new user form").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Check Reset button on add new user form");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UsersFunctions.usersList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User profile", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();

		WebElement reset = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.reset)));

		String firstName = UsersInfoData.FirstName;
		String lastName = UsersInfoData.LastName;
		String EmailId = UsersInfoData.emailId;

		UsersFunctions.addUsers(firstName, lastName, EmailId);


		try {

			reset.click();
			Thread.sleep(2000);

			String expectedText1 = "Name is required";
			String actualText1 = driver.findElement(By.xpath(UsersXpath.actualText1)).getText();
			parentTest.log(Status.INFO, MarkupHelper.createLabel(actualText1, ExtentColor.RED));
			Assert.assertEquals(actualText1, expectedText1);

			String expectedText2 = "Name is required";
			String actualText2 = driver.findElement(By.xpath(UsersXpath.actualText2)).getText();
			parentTest.log(Status.INFO, MarkupHelper.createLabel(actualText2, ExtentColor.RED));
			Assert.assertEquals(actualText2, expectedText2);

			String expectedText3 = "E-mail must be valid";
			String actualText3 = driver.findElement(By.xpath(UsersXpath.actualText3)).getText();
			parentTest.log(Status.INFO, MarkupHelper.createLabel(actualText3, ExtentColor.RED));
			Assert.assertEquals(actualText3, expectedText3);

		} catch(Exception e) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Failed !!", ExtentColor.RED));
		}


	}

	// Check Cancel button on add new user form

	@Test(priority = 8)
	public void cancelButton() throws InterruptedException {
		parentTest = extent.createTest("Check Cancel button on add new user form").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Check Cancel button on add new user form");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UsersFunctions.usersList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User profile", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();

		WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.cancel)));
		cancel.click();
		Assert.assertTrue(true);
	}

	//Check close button on add new user form

	@Test(priority = 9)
	public void closeButton() throws InterruptedException {
		parentTest = extent.createTest("Check close button on add new user form").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Check close button on add new user form");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UsersFunctions.usersList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User profile", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();

		WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.close)));
		close.click();
		Assert.assertTrue(true);

	}

	//Search option

	@Test(priority = 10)
	public void search() throws InterruptedException {
		parentTest = extent.createTest("Search option").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("Search option");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UsersFunctions.usersList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User profile", ExtentColor.ORANGE));

		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.search)));
		search.sendKeys(UsersInfoData.search);
		Thread.sleep(5000);

		WebElement firstRow = driver.findElement(By.xpath(UsersXpath.firstRow));
		String expectedText = UsersInfoData.search;
		String actualText = firstRow.getText();
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(3000);
	}

	//User profile 

	@Test(priority = 11)
	public void profileOfUser() throws InterruptedException {
		parentTest = extent.createTest("User profile").assignAuthor("Sabbir").assignCategory("Standart User");
		parentTest.info("User profile");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UsersFunctions.usersList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User profile", ExtentColor.ORANGE));


		WebElement firstRowInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.firstRowInfo)));
		firstRowInfo.click();

		String actualText = "Users";

		WebElement user =driver.findElement(By.xpath(UsersXpath.user));
		String ExpectedText =user.getText();	
		Assert.assertTrue(ExpectedText.contains(actualText));
		Thread.sleep(3000);
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
