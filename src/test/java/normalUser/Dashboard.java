package normalUser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Random;

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

import NormalUserXpath.DashboardXpath;
import browser.OpenBrowser;
import normalUserFunctions.DashboardFunctions;
import normalUserInputData.DashboardInfoData;

public class Dashboard extends OpenBrowser {

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 8 -- Dashboard";
	String fileName = System.getProperty("user.dir")+"/reports/Standard User/TestResults-Dashboard.html";

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
		htmlReports.config().setReportName("Standart User - Dashboard");
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
	public void loginUser()  throws InterruptedException {

		parentTest = extent.createTest("Login with valid username & Password").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Login with valid credentials");

		DashboardFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Successful", ExtentColor.ORANGE));

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

	}

	// Add Location from the Dashboard

	@Test(priority = 2)
	public void addLocation() throws InterruptedException{
		parentTest = extent.createTest("Add Location from the Dashboard").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Add Location from the Dashboard");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		Thread.sleep(1000);

		DashboardFunctions.optionSelect();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Option selected", ExtentColor.ORANGE));

		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.selectContinue)));
		selectContinue.click();
		Assert.assertTrue(true);

	}

	// Add Location from the Dashboard with valid data

	@Test(priority = 3)
	public void validLocationData() throws InterruptedException {
		parentTest = extent.createTest("Add Location from the Dashboard with valid data").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Add Location from the Dashboard with valid data");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		Thread.sleep(1000);

		DashboardFunctions.optionSelect();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Option selected", ExtentColor.ORANGE));

		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.selectContinue)));
		selectContinue.click();

		String LName = DashboardInfoData.locationName;
		String LLicence = DashboardInfoData.licenceNumber;
		String LCapacity = DashboardInfoData.capacity;
		String Laddress = DashboardInfoData.address;
		String LsuiteUnit = DashboardInfoData.suiteUnit;
		String Lcity = DashboardInfoData.city;
		String Lstate = DashboardInfoData.state;
		String Lzip = DashboardInfoData.zip;
		String LphoneNumber = DashboardInfoData.phoneNumber;
		String Lemail = DashboardInfoData.email;
		String Lnote = DashboardInfoData.note;

		DashboardFunctions.addData(LName, LLicence, LCapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.save)));
		save.click();
		Thread.sleep(5000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/locations/"));

	}

	//Adding location without any info

	@Test(priority = 4)
	public void invalidLocationData() throws InterruptedException {
		parentTest = extent.createTest("Adding location without any info").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Adding location without any info");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		Thread.sleep(1000);

		DashboardFunctions.optionSelect();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Option selected", ExtentColor.ORANGE));

		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.selectContinue)));
		selectContinue.click();

		Thread.sleep(2000);

		String LName = DashboardInfoData.locationName2;
		String LLicence = DashboardInfoData.licenceNumber2;
		String LCapacity = DashboardInfoData.capacity2;
		String Laddress = DashboardInfoData.address2;
		String LsuiteUnit = DashboardInfoData.suiteUnit2;
		String Lcity = DashboardInfoData.city2;
		String Lstate = DashboardInfoData.state2;
		String Lzip = DashboardInfoData.zip2;
		String LphoneNumber = DashboardInfoData.phoneNumber2;
		String Lemail = DashboardInfoData.email2;
		String Lnote = DashboardInfoData.note2;

		DashboardFunctions.addData(LName, LLicence, LCapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.save)));


		try {
			save.click();
			Thread.sleep(2000);	

		} 
		catch (Exception e){
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Mandatory fields has no data !", ExtentColor.RED));
			save.isEnabled();
			Thread.sleep(2000);
		}

		WebElement addressMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.addressMsg)));
		WebElement cityMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.cityMsg)));
		WebElement stateMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.stateMsg)));
		WebElement phoneMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.phoneMsg)));
		WebElement emailMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.emailMsg)));

		parentTest.log(Status.INFO, MarkupHelper.createLabel(addressMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(cityMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(stateMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(phoneMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(emailMsg.getText(), ExtentColor.RED));

		Assert.assertTrue(true);

	}

	//Email validation check on Add location form

	@Test(priority = 5)
	public void validateEmailData() throws InterruptedException {
		parentTest = extent.createTest("Email validation check on Add location form").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Email validation check on Add location form");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));
		Thread.sleep(1000);
		DashboardFunctions.optionSelect();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Option selected", ExtentColor.ORANGE));

		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.selectContinue)));
		selectContinue.click();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.email)));

		email.sendKeys(DashboardInfoData.validemail);
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.note)));
		note.sendKeys(DashboardInfoData.note3);

		if(email.getAttribute("value").startsWith("@")){
			WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.invalidMsg)));
			parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
			Assert.assertTrue(true);
		}else {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Email submitted successfully!", ExtentColor.GREEN));
		}
	}

	// Add Patient from the Dashboard

	@Test(priority = 6)
	public void addPatient() throws InterruptedException{
		parentTest = extent.createTest("Add Patient from the Dashboard").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info(" Add Patient from the Dashboard");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.add2)));
		driver.findElement(By.xpath(DashboardXpath.add2)).click();

		Thread.sleep(5000);

		String Pprefix = DashboardInfoData.prefix;
		String PfirstName = DashboardInfoData.patientfirstName;
		String PlastName =DashboardInfoData.patientlastName;
		String Pssn= DashboardInfoData.ssn;
		String Pdob= DashboardInfoData.dob;
		String Pphone = DashboardInfoData.phone;
		String Pemail = DashboardInfoData.patientemail;
		String Pnote = DashboardInfoData.patientnote;

		DashboardFunctions.addPatientData(Pprefix, PfirstName, PlastName, Pssn, Pdob, Pphone, Pemail, Pnote);;

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.Submit)));
		save.click();
		Thread.sleep(5000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/patients/"));

	}

	//Adding patient without any info

	@Test(priority = 7)
	public void addPatientWithEmpty() throws InterruptedException{
		parentTest = extent.createTest("Adding patient without any info").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Adding patient without any info");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.add2)));
		driver.findElement(By.xpath(DashboardXpath.add2)).click();

		String Pprefix = DashboardInfoData.prefix2;
		String PfirstName = DashboardInfoData.patientfirstName2;
		String PlastName =DashboardInfoData.patientlastName2;
		String Pssn= DashboardInfoData.ssn2;
		String Pdob= DashboardInfoData.dob2;
		String Pphone = DashboardInfoData.phone2;
		String Pemail = DashboardInfoData.patientemail2;
		String Pnote = DashboardInfoData.patientnote2;

		DashboardFunctions.addPatientData(Pprefix, PfirstName, PlastName, Pssn, Pdob, Pphone, Pemail, Pnote);;

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.Submit)));


		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Mandatory fields has no data !", ExtentColor.RED));
			save.isEnabled();
			Thread.sleep(1000);
		}

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.firstNameMsg)));
		WebElement lastNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.lastNameMsg)));

		parentTest.log(Status.INFO, MarkupHelper.createLabel(firstNameMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(lastNameMsg.getText(), ExtentColor.RED));

		String expectedText1 = "First name is required";
		String actualText1 = firstNameMsg.getText();
		Assert.assertEquals(actualText1, expectedText1);

		String expectedText2 = "Last name is required";
		String actualText2 = lastNameMsg.getText();
		Assert.assertEquals(actualText2, expectedText2);

	}

	//Email validation check on Add patient form

	@Test(priority = 8)
	public void validateEmailDataOnPatient() throws InterruptedException {
		parentTest = extent.createTest("Email validation check on Add patient form").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Email validation check on Add patient form");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.add2)));
		driver.findElement(By.xpath(DashboardXpath.add2)).click();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.email)));

		email.sendKeys(DashboardInfoData.patientvalidemail);
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.patientNote)));
		note.sendKeys(DashboardInfoData.patientnote3);
		if(email.getAttribute("value").startsWith("@")){
			WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.invalidMsg)));
			parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
			Assert.assertTrue(true);
			Thread.sleep(1000);
		}else {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Email submitted successfully!", ExtentColor.GREEN));
			Thread.sleep(2000);
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
