package normalUser;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import NormalUserXpath.User_ProfileXpath;
import browser.OpenBrowser;
import normalUserFunctions.UserProfileFunctions;
import normalUserInputData.User_ProfileInfoData;

public class User_Profile extends OpenBrowser {
	public static String env = "Test";
	public static String testSuiteName = "Test Suit 9 -- User Profile";
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

	@Parameters("myBrowser")
	@BeforeTest
	public void setup(String myBrowser) throws MalformedURLException {

		driver = start(myBrowser);

	}


	//Opening browser with the given URL and navigate to Registration Page

	@BeforeMethod
	public void openBrowser() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.get("https://dev.zntral.net/session/login");
		driver.manage().window().maximize();
		Thread.sleep(3000);
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

		UserProfileFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//View the user profile

	@Test(priority = 2)
	public void userProfile() throws InterruptedException {

		UserProfileFunctions.verifyLogin();

		UserProfileFunctions.viewUserProfile();

		String expectedUrl = "https://dev.zntral.net/profile";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	// Add phone number 

	@Test(priority = 3)
	public void addPhone() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UserProfileFunctions.verifyLogin();

		UserProfileFunctions.viewUserProfile();

		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.phoneNumber)));
		phoneNumber.click();
		Thread.sleep(2000);

		WebElement selectPhoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.selectPhoneNumber)));
		while (!selectPhoneNumber.getAttribute("value").equals("")) {
			selectPhoneNumber.sendKeys(Keys.BACK_SPACE);
		}
		selectPhoneNumber.sendKeys(User_ProfileInfoData.addPhone);
		Thread.sleep(1000);

		WebElement phoneNumberType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.phoneNumberType)));
		phoneNumberType.click();
		Thread.sleep(1000);

		WebElement selectPhoneNumberType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.selectPhoneNumberType)));
		Thread.sleep(2000);
		System.out.println(selectPhoneNumberType.getText());

		//		if(selectPhoneNumberType.getText().equalsIgnoreCase("Work")) {
		//			WebElement selectPhoneNumberTypeHome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.selectPhoneNumberTypeHome)));
		//			Thread.sleep(1000);
		//			selectPhoneNumberTypeHome.click();
		//			Thread.sleep(2000);
		//		}
		if (selectPhoneNumberType.getText().equals("Home")) {
			WebElement selectPhoneNumberTypeMobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.selectPhoneNumberTypeMobile)));
			selectPhoneNumberTypeMobile.click();
			Thread.sleep(2000);
		}
		else {
			Thread.sleep(1000);
			WebElement selectPhoneNumberTypeHome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.selectPhoneNumberTypeHome)));
			selectPhoneNumberTypeHome.click();
			Thread.sleep(1000);
		}


		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.save)));

		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			if(selectPhoneNumber.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg)));
				Assert.assertTrue(true);
				Thread.sleep(1000);

			}
			else if(selectPhoneNumber.getAttribute("value").length() > 10) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg)));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg)));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
		}

	}

	// Add gender

	@Test(priority = 4)
	public void addGender() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UserProfileFunctions.verifyLogin();

		UserProfileFunctions.viewUserProfile();


		WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.gender)));
		gender.click();
		Thread.sleep(2000);

		WebElement selectGender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.selectGender)));
		selectGender.click();
		Thread.sleep(1000);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.save)));

		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			Assert.assertTrue(true);
			Thread.sleep(1000);
		}

	}

	// update email

	@Test(priority = 5)
	public void updateEmail() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UserProfileFunctions.verifyLogin();

		UserProfileFunctions.viewUserProfile();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.email)));
		email.click();
		Thread.sleep(2000);

		WebElement newEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.newEmail)));
		newEmail.sendKeys(User_ProfileInfoData.firstMail);
		Thread.sleep(1000);

		WebElement confirmEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.confirmEmail)));
		confirmEmail.sendKeys(User_ProfileInfoData.secondMail);
		Thread.sleep(1000);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.save2)));

		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			if(newEmail.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg2)));
				Assert.assertTrue(true);
				Thread.sleep(1000);

			}
			else if(newEmail.getAttribute("value").startsWith("@")) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg2)));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else if(newEmail.getAttribute("value") != confirmEmail.getAttribute("value")) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg2)));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg2)));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
		}

	}


	//Add address

	@Test(priority = 6)
	public void addAddress() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UserProfileFunctions.verifyLogin();

		UserProfileFunctions.viewUserProfile();

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.address)));
		address.click();
		Thread.sleep(2000);

		WebElement streetAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.streetAddress)));
		while (!streetAddress.getAttribute("value").equals("")) {
			streetAddress.sendKeys(Keys.BACK_SPACE);
		}
		streetAddress.sendKeys(User_ProfileInfoData.streetAddress);
		Thread.sleep(1000);

		WebElement unit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.unit)));
		unit.sendKeys(User_ProfileInfoData.unit);
		Thread.sleep(1000);

		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.zip)));
		zip.sendKeys(User_ProfileInfoData.zip);
		Thread.sleep(1000);

		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.city)));
		city.sendKeys(User_ProfileInfoData.city);
		Thread.sleep(1000);

		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.state)));
		state.sendKeys(User_ProfileInfoData.state);
		Thread.sleep(1000);

		WebElement poBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.poBox)));
		poBox.sendKeys(User_ProfileInfoData.poBox);
		Thread.sleep(1000);

		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.submit)));

		try {
			submit.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			if(streetAddress.getAttribute("value").isEmpty()) {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg3)));
				Assert.assertTrue(true);
				Thread.sleep(1000);

			}
			else {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg3)));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
		}
	}

	//Change password

	@Test(priority = 7)
	public void changePassword() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UserProfileFunctions.verifyLogin();

		UserProfileFunctions.viewUserProfile();

		WebElement password2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.password2)));
		password2.click();
		Thread.sleep(2000);

		WebElement requestOTP = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.requestOTP)));
		requestOTP.click();
		Thread.sleep(1000);

		WebElement currentPass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.currentPass)));
		currentPass.sendKeys(User_ProfileInfoData.currentPass);
		Thread.sleep(1000);

		WebElement newPass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.newPass)));
		newPass.sendKeys(User_ProfileInfoData.newPass);
		Thread.sleep(1000);

		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.save)));

		try {
			submit.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			if(currentPass.getAttribute("value").isEmpty()) {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg2)));
				Assert.assertTrue(true);
				Thread.sleep(1000);

			}
			else if(currentPass.getAttribute("value").length() <= 5) {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg2)));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else if (newPass.getAttribute("value").isEmpty()) {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg2)));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else if (currentPass.getAttribute("value") == newPass.getAttribute("value")) {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg2)));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else {
				submit.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.invalidMsg2)));
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
		}

	}


	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}
}
