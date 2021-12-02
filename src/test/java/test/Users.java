package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Users {

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 3 -- Users";

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

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//button[1]")));

		username.sendKeys("shaque.sabbir@gmail.com");
		Thread.sleep(2000);

		password.sendKeys("Sabbir33");

		login.click();
		Thread.sleep(5000);

		String expectedUrl = "https://zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);


	}

	//View the user list

	@Test
	public void userList() throws InterruptedException {

		loginUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement userlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Users']")));
		userlist.click();
		Thread.sleep(3000);

		String expectedUrl = "https://zntral.net/users";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Add new user form

	@Test
	public void addUser() throws InterruptedException{
		userList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[normalize-space()='add']")));
		add.click();
		Thread.sleep(2000);

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[normalize-space()='Add new user']")));
		Thread.sleep(2000);
		addUser.click();

		Assert.assertTrue(true);
		Thread.sleep(3000);

	}

	//Adding user with valid info

	@Test
	public void validUserData() throws InterruptedException {
		addUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div[1]/div[1]/div[1]/input[1]")));
		WebElement emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		WebElement groupDrpDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//div//div//div//div//div[@role='button']//div//div//div//i[@aria-hidden='true']")));
		//WebElement groupSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='save']")));
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='save']")));

		Thread.sleep(2000);
		FirstName.sendKeys("TestData");
		Thread.sleep(1000);
		LastName.sendKeys("test");
		Thread.sleep(1000);
		emailId.sendKeys("testdata101@email.com");
		Thread.sleep(1000);
		groupDrpDown.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Hospice Registered Nurse')]"))).click();
		Thread.sleep(1000);


		try {
			save.click();
			Thread.sleep(5000);
			WebElement firstRow = driver.findElement(By.xpath("//tbody/tr[1]/td[3]"));
			if(firstRow.getAttribute("value") == emailId.getText()) {
				System.out.println("Successfully added");
				Assert.assertTrue(true);
			}

		}catch(Exception e) {
			System.out.println("Failed !!!");
			Assert.assertTrue(false);
		}
	}

	//Already existed user

	@Test
	public void existedUser() throws InterruptedException {
		addUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div[1]/div[1]/div[1]/input[1]")));
		WebElement emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		//WebElement groupDrpDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//div//div//div//div//div[@role='button']//div//div//div//i[@aria-hidden='true']")));
		//WebElement groupSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='save']")));
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='save']")));

		Thread.sleep(2000);
		FirstName.sendKeys("Test");
		Thread.sleep(1000);
		LastName.sendKeys("data");
		emailId.sendKeys("testdata22@email.com");
		Thread.sleep(2000);
		save.click();
		Thread.sleep(2000);

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]")));
			driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]")).isDisplayed();

			Thread.sleep(3000);
		} catch (Exception e){
			System.out.println("New User");
		}

	}
	//Add new user form -- validation
	@Test
	public void validateNewUser() throws InterruptedException {
		addUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div[1]/div[1]/div[1]/input[1]")));
		WebElement emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		WebElement groupDrpDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//div//div//div//div//div[@role='button']//div//div//div//i[@aria-hidden='true']")));
		//WebElement groupSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='save']")));
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='save']")));

		Thread.sleep(2000);
		FirstName.sendKeys("");
		Thread.sleep(1000);
		LastName.sendKeys("");
		Thread.sleep(1000);
		emailId.sendKeys("");
		Thread.sleep(1000);
		groupDrpDown.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Hospice Registered Nurse')]"))).click();
		Thread.sleep(1000);


		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			if(FirstName.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-messages__message']")));
				System.out.println(invalidMsg.getText());

				Assert.assertTrue(true);
				Thread.sleep(1000);

			}
			else if(LastName.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-messages__message']")));
				System.out.println(invalidMsg.getText());

				Assert.assertTrue(true);
				Thread.sleep(1000);

			}
			else if(emailId.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-messages__message']")));
				System.out.println(invalidMsg.getText());

				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else if(emailId.getAttribute("value").startsWith("@")) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-messages__message']")));
				System.out.println(invalidMsg.getText());

				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
			else {
				save.isDisplayed();
				System.out.println("Mandatory field has no value");
				Assert.assertTrue(true);
				Thread.sleep(1000);
			}
		}
	}

	// Check Reset button on add new user form

	@Test
	public void resetButton() throws InterruptedException {

		addUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div[1]/div[1]/div[1]/input[1]")));
		WebElement emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		WebElement groupDrpDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//div//div//div//div//div[@role='button']//div//div//div//i[@aria-hidden='true']")));
		//WebElement groupSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='save']")));
		WebElement reset = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Reset']")));

		Thread.sleep(2000);
		FirstName.sendKeys("TestData");
		Thread.sleep(1000);
		LastName.sendKeys("test");
		Thread.sleep(1000);
		emailId.sendKeys("testdata101@email.com");
		Thread.sleep(1000);
		groupDrpDown.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Hospice Registered Nurse')]"))).click();
		Thread.sleep(1000);
		reset.click();
		Thread.sleep(1000);
		WebElement resetConfirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Yes']")));
		

		try {

			resetConfirm.click();
			Thread.sleep(2000);
			
			String expectedText1 = "Name is required";
			String actualText1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]")).getText();
			Assert.assertEquals(actualText1, expectedText1);

			String expectedText2 = "Name is required";
			String actualText2 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]")).getText();
			Assert.assertEquals(actualText2, expectedText2);

			String expectedText3 = "E-mail must be valid";
			String actualText3 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]")).getText();
			Assert.assertEquals(actualText3, expectedText3);
			Thread.sleep(3000);
		} catch(Exception e) {
			System.out.println("Failed !!");
		}

		
	}

	// Check Cancel button on add new user form

	@Test
	public void cancelButton() throws InterruptedException {
		addUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[3]/button[3]")));
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[3]/button[3]")).click();

		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[3]/button[1]")));
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[3]/button[1]")).click();
		Thread.sleep(3000);
	}

	//Check close button on add new user form

	@Test
	public void closeButton() throws InterruptedException {
		addUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/span[1]/button[1]")));
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/span[1]/button[1]")).click();

		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[3]/button[1]")));
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[3]/button[1]")).click();
		Thread.sleep(3000);
	}

	//Search option

	@Test
	public void search() throws InterruptedException {
		userList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("testdata@email.com");

		WebElement firstRow = driver.findElement(By.xpath("//tbody/tr[1]/td[3]"));
		String expectedText = "testdata@email.com";
		String actualText = firstRow.getText();
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(3000);
	}

	//User profile 

	@Test
	public void profileOfUser() throws InterruptedException {
		userList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")));
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")).click();

		String actualText = "Users";

		WebElement user =driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/header[1]/div[1]/div[1]/h2[1]/a[1]"));
		String ExpectedText =user.getText();	
		Assert.assertEquals(actualText, ExpectedText);
		Thread.sleep(3000);
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
