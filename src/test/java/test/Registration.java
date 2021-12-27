package test;
import static org.testng.Assert.assertFalse;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Registration {

	//Opening browser with the given URL and navigate to Registration Page

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 1 -- Registration";


	public static RemoteWebDriver driver = null;
	@Parameters("myBrowser")

	@BeforeTest
	//public static void setup(String myBrowser) throws MalformedURLException {
	public static void setup(String myBrowser) throws MalformedURLException {	
//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setBrowserName("chrome");
//		caps.setPlatform(Platform.WINDOWS);
//		ChromeOptions options = new ChromeOptions();
//		options.merge(caps);
//		String nodeUrl = "http://192.168.31.17:4444/wd/hub";
//		driver = new RemoteWebDriver(new URL(nodeUrl),options);

				if(myBrowser.equalsIgnoreCase("chrome")){
					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setBrowserName("chrome");
					caps.setPlatform(Platform.WINDOWS);
					ChromeOptions options = new ChromeOptions();
					options.merge(caps);
					String nodeUrl = "http://192.168.31.17:4444/wd/hub";
					driver = new RemoteWebDriver(new URL(nodeUrl),options);
		
				}
		
				if(myBrowser.equalsIgnoreCase("firefox")) {
					//System.setProperty("webdriver.gecko.driver","C:\\Users\\tahni\\eclipse-workspace\\geckodriver.exe");
					DesiredCapabilities caps = new DesiredCapabilities();
					//driver = new FirefoxDriver();
					caps.setPlatform(Platform.WINDOWS);
					FirefoxOptions options = new FirefoxOptions();
					options.merge(caps);
					String nodeUrl = "http://192.168.31.17:4444/wd/hub";
					driver = new RemoteWebDriver(new URL(nodeUrl),options);
		
				}

	}

	@BeforeSuite
	public static void beforeSuit() {

		if (env.equalsIgnoreCase("Test")) {

			System.out.println("Test executes in correct environment where environment= " + env);
			System.out.println("Test Suite name = " + testSuiteName);

		}else {
			System.out.println("Test executes in wrong environment where environment= " + env);

		}
	}

	@BeforeMethod
	public void openBrowser() throws InterruptedException {

		driver.manage().deleteAllCookies();

		driver.get("https://zntral.net/session/login");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement signUpButton = driver.findElement(By.xpath("//span[normalize-space()='Create Account']"));
		signUpButton.click(); 
		Thread.sleep(3000);
	}

	//Verifying elements on Registration page

	@Test(priority = 1)
	public void verifyElemntsOnPageTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signUpTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='mb-3']")));
		signUpTitle.isDisplayed();

	}

	// Registration with all valid data

	@Test(priority = 2)
	public void validRegistrationTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div/div/div/div/div/form[@novalidate='novalidate']/div[1]/div[1]/div[1]/div[1]/input[1]")));
		firstName.sendKeys("Test");

		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div//div//div//div[2]//div[1]//div[1]//div[1]//input[1]")));
		LastName.sendKeys("Site");

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[1]//div[1]//input[1]")));
		email.sendKeys("test2221@gmail.com");

		WebElement verifiedemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[1]//div[1]//div[1]//input[1]")));
		verifiedemail.sendKeys("test2221@gmail.com");

		WebElement checkmark = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input theme--light v-input--selection-controls v-input--radio-group v-input--radio-group--column']//div[2]//div[1]//div[1]")));
		checkmark.click();
		Thread.sleep(20000);

		WebElement termsOfServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[7]//div[1]//div[1]//div[1]//div[1]")));
		termsOfServices.click();
		Thread.sleep(1000);

		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='v-btn v-btn--contained theme--light v-size--default primary']")));
		signUp.click();
		Thread.sleep(10000);

		String expectedUrl = "https://zntral.net/session/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);


	}

	// Registration - First Name field validation

	@Test(priority = 3)
	public void firstNameValidationTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div/div/div/div/div/form[@novalidate='novalidate']/div[1]/div[1]/div[1]/div[1]/input[1]")));
		firstName.sendKeys("fgggfggfgfgfgfgfgfgfgfgfg");

		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div//div//div//div[2]//div[1]//div[1]//div[1]//input[1]")));
		LastName.sendKeys("Site");

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[1]//div[1]//input[1]")));
		email.sendKeys("test2221@gmail.com");

		WebElement verifiedemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[1]//div[1]//div[1]//input[1]")));
		verifiedemail.sendKeys("test2221@gmail.com");

		WebElement checkmark = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input theme--light v-input--selection-controls v-input--radio-group v-input--radio-group--column']//div[2]//div[1]//div[1]")));
		checkmark.click();
		Thread.sleep(20000);

		WebElement termsOfServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[7]//div[1]//div[1]//div[1]//div[1]")));
		termsOfServices.click();
		Thread.sleep(1000);

		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Continue']")));

		try {
			signUp.click();
			Thread.sleep(5000);
			String expectedUrl = "https://zntral.net/session/login";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl);
		}
		catch(Exception e) {

			if(firstName.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath("//div[@class='v-messages__message']"));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);

			}
			else if(firstName.getAttribute("value").length() > 20) {
				WebElement userField = driver.findElement(By.xpath("//div[@role='alert']"));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else {

				WebElement userField = driver.findElement(By.xpath("//div[@role='alert']"));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
				String expectedURL = "https://zntral.net/session/sign-up";
				String actualURL = driver.getCurrentUrl();
				Assert.assertEquals(actualURL, expectedURL);
			}
		}

	}

	// Registration-- Last Name field validation

	@Test(priority = 4)
	public void lastNameValidationTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div/div/div/div/div/form[@novalidate='novalidate']/div[1]/div[1]/div[1]/div[1]/input[1]")));
		firstName.sendKeys("TEst");

		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div//div//div//div[2]//div[1]//div[1]//div[1]//input[1]")));
		LastName.sendKeys("fhfghdfghgdhfhfghfhfhggh");

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[1]//div[1]//input[1]")));
		email.sendKeys("test2221@gmail.com");

		WebElement verifiedemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[1]//div[1]//div[1]//input[1]")));
		verifiedemail.sendKeys("test2221@gmail.com");

		WebElement checkmark = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input theme--light v-input--selection-controls v-input--radio-group v-input--radio-group--column']//div[2]//div[1]//div[1]")));
		checkmark.click();
		Thread.sleep(20000);

		WebElement termsOfServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[7]//div[1]//div[1]//div[1]//div[1]")));
		termsOfServices.click();
		Thread.sleep(1000);

		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Continue']")));
		Thread.sleep(2000);

		try {
			signUp.click();
			Thread.sleep(5000);
			String expectedUrl = "https://zntral.net/session/login";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl);
		}
		catch(Exception e) {

			if(LastName.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath("//div[@class='v-messages__message']"));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);

			}
			else if(LastName.getAttribute("value").length() > 20) {
				WebElement userField = driver.findElement(By.xpath("//div[@role='alert']"));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else {

				WebElement userField = driver.findElement(By.xpath("//div[@role='alert']"));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
				String expectedURL = "https://zntral.net/session/sign-up";
				String actualURL = driver.getCurrentUrl();
				Assert.assertEquals(actualURL, expectedURL);
			}
		}


	}

	// Registration -- user email field validation

	@Test(priority = 5)
	public void emailValidationTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div/div/div/div/div/form[@novalidate='novalidate']/div[1]/div[1]/div[1]/div[1]/input[1]")));
		firstName.sendKeys("TEst");

		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div//div//div//div[2]//div[1]//div[1]//div[1]//input[1]")));
		LastName.sendKeys("testttt");

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[1]//div[1]//input[1]")));
		email.sendKeys("test24221@gmail.com");

		WebElement verifiedemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[1]//div[1]//div[1]//input[1]")));
		verifiedemail.sendKeys("test2221@gmail.com");

		WebElement checkmark = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input theme--light v-input--selection-controls v-input--radio-group v-input--radio-group--column']//div[2]//div[1]//div[1]")));
		checkmark.click();
		Thread.sleep(20000);

		WebElement termsOfServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[7]//div[1]//div[1]//div[1]//div[1]")));
		termsOfServices.click();
		Thread.sleep(1000);

		WebElement signUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Continue']")));
		Thread.sleep(2000);

		try {
			signUp.click();
			Thread.sleep(5000);
			String expectedUrl = "https://zntral.net/session/login";
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedUrl);
		}
		catch(Exception e) {

			if(email.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath("//div[@class='v-messages__message']"));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);

			}
			else if(email.getAttribute("value").startsWith("@")) {
				WebElement userField = driver.findElement(By.xpath("//div[@role='alert']"));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else if(email.getAttribute("value") != verifiedemail.getAttribute("value")) {
				WebElement userField = driver.findElement(By.xpath("//div[@role='alert']"));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
			}
			else {

				WebElement userField = driver.findElement(By.xpath("//div[@role='alert']"));
				System.out.println(userField.getText());
				signUp.isDisplayed();
				Assert.assertTrue(true);
				Thread.sleep(2000);
				String expectedURL = "https://zntral.net/session/sign-up";
				String actualURL = driver.getCurrentUrl();
				Assert.assertEquals(actualURL, expectedURL);
			}
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}

	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}
}
