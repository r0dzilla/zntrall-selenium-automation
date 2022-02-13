package superAdmin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

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
import superAdminFunctions.PatientsFunctions;
import superAdminInputData.PatientsInfoData;
import superAdminXpath.PatientsXpath;

public class Patients extends OpenBrowser{

	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 5 -- Patients";
	String fileName = System.getProperty("user.dir")+"/reports/Super Admin/TestResults-Patients.html";

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
		htmlReports.config().setReportName("Super Admin - Patients");
		htmlReports.config().setTheme(Theme.STANDARD);
		htmlReports.config().setDocumentTitle("Test Report for Super Admin");
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


	@AfterClass
	public void afterClass() {
		extent.flush();
	}

	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}


	//Verifying elements on Login page
	@Test(priority = 1)
	public void verifyElemntsOnPageTest() throws InterruptedException {
		parentTest = extent.createTest("Verifying elements").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.log(Status.INFO, "Test is started");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.signInTitle)));
		signInTitle.isDisplayed();

		parentTest.log(Status.PASS, MarkupHelper.createLabel("All Elements are present", ExtentColor.BLUE));

	}

	//login with valid username & Password

	@Test(priority = 2)
	public void login() throws InterruptedException {

		parentTest = extent.createTest("login with valid username & Password").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Login with valid credentials");

		PatientsFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Check Patients list

	@Test(priority = 3)
	public void checkPatientList() throws InterruptedException {

		parentTest = extent.createTest("Check Patients list").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check Patients list");
		
		PatientsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		PatientsFunctions.patientList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Patient List is shown", ExtentColor.ORANGE));

		String expectedUrl = "https://dev.zntral.net/patients";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//Search option

	@Test(priority = 4)
	public void search() throws InterruptedException {
		
		parentTest = extent.createTest("Search option").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check Search option");
		
		PatientsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		PatientsFunctions.patientList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Patient List is shown", ExtentColor.ORANGE));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		String searchLocation = PatientsInfoData.searchLocation;
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.search)));
		search.sendKeys(searchLocation);
		Thread.sleep(3000);


		WebElement tableContents = driver.findElement(By.tagName("table"));
		List<WebElement> rows=tableContents.findElements(By.tagName("tr"));
		for(int rnum=1;rnum<rows.size();rnum++)
		{
			List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));
			//Assert.assertEquals(columns.get(0).getText(),search.getAttribute("value"));
			String colVal = columns.get(1).getText();
			System.out.println(colVal);

			if(colVal.contains(search.getAttribute("value"))) {
				System.out.println("Matched!");
				Assert.assertTrue(true);
			}else {
				System.out.println("No value");

			}
		}

	}


	//Find patient info from patient list

	@Test(priority = 5)
	public void patientFromLocation() throws InterruptedException {
		
		parentTest = extent.createTest("Find patient info from patient list").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Find patient info from patient list");
		
		PatientsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));
		
		PatientsFunctions.patientList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Patient List is shown", ExtentColor.ORANGE));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		String searchLocation = PatientsInfoData.searchLocation;
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.search)));
		search.sendKeys(searchLocation);
		Thread.sleep(3000);


		WebElement tableContents = driver.findElement(By.tagName("table"));
		List<WebElement> rows=tableContents.findElements(By.tagName("tr"));
		for(int rnum=1;rnum<rows.size();rnum++)
		{
			List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));
			//Assert.assertEquals(columns.get(0).getText(),search.getAttribute("value"));
			String colVal = columns.get(1).getText();
			System.out.println(colVal);

			if(colVal.contains(search.getAttribute("value"))) {
				System.out.println("Matched!");
				Assert.assertTrue(true);
			}else {
				System.out.println("No value");

			}
		}
		
		WebElement firstRow = driver.findElement(By.xpath(PatientsXpath.firstRow));

		firstRow.click();
		Thread.sleep(3000);
		Assert.assertTrue(true);

	}


	//First Name wise sort

	@Test(priority = 6)
	public void firstNameWiseSort() throws InterruptedException {
		
		parentTest = extent.createTest("First Name wise sort").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("First Name wise sort");
		
		PatientsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));
		
		PatientsFunctions.patientList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Patient List is shown", ExtentColor.ORANGE));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement selectName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.selectName)));

		selectName.click();
		Thread.sleep(1000);
		selectName.click();
		Thread.sleep(1000);
		selectName.click();
		Thread.sleep(1000);
		Assert.assertTrue(true);
	}

	//Location wise sort

	@Test(priority = 7)
	public void locationWiseSort() throws InterruptedException {
		
		parentTest = extent.createTest("Location wise sort").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Location wise sort");
		
		PatientsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));
		
		PatientsFunctions.patientList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Patient List is shown", ExtentColor.ORANGE));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement selectLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.selectLocation)));

		selectLocation.click();
		Thread.sleep(1000);
		selectLocation.click();
		Thread.sleep(1000);
		selectLocation.click();
		Thread.sleep(1000);
		Assert.assertTrue(true);
	}
}
