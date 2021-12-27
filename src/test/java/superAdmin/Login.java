package superAdmin;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

public class Login{

	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 1 -- Login";

	public static RemoteWebDriver driver = null;
	@Parameters("myBrowser")

	@BeforeTest
	public static void loginsetup(String myBrowser) throws MalformedURLException {

//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setBrowserName("chrome");
//		caps.setPlatform(Platform.WINDOWS);
//		ChromeOptions options = new ChromeOptions();
//		options.merge(caps);
//		String nodeUrl = "http://192.168.31.17:4444/wd/hub";
//		driver = new RemoteWebDriver(new URL(nodeUrl),options);
//
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

		if (env.equalsIgnoreCase("Test for Super Admin")) {

			System.out.println("Test executes in correct environment where environment= " + env);
			System.out.println("Test Suite name = " + testSuiteName);

		}else {
			System.out.println("Test executes in wrong environment where environment= " + env);

		}
	}

	//Opening browser with the given URL and navigate to Registration Page

	@BeforeMethod
	public void openBrowser() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.get("https://d1.zntral.net/session/login");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Thread.sleep(3000);
	}

	//Verifying elements on Login page
	@Test
	public void verifyElemntsOnPageTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='mb-3']")));
		signInTitle.isDisplayed();

	}

	//login with valid username & Password

	@Test
	public void verifyLogin() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//button[1]")));

		username.sendKeys("superadmin@mercury.com");
		password.sendKeys("qwerty");

		login.click();
		Thread.sleep(5000);

		String expectedUrl = "https://d1.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//login without username

	@Test
	public void loginWithoutUsername() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//button[1]")));

		username.sendKeys("");
		password.sendKeys("Sabbir33");

		try {
			login.click();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			if(username.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath("//div[@class='v-messages__message']"));
				System.out.println(userField.getText());
				login.isDisplayed();
				String expectedUrl = "https://d1.zntral.net/session/login";
				String actualUrl = driver.getCurrentUrl();
				Assert.assertEquals(actualUrl, expectedUrl);
				Assert.assertTrue(true);

			}
		}

	}

	//login without Password

	@Test
	public void loginWithoutPassword() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//button[1]")));

		WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input--selection-controls__ripple']")));

		username.sendKeys("test@gmil.com");
		password.sendKeys("");

		checkbox.click();

		try {
			login.click();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			if(password.getAttribute("value").isEmpty()) {
				WebElement userField = driver.findElement(By.xpath("//div[@class='v-messages__message']"));
				System.out.println(userField.getText());
				login.isDisplayed();

				String expectedUrl = "https://d1.zntral.net/session/login";
				String actualUrl = driver.getCurrentUrl();
				Assert.assertEquals(actualUrl, expectedUrl);
				Assert.assertTrue(true);

			}
		}

	}


	//login with invalid info

	@Test
	public void invalidLoginInfo() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//button[1]")));

		username.sendKeys("superadmin@mercury.com");
		password.sendKeys("afvfd");

		login.click();
		Thread.sleep(5000);
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='status']")));
		System.out.println(error.getText());

		String expectedUrl = "https://d1.zntral.net/session/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		Assert.assertTrue(true);

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
