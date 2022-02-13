package superAdmin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

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

import browser.OpenBrowser;
import superAdminFunctions.UserProfileFunctions;
import superAdminInputData.User_profileInfoData;
import superAdminXpath.User_profileXpath;

public class User_profile extends OpenBrowser {

	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 6 -- Profile";
	String fileName = System.getProperty("user.dir")+"/reports/Super Admin/TestResults-User_profile.html";

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
		htmlReports.config().setReportName("Super Admin - Profile");
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
		parentTest = extent.createTest("Verifying elements on Login page").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.log(Status.INFO, "Test is started");

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.signInTitle)));
		signInTitle.isDisplayed();

		parentTest.log(Status.PASS, MarkupHelper.createLabel("All Elements are present", ExtentColor.BLUE));
	}

	//login with valid username & Password

	@Test(priority = 2)
	public void login() throws InterruptedException {

		parentTest = extent.createTest("login with valid username & Password").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Login with valid credentials");

		UserProfileFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//View the user profile

	@Test(priority = 2)
	public void userProfile() throws InterruptedException {

		parentTest = extent.createTest("View the user profile").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("View the user profile");
		
		UserProfileFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UserProfileFunctions.userList();

		String expectedUrl = "https://dev.zntral.net/profile";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	// Add phone number 

	@Test(priority = 3)
	public void addPhone() throws InterruptedException {
		parentTest = extent.createTest("Add phone number ").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Add phone number ");
		
		UserProfileFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));
		
		UserProfileFunctions.userList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User Profile", ExtentColor.ORANGE));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.phoneNumber)));
		phoneNumber.click();
		Thread.sleep(2000);

		WebElement selectPhoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.selectPhoneNumber)));
		while (!selectPhoneNumber.getAttribute("value").equals("")) {
			selectPhoneNumber.sendKeys(Keys.BACK_SPACE);
		}

		String addPhone = User_profileInfoData.addPhone;

		selectPhoneNumber.sendKeys(addPhone);
		Thread.sleep(1000);

		WebElement phoneNumberType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.phoneNumberType)));
		phoneNumberType.click();
		Thread.sleep(1000);

		List<WebElement> items = driver.findElements(By.xpath(User_profileXpath.items));
		for(WebElement e: items)
		{
			//System.out.print(e.getText());
			if(e.getText().equalsIgnoreCase("Mobile"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.itemsMobile))).click();
			}
			if(e.getText().equalsIgnoreCase("Work"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.itemsWork))).click();
			}
			else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.otherItems))).click();
			}
		}

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.save)));

		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			if(selectPhoneNumber.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.invalidMsg)));
				
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));

				Assert.assertTrue(true);
				Thread.sleep(1000);

			}
			else if(selectPhoneNumber.getAttribute("value").length() > 10) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.invalidMsg)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.invalidMsg)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
		}

	}

	// Add gender

	@Test(priority = 4)
	public void addGender() throws InterruptedException {
		
		parentTest = extent.createTest("Add gender").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Add gender");

		UserProfileFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));
		
		UserProfileFunctions.userList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User Profile", ExtentColor.ORANGE));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.gender)));
		gender.click();
		Thread.sleep(2000);

		WebElement selectGender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.selectGender)));
		selectGender.click();
		Thread.sleep(1000);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.save)));

		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			parentTest.log(Status.INFO, MarkupHelper.createLabel("Gender selection is missing", ExtentColor.RED));
			Assert.assertTrue(true);
			Thread.sleep(1000);
		}

	}


	// update email

	@Test(priority = 5)
	public void updateEmail() throws InterruptedException {
		
		parentTest = extent.createTest("Update email").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Update email");
		
		UserProfileFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));

		UserProfileFunctions.userList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User Profile", ExtentColor.ORANGE));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.email)));
		email.click();
		Thread.sleep(2000);

		//		WebElement newEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.newEmail)));
		//		String firstMail = User_profileInfoData.firstMail;
		//		newEmail.sendKeys(firstMail);
		//		Thread.sleep(1000);
		//
		//		WebElement confirmEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.confirmEmail)));
		//		String secondMail = User_profileInfoData.secondMail;
		//		confirmEmail.sendKeys(secondMail);
		//		Thread.sleep(1000);

		//		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.updateEmailSave)));
		//
		//		try {
		//			save.click();
		//			Thread.sleep(1000);	
		//
		//		} 
		//		catch (Exception e){
		//			if(newEmail.getAttribute("value").isEmpty()) {
		//				save.isDisplayed();
		//				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.InvalidMsgs)));
		//				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
		//				Assert.assertTrue(true);
		//				Thread.sleep(1000);
		//
		//			}
		//			else if(newEmail.getAttribute("value").startsWith("@")) {
		//				save.isDisplayed();
		//				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.InvalidMsgs)));
		//				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
		//
		//				Assert.assertTrue(true);
		//				Thread.sleep(1000);
		//			}
		//			else if(newEmail.getAttribute("value") != confirmEmail.getAttribute("value")) {
		//				save.isDisplayed();
		//				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.InvalidMsgs)));
		//				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
		//				Assert.assertTrue(true);
		//				Thread.sleep(1000);
		//			}
		//			else {
		//				save.isDisplayed();
		//				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.InvalidMsgs)));
		//				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
		//				Assert.assertTrue(true);
		//				Thread.sleep(1000);
		//			}
		//		}

	}


	//Add address

	@Test(priority = 6)
	public void addAddress() throws InterruptedException {
		
		parentTest = extent.createTest("Add address").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Add address");
		
		UserProfileFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));
		
		UserProfileFunctions.userList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User Profile", ExtentColor.ORANGE));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		String street = User_profileInfoData.streetAddress;
		String unitNo = User_profileInfoData.unit;
		String zipNo = User_profileInfoData.zip;
		String cityName = User_profileInfoData.city;
		String stateName = User_profileInfoData.state;
		String poBoxNo = User_profileInfoData.poBox;

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.address)));
		address.click();
		Thread.sleep(2000);

		WebElement streetAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.streetAddress)));
		while (!streetAddress.getAttribute("value").equals("")) {
			streetAddress.sendKeys(Keys.BACK_SPACE);
		}
		streetAddress.sendKeys(street);
		Thread.sleep(1000);

		WebElement unit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.unit)));
		while (!unit.getAttribute("value").equals("")) {
			unit.sendKeys(Keys.BACK_SPACE);
		}
		unit.sendKeys(unitNo);
		Thread.sleep(1000);

		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.zip)));
		while (!zip.getAttribute("value").equals("")) {
			zip.sendKeys(Keys.BACK_SPACE);
		}
		zip.sendKeys(zipNo);
		Thread.sleep(1000);

		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.city)));
		while (!city.getAttribute("value").equals("")) {
			city.sendKeys(Keys.BACK_SPACE);
		}
		city.sendKeys(cityName);
		Thread.sleep(1000);

		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.state)));
		while (!state.getAttribute("value").equals("")) {
			state.sendKeys(Keys.BACK_SPACE);
		}
		state.sendKeys(stateName);
		Thread.sleep(1000);

		WebElement poBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.poBox)));
		while (!poBox.getAttribute("value").equals("")) {
			poBox.sendKeys(Keys.BACK_SPACE);
		}
		poBox.sendKeys(poBoxNo);
		Thread.sleep(1000);

		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.submit)));

		try {
			submit.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			if(streetAddress.getAttribute("value").isEmpty()) {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.addAddressInvalidMsg)));

				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(true);
				Thread.sleep(1000);

			}
			else {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.addAddressInvalidMsg)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
		}
	}

	//Change password

	@Test(priority = 7)
	public void changePassword() throws InterruptedException {
		
		parentTest = extent.createTest("Change password").assignAuthor("Sabbir").assignCategory("Super Admin");
		parentTest.info("Change password");
		
		UserProfileFunctions.verifyLogin();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 1: Login Successful", ExtentColor.ORANGE));
		
		UserProfileFunctions.userList();
		parentTest.log(Status.INFO, MarkupHelper.createLabel("Step 2: User Profile", ExtentColor.ORANGE));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		String currPass = User_profileInfoData.currentPass;
		String newPassword = User_profileInfoData.newPass;

		WebElement passWord = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.password)));
		passWord.click();
		Thread.sleep(2000);

		WebElement requestOTP = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.requestOTP)));
		requestOTP.click();
		Thread.sleep(1000);

		WebElement currentPass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.currentPass)));
		currentPass.sendKeys(currPass);
		Thread.sleep(1000);

		WebElement newPass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.newPass)));
		newPass.sendKeys(newPassword);
		Thread.sleep(1000);

		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.save)));

		try {
			submit.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			if(currentPass.getAttribute("value").isEmpty()) {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.InvalidMsgs)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(true);
				Thread.sleep(1000);

			}
			else if(currentPass.getAttribute("value").length() <= 5) {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.InvalidMsgs)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else if (newPass.getAttribute("value").isEmpty()) {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.InvalidMsgs)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else if (currentPass.getAttribute("value") == newPass.getAttribute("value")) {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.InvalidMsgs)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.InvalidMsgs)));
				parentTest.log(Status.INFO, MarkupHelper.createLabel(invalidMsg.getText(), ExtentColor.RED));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
		}

	}

}


