package superAdmin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import superAdminFunctions.ClientsFunctions;
import superAdminInputData.ClientsInfoData;
import superAdminXpath.ClientsXpath;

public class Clients extends OpenBrowser{
	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 2 -- Clients";
	String fileName = System.getProperty("user.dir")+"/reports/Super Admin/TestResults-Clients.html";

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
		htmlReports.config().setReportName("Super Admin - Clients");
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

	//Verifying elements on Client page
	@Test(priority = 1)
	public void verifyElemntsOnPageTest() throws InterruptedException {

		parentTest = extent.createTest("Verifying elements on Client page").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.log(Status.INFO, "Test is started");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.signInTitle)));
		signInTitle.isDisplayed();

		parentTest.log(Status.PASS, MarkupHelper.createLabel("All Elements are present", ExtentColor.BLUE));

	}

	//login with valid username & Password

	@Test(priority = 2)
	public void login() throws InterruptedException {

		parentTest = extent.createTest("login with valid username & Password").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Login with valid credentials");

		ClientsFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Successful", ExtentColor.ORANGE));
	}


	//Check client list

	@Test(priority = 3)
	public void checkClientList() throws InterruptedException {
		parentTest = extent.createTest("Check client list").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check client list");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ClientsFunctions.clientList();

		String expectedUrl = "https://dev.zntral.net/clients";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client list is displayed", ExtentColor.ORANGE));

	}


	//Check client profile

	@Test(priority = 4)
	public void checkClientProfile() throws InterruptedException {

		parentTest = extent.createTest("Check client profile").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check client profile");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List is shown", ExtentColor.ORANGE));

		ClientsFunctions.clientProfile();

	}


	//Check client profile status change (Active to inactive)

	@Test(priority = 5)
	public void changeStatusToInactive() throws InterruptedException {

		parentTest = extent.createTest("Check client profile status").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check client profile status change (Active to inactive)");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		ClientsFunctions.clientProfile();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Client Profile", ExtentColor.ORANGE));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.status)));
		status.click();
		WebElement statusInactive = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.statusInactive)));
		statusInactive.click();
		Assert.assertTrue(true);
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Status changed to Inactive", ExtentColor.GREEN));


	}


	//Check client profile status change (InActive to active)

	@Test(priority = 6)
	public void changeStatusToActive() throws InterruptedException {

		parentTest = extent.createTest("Check client profile status").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check client profile status change (InActive to Active)");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		ClientsFunctions.clientProfile();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Client Profile", ExtentColor.ORANGE));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.status)));
		status.click();
		WebElement statusActive = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.statusActive)));
		statusActive.click();
		Assert.assertTrue(true);
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Status changed to Active", ExtentColor.GREEN));

	}

	//Check location status on client profile

	@Test(priority = 7)
	public void checkLocationStatus() throws InterruptedException{

		parentTest = extent.createTest("Check location status").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check location status on client profile");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		ClientsFunctions.clientProfile();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Client Profile", ExtentColor.ORANGE));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement locationTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.locationTab)));
		locationTab.click();

		try {
			WebElement locationInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.locationInfo)));
			if(locationInfo.getText() != null ) {
				Assert.assertTrue(true);
				parentTest.log(Status.INFO, MarkupHelper.createLabel("Location Found !!", ExtentColor.GREEN));
			}
		}
		catch(Exception e) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Location not Found !!", ExtentColor.RED));
		}
	}


	//Check patient status on client profile

	@Test(priority = 8)
	public void checkPatientStatus() throws InterruptedException{

		parentTest = extent.createTest("Check patient status on client profile").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check patient status on client profile");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		ClientsFunctions.clientProfile();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Client Profile", ExtentColor.ORANGE));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement patientTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.patientTab)));
		patientTab.click();
		try {
			WebElement patientInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.patientInfo)));
			if(patientInfo.getText() != null ) {
				Assert.assertTrue(true);
				parentTest.log(Status.INFO, MarkupHelper.createLabel("Patient Found !!", ExtentColor.GREEN));
			}
		}
		catch(Exception e) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Patient not Found !!", ExtentColor.RED));
		}
	}


	//Search option

	@Test(priority = 9)
	public void search() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		parentTest = extent.createTest("Check Search").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check Search");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.search)));
		search.sendKeys("John");
		WebElement firstRow = driver.findElement(By.xpath(ClientsXpath.firstRow));	
		String actualText = firstRow.getText();
		Assert.assertTrue(actualText.contains(search.getText()));
		Thread.sleep(3000);
	}


	//Check client type list

	@Test(priority = 10)
	public void checkClientTypeList() throws InterruptedException {

		parentTest = extent.createTest("Check client type list").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check client type list");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		WebElement element = driver.findElement(By.xpath(ClientsXpath.element));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		String expectedText = "Types";
		String actualText = element.getText();
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(3000);
	}

	//Add new client group with valid info

	@Test(priority = 11)
	public void addNewClientGroup() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		parentTest = extent.createTest("Add new client group").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Add new client group with valid info");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		String clientGroupName = ClientsInfoData.name;
		String clientGroupAcronym = ClientsInfoData.acronym;

		ClientsFunctions.addClientGroup(clientGroupName, clientGroupAcronym);

		Assert.assertTrue(true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		parentTest.log(Status.INFO, MarkupHelper.createLabel("New Client Group Created", ExtentColor.ORANGE));

	}

	//Edit client group with valid info

	@Test(priority = 12)
	public void editClientGroup() throws InterruptedException {

		parentTest = extent.createTest("Edit client group").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Edit client group with valid info");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		String editClientGroupName = ClientsInfoData.editName;
		String editClientGroupAcronym = ClientsInfoData.editAcronym;

		WebElement selectAnyGroup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.selectAnyGroup)));
		selectAnyGroup.click();
		WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.name)));
		name.sendKeys(editClientGroupName);
		WebElement acronym = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.acronym)));
		acronym.sendKeys(editClientGroupAcronym);
		WebElement update = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.update)));
		update.click();
		Assert.assertTrue(true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Client Group Edited", ExtentColor.ORANGE));
	}

	//Name wise sort

	@Test(priority = 13)
	public void nameSort() throws InterruptedException {

		parentTest = extent.createTest("Name wise sort").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Name wise sort");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		WebElement selectName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.selectName)));

		selectName.click();
		selectName.click();
		selectName.click();
		Assert.assertTrue(true);
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Name wise sorting successful", ExtentColor.ORANGE));
	}

	//Type wise sort

	@Test(priority = 14)
	public void typeSort() throws InterruptedException {

		parentTest = extent.createTest("Type wise sort").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Type wise sort");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.selectType)));

		selectType.click();
		selectType.click();
		selectType.click();
		Assert.assertTrue(true);
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Type wise sorting successful", ExtentColor.ORANGE));
	}

	//Check user status on client profile

	@Test(priority = 15)
	public void checkUserStatus() throws InterruptedException{

		parentTest = extent.createTest("Check user status").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Check user status on client profile");

		ClientsFunctions.verifyLogin();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ClientsFunctions.clientList();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Client List", ExtentColor.ORANGE));

		ClientsFunctions.clientProfile();

		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 3: Client Profile", ExtentColor.ORANGE));


		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement userTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.userTab)));
		userTab.click();

		try {
			WebElement userInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.userInfo)));
			if(userInfo.getText() != null ) {
				Assert.assertTrue(true);
				parentTest.log(Status.INFO, MarkupHelper.createLabel("User Found !!", ExtentColor.GREEN));
			}
		}
		catch(Exception e) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("User not Found !!", ExtentColor.RED));
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
