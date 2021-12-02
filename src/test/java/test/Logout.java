package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Logout {

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 10 -- Logout";

	public static RemoteWebDriver driver = null;
	WebDriver driver2;
	@Parameters("myBrowser")

	@BeforeTest
	public static void setup() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName("chrome");
		caps.setPlatform(Platform.WINDOWS);
		ChromeOptions options = new ChromeOptions();
		options.merge(caps);
		String nodeUrl = "http://192.168.31.17:4444/wd/hub";
		driver = new RemoteWebDriver(new URL(nodeUrl),options);

		//		if(myBrowser.equalsIgnoreCase("chrome")){
		//			DesiredCapabilities caps = new DesiredCapabilities();
		//			caps.setBrowserName("chrome");
		//			caps.setPlatform(Platform.WINDOWS);
		//			ChromeOptions options = new ChromeOptions();
		//			options.merge(caps);
		//			String nodeUrl = "http://192.168.31.17:4444/wd/hub";
		//			driver = new RemoteWebDriver(new URL(nodeUrl),options);
		//			
		//		}
		//
		//		if(myBrowser.equalsIgnoreCase("firefox")) {
		//			//System.setProperty("webdriver.gecko.driver","C:\\Users\\tahni\\eclipse-workspace\\geckodriver.exe");
		//			DesiredCapabilities caps = new DesiredCapabilities();
		//			//driver = new FirefoxDriver();
		//			caps.setPlatform(Platform.WINDOWS);
		//			FirefoxOptions options = new FirefoxOptions();
		//			options.merge(caps);
		//			String nodeUrl = "http://192.168.31.17:4444/wd/hub";
		//			driver = new RemoteWebDriver(new URL(nodeUrl),options);
		//		
		//		}

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Thread.sleep(3000);
	}

	//login

	@Test
	public void loginUser() throws InterruptedException {

		WebElement username = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		WebElement password = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/input[1]"));
		WebElement login = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[4]/button[1]/span[1]"));

		username.sendKeys("shaque.sabbir@gmail.com");
		Thread.sleep(2000);

		password.sendKeys("Sabbir33");

		login.click();
		Thread.sleep(5000);

		String expectedUrl = "https://zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	
	//logout
	
	@Test
	public void logout() throws InterruptedException {
		loginUser();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement threeDotMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='v-icon notranslate material-icons theme--dark']")));
		threeDotMenu.click();
		Thread.sleep(2000);

		WebElement clickLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Log Out']")));
		clickLogout.click();
		Thread.sleep(3000);
		
		String expectedUrl = "https://zntral.net/session/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}
}
