package normalUser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

import NormalUserXpath.LocationsXpath;
import browser.OpenBrowser;
import normalUserFunctions.LocationsFunctions;
import normalUserInputData.LocationInfoData;
import normalUserInputData.PatientsInfoData;

public class Locations extends OpenBrowser{
	public static String env = "Test";
	public static String testSuiteName = "Test Suit 4 -- Locations";
	String fileName = System.getProperty("user.dir")+"/reports/Standard User/TestResults-Locations.html";

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
		htmlReports.config().setReportName("Standart User - Locations");
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

		parentTest = extent.createTest("Login with valid username & Password").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Login with valid credentials");

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Successful", ExtentColor.ORANGE));

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);


	}


	//View the location list

	@Test(priority = 2)
	public void locationList() throws InterruptedException {
		parentTest = extent.createTest("View the location list").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("View the location list");

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		String expectedUrl = "https://dev.zntral.net/locations";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Search option

	@Test(priority = 3)
	public void search() throws InterruptedException {
		parentTest = extent.createTest("Search option").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Search option");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.search)));
		driver.findElement(By.xpath(LocationsXpath.search)).sendKeys(LocationInfoData.searchLocation);
		Thread.sleep(2000);
		WebElement firstRow = driver.findElement(By.xpath(LocationsXpath.firstRow));

		String expectedText = LocationInfoData.searchLocation;
		String actualText = firstRow.getText();
		Assert.assertEquals(actualText, expectedText);
	}

	//Add new Location -- Click "+" button

	@Test(priority = 4)
	public void addLocation() throws InterruptedException{

		parentTest = extent.createTest("Add new Location -- Click \"+\" button").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Add new Location -- Click \"+\" button");

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		LocationsFunctions.addResidentType();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Resident Type Selected", ExtentColor.ORANGE));


	}

	//Adding location with valid info

	@Test(priority = 5)
	public void validLocationData() throws InterruptedException {
		parentTest = extent.createTest("Adding location with valid info").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Adding location with valid info");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		LocationsFunctions.addResidentType();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Resident Type Selected", ExtentColor.ORANGE));


		String LName = LocationInfoData.locationName;
		String LlicenceNumber = LocationInfoData.licenceNumber;
		String Lcapacity = LocationInfoData.capacity;
		String Laddress = LocationInfoData.address;
		String LsuiteUnit = LocationInfoData.suiteUnit;
		String Lcity = LocationInfoData.city;
		String Lstate = LocationInfoData.state;
		String Lzip = LocationInfoData.zip;
		String LphoneNumber=LocationInfoData.phoneNumber;
		String Lemail=LocationInfoData.email;
		String Lnote=LocationInfoData.note;

		LocationsFunctions.addLocationData(LName, LlicenceNumber, Lcapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));
		save.click();
		Thread.sleep(5000);


		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/locations/"));

	}

	//Adding location without any info

	@Test(priority = 6)
	public void invalidLocationData() throws InterruptedException {
		parentTest = extent.createTest("Adding location without any info").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Adding location without any info");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		LocationsFunctions.addResidentType();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Resident Type Selected", ExtentColor.ORANGE));


		String LName = LocationInfoData.locationName2;
		String LlicenceNumber = LocationInfoData.licenceNumber2;
		String Lcapacity = LocationInfoData.capacity2;
		String Laddress = LocationInfoData.address2;
		String LsuiteUnit = LocationInfoData.suiteUnit2;
		String Lcity = LocationInfoData.city2;
		String Lstate = LocationInfoData.state2;
		String Lzip = LocationInfoData.zip2;
		String LphoneNumber=LocationInfoData.phoneNumber2;
		String Lemail=LocationInfoData.email2;
		String Lnote=LocationInfoData.note2;

		LocationsFunctions.addLocationData(LName, LlicenceNumber, Lcapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));
		save.isEnabled();
		Thread.sleep(5000);

		WebElement addressMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.addressMsg)));
		WebElement cityMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.cityMsg)));
		WebElement stateMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.stateMsg)));
		WebElement phoneMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneMsg)));
		WebElement emailMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.emailMsg)));

		parentTest.log(Status.INFO, MarkupHelper.createLabel(addressMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(cityMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(stateMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(phoneMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(emailMsg.getText(), ExtentColor.RED));

		String expectedText1 = "Address is required";
		String actualText1 = addressMsg.getText();
		Assert.assertEquals(actualText1, expectedText1);

		String expectedText2 = "City is required";
		String actualText2 = cityMsg.getText();
		Assert.assertEquals(actualText2, expectedText2);

		String expectedText3 = "State is required";
		String actualText3 = stateMsg.getText();
		Assert.assertEquals(actualText3, expectedText3);

		String expectedText4 = "Phone is required";
		String actualText4 = phoneMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		String expectedText5 = "E-mail must be valid";
		String actualText5 = emailMsg.getText();
		Assert.assertEquals(actualText5, expectedText5);

	}

	//Adding location form --- Phone number, Email validation check

	@Test(priority = 7)
	public void validatePhoneEmailData() throws InterruptedException {
		parentTest = extent.createTest("Adding location form").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Phone number, Email validation check");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		LocationsFunctions.addResidentType();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Resident Type Selected", ExtentColor.ORANGE));


		String LName = LocationInfoData.locationName;
		String LlicenceNumber = LocationInfoData.licenceNumber;
		String Lcapacity = LocationInfoData.capacity;
		String Laddress = LocationInfoData.address;
		String LsuiteUnit = LocationInfoData.suiteUnit;
		String Lcity = LocationInfoData.city;
		String Lstate = LocationInfoData.state;
		String Lzip = LocationInfoData.zip;
		String LphoneNumber=LocationInfoData.phoneNumber3;
		String Lemail=LocationInfoData.mail3;
		String Lnote=LocationInfoData.note;

		LocationsFunctions.addLocationData(LName, LlicenceNumber, Lcapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));
		save.isEnabled();
		Thread.sleep(5000);


		WebElement phoneMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneMsg2)));
		WebElement emailMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.emailMsg)));

		parentTest.log(Status.INFO, MarkupHelper.createLabel(phoneMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(emailMsg.getText(), ExtentColor.RED));

		String expectedText4 = "Phone Number minimum 10 digit required";
		String actualText4 = phoneMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		String expectedText5 = "E-mail must be valid";
		String actualText5 = emailMsg.getText();
		Assert.assertEquals(actualText5, expectedText5);

	}

	//Adding location form --- back button check

	@Test(priority = 8)
	public void validateBackButton() throws InterruptedException {
		parentTest = extent.createTest("Adding location form").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Back button check");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		LocationsFunctions.addResidentType();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Resident Type Selected", ExtentColor.ORANGE));


		WebElement back = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.back)));
		back.click();

		Assert.assertTrue(true);
	}


	//Adding location form --- cancel button check

	@Test(priority = 9)
	public void validateCancelButton() throws InterruptedException {
		parentTest = extent.createTest("Adding location form").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Cancel button check");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		LocationsFunctions.addResidentType();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Resident Type Selected", ExtentColor.ORANGE));

		WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.cancel)));
		cancel.click();

		Thread.sleep(2000);
		Assert.assertTrue(true);
	}

	//Add patient info after adding new location info

	@Test(priority = 10)
	public void addPatientfromLocation() throws InterruptedException {

		parentTest = extent.createTest("Add patient info after adding new location info").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Add patient info after adding new location info");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		LocationsFunctions.addResidentType();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Resident Type Selected", ExtentColor.ORANGE));

		String LName = LocationInfoData.locationName;
		String LlicenceNumber = LocationInfoData.licenceNumber;
		String Lcapacity = LocationInfoData.capacity;
		String Laddress = LocationInfoData.address;
		String LsuiteUnit = LocationInfoData.suiteUnit;
		String Lcity = LocationInfoData.city;
		String Lstate = LocationInfoData.state;
		String Lzip = LocationInfoData.zip;
		String LphoneNumber=LocationInfoData.phoneNumber;
		String Lemail=LocationInfoData.email;
		String Lnote=LocationInfoData.note;

		LocationsFunctions.addLocationData(LName, LlicenceNumber, Lcapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));
		save.click();
		Thread.sleep(5000);
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 4: Location added", ExtentColor.ORANGE));

		WebElement addButton1 = driver.findElement(By.xpath(LocationsXpath.add));
		addButton1.click();
		Thread.sleep(1000);
		WebElement addPatientButton = driver.findElement(By.xpath(LocationsXpath.addPatientButton));
		addPatientButton.click();
		Thread.sleep(1000);

		String PFirstName= LocationInfoData.patientfirstName;
		String PLastName=LocationInfoData.patientlastName;
		String Pssn=LocationInfoData.ssn;
		String PphoneNumber2= LocationInfoData.phone;
		String Pemail2=LocationInfoData.patientemail;

		LocationsFunctions.addPatient(PFirstName, PLastName, Pssn, PphoneNumber2, Pemail2);

		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.submit)));
		submit.click();
		Thread.sleep(5000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/patients/"));

	}

	//Add patient info after adding new location info -- Empty fields

	@Test(priority = 11)
	public void emptyPatientInfofromLocation() throws InterruptedException {
		parentTest = extent.createTest("Add patient info after adding new location info").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Empty fields");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		LocationsFunctions.addResidentType();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Resident Type Selected", ExtentColor.ORANGE));

		String LName = LocationInfoData.locationName;
		String LlicenceNumber = LocationInfoData.licenceNumber;
		String Lcapacity = LocationInfoData.capacity;
		String Laddress = LocationInfoData.address;
		String LsuiteUnit = LocationInfoData.suiteUnit;
		String Lcity = LocationInfoData.city;
		String Lstate = LocationInfoData.state;
		String Lzip = LocationInfoData.zip;
		String LphoneNumber=LocationInfoData.phoneNumber;
		String Lemail=LocationInfoData.email;
		String Lnote=LocationInfoData.note;

		LocationsFunctions.addLocationData(LName, LlicenceNumber, Lcapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));
		save.click();
		Thread.sleep(5000);
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 4: Location added", ExtentColor.ORANGE));

		WebElement addButton1 = driver.findElement(By.xpath("//i[normalize-space()='add']"));
		addButton1.click();
		Thread.sleep(1000);
		WebElement addPatientButton = driver.findElement(By.xpath("//strong[normalize-space()='Patient']"));
		addPatientButton.click();
		Thread.sleep(1000);


		String PFirstName= LocationInfoData.patientfirstName2;
		String PLastName=LocationInfoData.patientlastName2;
		String Pssn=LocationInfoData.ssn2;
		String PphoneNumber2= LocationInfoData.phone2;
		String Pemail2=LocationInfoData.patientemail2;

		LocationsFunctions.addPatient(PFirstName, PLastName, Pssn, PphoneNumber2, Pemail2);

		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.submit)));
		submit.isEnabled();
		Thread.sleep(5000);

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.firstNameMsg)));
		WebElement lastNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.lastNameMsg)));

		parentTest.log(Status.INFO, MarkupHelper.createLabel(firstNameMsg.getText(), ExtentColor.RED));
		parentTest.log(Status.INFO, MarkupHelper.createLabel(lastNameMsg.getText(), ExtentColor.RED));

		String expectedText4 = "First name is required";
		String actualText4 = firstNameMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		String expectedText5 = "Last name is required";
		String actualText5 = lastNameMsg.getText();
		Assert.assertEquals(actualText5, expectedText5);

		Thread.sleep(1000);

	}

	//Add contact info after adding new location info

	@Test(priority = 12)
	public void addContactfromLocation() throws InterruptedException {

		parentTest = extent.createTest("Add contact info after adding new location info").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Add contact info after adding new location info");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		LocationsFunctions.addResidentType();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Resident Type Selected", ExtentColor.ORANGE));

		String LName = LocationInfoData.locationName;
		String LlicenceNumber = LocationInfoData.licenceNumber;
		String Lcapacity = LocationInfoData.capacity;
		String Laddress = LocationInfoData.address;
		String LsuiteUnit = LocationInfoData.suiteUnit;
		String Lcity = LocationInfoData.city;
		String Lstate = LocationInfoData.state;
		String Lzip = LocationInfoData.zip;
		String LphoneNumber=LocationInfoData.phoneNumber;
		String Lemail=LocationInfoData.email;
		String Lnote=LocationInfoData.note;

		LocationsFunctions.addLocationData(LName, LlicenceNumber, Lcapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));
		save.click();
		Thread.sleep(5000);
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 4: Location added", ExtentColor.ORANGE));


		WebElement addButton1 = driver.findElement(By.xpath(LocationsXpath.add));
		addButton1.click();
		Thread.sleep(1000);
		WebElement addContactButton = driver.findElement(By.xpath(LocationsXpath.addContactButton));
		addContactButton.click();
		Thread.sleep(1000);

		WebElement FirstName = driver.findElement(By.xpath(LocationsXpath.FirstName2));
		FirstName.sendKeys(LocationInfoData.contactfirstName);
		WebElement phoneNumber2 = driver.findElement(By.xpath(LocationsXpath.phoneNumber2));
		phoneNumber2.sendKeys(LocationInfoData.contactPhone);
		WebElement email2 = driver.findElement(By.xpath(LocationsXpath.email));
		email2.sendKeys(LocationInfoData.contactEmail);
		WebElement save2 = driver.findElement(By.xpath(LocationsXpath.save));

		save2.click();
		Thread.sleep(5000);

		WebElement contactButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.contactButton)));
		contactButton.click();

		WebElement firstRow = driver.findElement(By.xpath(LocationsXpath.firstRow));
		String expectedText = LocationInfoData.contactfirstName;
		String actualText = firstRow.getText();
		Assert.assertEquals(actualText, expectedText);

		String expectedText1 = firstRow.getText();
		firstRow.click();
		WebElement firstRowInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.firstRowInfo)));
		String actualText1 = firstRowInfo.getText();
		Assert.assertEquals(actualText1, expectedText1);
		Thread.sleep(2000);

		WebElement closeInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.closeInfo)));
		closeInfo.click();

	}

	//Add contact info after adding new location info with empty fields

	@Test(priority = 13)
	public void addContactEmptyLocation() throws InterruptedException {
		parentTest = extent.createTest("Add contact info after adding new location info").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Empty fields");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));

		LocationsFunctions.addResidentType();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Resident Type Selected", ExtentColor.ORANGE));

		String LName = LocationInfoData.locationName;
		String LlicenceNumber = LocationInfoData.licenceNumber;
		String Lcapacity = LocationInfoData.capacity;
		String Laddress = LocationInfoData.address;
		String LsuiteUnit = LocationInfoData.suiteUnit;
		String Lcity = LocationInfoData.city;
		String Lstate = LocationInfoData.state;
		String Lzip = LocationInfoData.zip;
		String LphoneNumber=LocationInfoData.phoneNumber;
		String Lemail=LocationInfoData.email;
		String Lnote=LocationInfoData.note;

		LocationsFunctions.addLocationData(LName, LlicenceNumber, Lcapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));
		save.click();
		Thread.sleep(5000);
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 4: Location added", ExtentColor.ORANGE));


		WebElement addButton1 = driver.findElement(By.xpath(LocationsXpath.add));
		addButton1.click();
		Thread.sleep(1000);
		WebElement addContactButton = driver.findElement(By.xpath(LocationsXpath.addContactButton));
		addContactButton.click();
		Thread.sleep(1000);

		WebElement FirstName = driver.findElement(By.xpath(LocationsXpath.FirstName2));
		FirstName.sendKeys(LocationInfoData.contactfirstName2);
		WebElement phoneNumber2 = driver.findElement(By.xpath(LocationsXpath.phoneNumber2));
		phoneNumber2.sendKeys(LocationInfoData.contactPhone2);
		WebElement email2 = driver.findElement(By.xpath(LocationsXpath.email));
		email2.sendKeys(LocationInfoData.contactEmail2);
		WebElement save2 = driver.findElement(By.xpath(LocationsXpath.save));

		save2.isEnabled();
		Thread.sleep(5000);

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.firstNameMsg2)));
		String expectedText4 = "First name is required";
		String actualText4 = firstNameMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

	}

	//Edit location info

	@Test(priority = 14)
	public void editLocationInfo() throws InterruptedException {
		parentTest = extent.createTest("Edit location info").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Edit location info");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		LocationsFunctions.viewLocationList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Location List", ExtentColor.ORANGE));
		Thread.sleep(3000);

		WebElement locationSelect = driver.findElement(By.xpath(LocationsXpath.firstRow));
		locationSelect.click();
		Thread.sleep(2000);

		WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.editButton)));
		editButton.click();

		WebElement editOptionSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.editOptionSelect)));
		editOptionSelect.click();

		WebElement editLicenceNumber = driver.findElement(By.xpath(LocationsXpath.editLicenceNumber));
		editLicenceNumber.clear();
		Thread.sleep(1000);

		while (!editLicenceNumber.getAttribute("value").equals("")) {
			editLicenceNumber.sendKeys(Keys.BACK_SPACE);
		}

		editLicenceNumber.sendKeys(LocationInfoData.editLicenceNumber);

		WebElement editCapacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.editCapacity)));
		editCapacity.clear();
		Thread.sleep(1000);

		while (!editCapacity.getAttribute("value").equals("")) {
			editCapacity.sendKeys(Keys.BACK_SPACE);
		}
		editCapacity.sendKeys(LocationInfoData.editCapacity);

		WebElement update = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.update)));
		update.click();
		Thread.sleep(3000);

		Assert.assertTrue(true);

	}


	//Add location From patient overview page

	@Test
	public void addLocationFromPatient() throws InterruptedException {
		parentTest = extent.createTest("Add location From patient overview page").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Add location From patient overview page");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		LocationsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		driver.findElement(By.xpath(LocationsXpath.patients)).click();

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.add2)));
		add.click();
		WebElement selectAddPatient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.selectAddPatient)));
		selectAddPatient.click();

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.FirstName)));
		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.LastName)));
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.submit)));

		firstName.sendKeys(PatientsInfoData.patientfirstName);
		lastName.sendKeys(PatientsInfoData.patientlastName);
		save.click();

		WebElement addNewLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.addNewLocation)));
		addNewLocation.click();		
		WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.addButton)));
		addButton.click();
		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.selectType)));
		selectType.click();
		WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.continueButton)));
		continueButton.click();

		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.locationName)));
		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.licenceNumber)));
		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.capacity)));
		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.address)));
		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.suiteUnit)));
		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.city)));
		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.state)));
		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.zip)));
		WebElement save2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));

		locationName.sendKeys(LocationInfoData.locationName);
		licenceNumber.sendKeys(LocationInfoData.licenceNumber);
		capacity.sendKeys(LocationInfoData.capacity);
		address.sendKeys(LocationInfoData.address);
		suiteUnit.sendKeys(LocationInfoData.suiteUnit);
		city.sendKeys(LocationInfoData.city);
		state.sendKeys(LocationInfoData.state);
		zip.sendKeys(LocationInfoData.zip);
		save2.click();
		Thread.sleep(5000);

		Assert.assertTrue(true);

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
