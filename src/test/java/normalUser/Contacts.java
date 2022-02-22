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

import NormalUserXpath.ContactsXpath;
import browser.OpenBrowser;
import normalUserFunctions.ContactsFunctions;
import normalUserInputData.ContactsInfoData;

public class Contacts extends OpenBrowser{
	public static String env = "Test";
	public static String testSuiteName = "Test Suit 7 -- Contacts";
	String fileName = System.getProperty("user.dir")+"/reports/Standard User/TestResults-Contacts.html";

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
		htmlReports.config().setReportName("Standart User - Contacts");
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

		ContactsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Login Successful", ExtentColor.GREEN));

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);


	}

	//View the contacts list

	@Test(priority = 2)
	public void contactList() throws InterruptedException {
		parentTest = extent.createTest("View the contacts list").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("View the contacts list");

		ContactsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ContactsFunctions.contactlist();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Contact List", ExtentColor.ORANGE));

		String expectedUrl = "https://dev.zntral.net/contacts";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Search option

	@Test(priority = 3)
	public void search() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		parentTest = extent.createTest("Search option").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Search option");

		ContactsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ContactsFunctions.contactlist();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Contact List", ExtentColor.ORANGE));

		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.search)));
		String searchUser = ContactsInfoData.search;
		search.sendKeys(searchUser);

		WebElement firstRow = driver.findElement(By.xpath(ContactsXpath.firstRow));	
		String actualText = firstRow.getText();
		String expectedText = searchUser;
		Assert.assertEquals(actualText.contains(expectedText), true);
		Thread.sleep(3000);
	}

	//Add new Contact -- Click "+" button

	@Test(priority = 4)
	public void addContact() throws InterruptedException{
		parentTest = extent.createTest("Add new Contact").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Add new Contact -- Click add button");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ContactsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ContactsFunctions.contactlist();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Contact List", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.add)));
		add.click();
		Thread.sleep(2000);
		Assert.assertTrue(true);
	}

	//Add contact with valid info

	@Test(priority = 5)
	public void validContactData() throws InterruptedException {

		parentTest = extent.createTest("Add contact with valid info").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Add contact with valid info");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ContactsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ContactsFunctions.contactlist();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Contact List", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.add)));
		add.click();
		Thread.sleep(2000);


		String first = ContactsInfoData.firstName;
		String last = ContactsInfoData.lastName;
		String phn = ContactsInfoData.phone;
		String mail = ContactsInfoData.email;
		String title2 = ContactsInfoData.title;
		String note2 = ContactsInfoData.note;

		ContactsFunctions.addContacts(first, last, phn, mail, title2, note2);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.save)));
		save.click();
		Thread.sleep(5000);

		WebElement firstRow = driver.findElement(By.xpath(ContactsXpath.firstRow));	
		String actualText = firstRow.getText();
		String expectedText = first;
		Assert.assertTrue(actualText.contains(expectedText));
		Thread.sleep(3000);

	}

	//Add contact with empty/invalid field

	@Test(priority = 6)
	public void emptyContactData() throws InterruptedException {
		parentTest = extent.createTest("Add contact with empty/invalid field").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Add contact with empty/invalid field");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ContactsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ContactsFunctions.contactlist();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Contact List", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.add)));
		add.click();
		Thread.sleep(2000);

		String first = ContactsInfoData.emptyfirstName;
		String last = ContactsInfoData.emptylastName;
		String phn = ContactsInfoData.emptyphone;
		String mail = ContactsInfoData.emptyemail;
		String title2 = ContactsInfoData.emptytitle;
		String note2 = ContactsInfoData.emptynote;

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.firstName)));
		firstName.sendKeys(first);

		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.lastName)));
		lastName.sendKeys(last);

		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.phone)));
		phone.sendKeys(phn);

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.email)));
		email.sendKeys(mail);

		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.title)));
		title.sendKeys(title2);

		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.note)));
		note.sendKeys(note2);	

		//First name field check
		if(firstName.getText().isEmpty()) {
			WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.firstNameMsg)));
			String expectedText4 = "First name is required";
			String actualText4 = firstNameMsg.getText();
			Assert.assertEquals(actualText4, expectedText4);
			parentTest.log(Status.INFO, MarkupHelper.createLabel(actualText4, ExtentColor.ORANGE));
			Thread.sleep(1000);
		}
		else {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("First Name submitted successfully!", ExtentColor.GREEN));
		}


		//Last name field check
		if(lastName.getText().isEmpty()) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Last Name field is empty!", ExtentColor.RED));

		}
		else {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Last Name submitted successfully!", ExtentColor.GREEN));

		}

		//Phone number field check		
		if(phone.getText().isEmpty()) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Phone number field is empty!", ExtentColor.RED));

		}
		else {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Phone number submitted successfully!", ExtentColor.GREEN));

		}

		//Email field check		
		if(email.getText().isEmpty()) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Email field is empty!", ExtentColor.RED));

		}
		else {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Data submitted successfully!", ExtentColor.GREEN));

		}

		Thread.sleep(3000);

		while (!email.getAttribute("value").equals("")) {
			email.sendKeys(Keys.BACK_SPACE);
		}

		String invalidmail = ContactsInfoData.invalidemail;
		email.sendKeys(invalidmail);

		note.sendKeys("");	

		//Title field check
		if(title.getText().isEmpty()) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Title field is empty!", ExtentColor.RED));
			Thread.sleep(1000);
		}
		else {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Data submitted successfully!", ExtentColor.GREEN));

		}


		//Note field check
		if(note.getText().isEmpty()) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Note field is empty!", ExtentColor.RED));

		}
		else {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Data submitted successfully!", ExtentColor.GREEN));

		}

		if(email.getAttribute("value").startsWith("@")){
			WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.invalidMsg)));
			parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
			Assert.assertTrue(true);
			Thread.sleep(1000);
		}else {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Email submitted successfully!", ExtentColor.GREEN));

		}

		//Save button check
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.save)));

		try {
			save.click();

		} 
		catch (Exception e){
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Mandatory fields has no data !", ExtentColor.RED));
			save.isEnabled();
			Thread.sleep(2000);
		}


	}


	//Edit contact from the list

	@Test(priority = 7)
	public void editContact() throws InterruptedException {
		parentTest = extent.createTest("Edit contact from the list").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Edit contact from the list");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ContactsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ContactsFunctions.contactlist();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Contact List", ExtentColor.ORANGE));


		String editphone = ContactsInfoData.editphone;
		String edittitle = ContactsInfoData.edittitle;

		WebElement dataForEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.dataForEdit)));
		dataForEdit.click();

		WebElement selectEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.selectEdit)));
		selectEdit.click();

		WebElement editPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.editPhone)));

		while (!editPhone.getAttribute("value").equals("")) {
			editPhone.sendKeys(Keys.BACK_SPACE);
		}
		editPhone.sendKeys(editphone);

		WebElement editTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.editTitle)));
		while (!editTitle.getAttribute("value").equals("")) {
			editTitle.sendKeys(Keys.BACK_SPACE);
		}

		editTitle.sendKeys(edittitle);
		Thread.sleep(3000);

		String expected = editphone;

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.save)));
		save.click();

		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.phoneNumber)));
		String actual = phoneNumber.getText();
		Thread.sleep(3000);

		try {
			Assert.assertEquals(actual, expected);

		} catch(Exception e) {
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Phone number don't match", ExtentColor.RED));
		}

	}

	//Remove any contact from the list

	@Test(priority = 8)
	public void removeContact() throws InterruptedException {

		parentTest = extent.createTest("Remove any contact from the list").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Remove any contact from the list");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ContactsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ContactsFunctions.contactlist();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Contact List", ExtentColor.ORANGE));


		WebElement dataForEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.dataForEdit)));
		dataForEdit.click();

		WebElement selectEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.selectEdit)));
		selectEdit.click();

		WebElement remove = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.remove)));
		remove.click();
		Thread.sleep(2000);

		driver.switchTo().alert().accept();

		Assert.assertTrue(true);
	}

	//cancel button from Add new contact form

	@Test(priority = 9)
	public void cancelAddNewForm() throws InterruptedException {
		parentTest = extent.createTest("Cancel button from Add new contact form").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("Cancel button from Add new contact form");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ContactsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ContactsFunctions.contactlist();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Contact List", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.add)));
		add.click();
		Thread.sleep(2000);

		WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.cancel)));
		cancel.click();
		Assert.assertTrue(true);
	}

	//close button from Add new contact form

	@Test(priority = 10)
	public void closeAddNewForm() throws InterruptedException {
		parentTest = extent.createTest("Close button from -- Add new contact form").assignAuthor("Sabbir").assignCategory("Standard User");
		parentTest.info("close button from -- Add new contact form");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ContactsFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		ContactsFunctions.contactlist();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: Contact List", ExtentColor.ORANGE));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.add)));
		add.click();
		Thread.sleep(2000);

		WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.close)));
		close.click();
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
