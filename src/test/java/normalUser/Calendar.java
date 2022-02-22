package normalUser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

import NormalUserXpath.CalendarXpath;
import browser.OpenBrowser;
import normalUserFunctions.CalendarFunctions;
import normalUserInputData.CalendarInfoData;

public class Calendar extends OpenBrowser{

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 6 -- Calendar";
	String fileName = System.getProperty("user.dir")+"/reports/Standard User/TestResults-Calendar.html";

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
		htmlReports.config().setReportName("Standart User - Calendar");
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

		parentTest = extent.createTest("login with valid username & Password").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Login with valid credentials");

		CalendarFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Successful", ExtentColor.GREEN));

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

	}


	//View the calendar

	@Test(priority = 2)
	public void calendar() throws InterruptedException {

		parentTest = extent.createTest("View the calendar").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("View the calendar");

		CalendarFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		CalendarFunctions.calendarList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Calendar", ExtentColor.ORANGE));
		
		String expectedUrl = "https://dev.zntral.net/patient_schedule";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//Add a single schedule on upcoming days

	@Test(priority = 3)
	public void addSingleEvent() throws InterruptedException {
		
		parentTest = extent.createTest("Add a single schedule on upcoming days").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Add a single schedule on upcoming days");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		CalendarFunctions.calendarList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Calendar", ExtentColor.ORANGE));

		WebElement selectDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectDate)));
		selectDate.click();
		WebElement patientSelectDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.patientSelectDropDown)));
		patientSelectDropDown.click();
		WebElement patientSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.patientSelect)));
		patientSelect.click();
		//		WebElement dateFieldSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.dateFieldSelect)));
		//		dateFieldSelect.click();
		//		WebElement dateSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.dateSelect)));
		//		dateSelect.click();
		WebElement allDay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.allDay)));
		allDay.click();
		WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.description)));
		String desc = CalendarInfoData.desc;
		description.sendKeys(desc);
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.save)));
		save.click();
		Thread.sleep(3000);

		try {
			WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.errorMsg)));
			if(driver.switchTo().alert() != null) {
				parentTest.log(Status.INFO, MarkupHelper.createLabel(errorMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(false);
			}

		}
		catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	//cancel a schedule

	@Test(priority = 4)
	public void cancelSchedule() throws InterruptedException {
		
		parentTest = extent.createTest("Cancel a schedule").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Cancel a schedule");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));
		
		CalendarFunctions.calendarList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Calendar", ExtentColor.ORANGE));

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectSchedule)));
		selectSchedule.click();
		WebElement selectThreeDotOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectThreeDotMenu)));
		selectThreeDotOption.click();
		WebElement selectCancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectCancel)));
		selectCancel.click();
		Assert.assertTrue(true);

	}

	//delete a schedule

	@Test(priority = 5)
	public void deleteSchedule() throws InterruptedException {
		
		parentTest = extent.createTest("Delete a schedule").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Delete a schedule");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		CalendarFunctions.calendarList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Calendar", ExtentColor.ORANGE));

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectSchedule)));
		selectSchedule.click();
		WebElement selectThreeDotOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectThreeDotMenu)));
		selectThreeDotOption.click();
		WebElement selectDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectDelete)));
		selectDelete.click();
		WebElement selectRecurringOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectRecurringOption)));
		selectRecurringOption.click();
		WebElement delete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.delete)));
		delete.click();
		Assert.assertTrue(true);
	}

	//Edit a schedule

	@Test(priority = 6)
	public void editSchedule() throws InterruptedException {
		
		parentTest = extent.createTest("Edit a schedule").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Edit a schedule");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		CalendarFunctions.calendarList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Calendar", ExtentColor.ORANGE));

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectSchedule)));
		selectSchedule.click();
		WebElement selectThreeDotOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectThreeDotMenu)));
		selectThreeDotOption.click();
		WebElement selectEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectEdit)));
		selectEdit.click();
		Thread.sleep(3000);
		WebElement selectPatientDrpDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.patientSelectDropDown)));
		selectPatientDrpDown.click();
		WebElement selectPatient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectPatient)));
		selectPatient.click();
		WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.description)));
		String desc = CalendarInfoData.desc;
		description.sendKeys(desc);
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.save)));
		save.click();
		Thread.sleep(3000);
	}

	// Day view

	@Test(priority = 7)
	public void dayViewOnCalendar() throws InterruptedException {
		parentTest = extent.createTest("Day view").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Day view");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));
		
		CalendarFunctions.calendarList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Calendar", ExtentColor.ORANGE));
		
		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectDay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectDay)));
		selectDay.click();
	}

	// Week view

	@Test(priority = 8)
	public void weekViewOnCalendar() throws InterruptedException {
		parentTest = extent.createTest("Week view").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Week view");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		CalendarFunctions.calendarList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Calendar", ExtentColor.ORANGE));
		
		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectWeek = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectWeek)));
		selectWeek.click();
	}

	// Month view

	@Test(priority = 9)
	public void monthViewOnCalendar() throws InterruptedException {
		parentTest = extent.createTest("Month view").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Month view");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		CalendarFunctions.calendarList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Calendar", ExtentColor.ORANGE));

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectMonth)));
		selectMonth.click();
	}

	// Agenda view

	@Test(priority = 10)
	public void agendaViewOnCalendar() throws InterruptedException {
		parentTest = extent.createTest("Agenda view").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Agenda view");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		CalendarFunctions.calendarList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Calendar", ExtentColor.ORANGE));
		
		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectAgenda = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectAgenda)));
		selectAgenda.click();
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
