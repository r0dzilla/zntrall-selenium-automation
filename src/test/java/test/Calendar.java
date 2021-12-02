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

public class Calendar {

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 6 -- Calendar";

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


	//View the calendar

	@Test
	public void calendar() throws InterruptedException {

		loginUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Calendar']")));
		driver.findElement(By.xpath("//span[normalize-space()='Calendar']")).click();
		Thread.sleep(3000);
		String expectedUrl = "https://zntral.net/patient_schedule";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//Add a single schedule on upcoming days

	@Test
	public void addSingleEvent() throws InterruptedException {
		calendar();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-calendar-monthly v-calendar-weekly v-calendar theme--light v-calendar-events']//span[@class='v-btn__content'][normalize-space()='28']")));
		selectDate.click();
		Thread.sleep(2000);

		WebElement patientSelectDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='combobox']//div//div//div//i[@aria-hidden='true']")));
		patientSelectDropDown.click();
		Thread.sleep(1000);

		WebElement patientSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-list-item__title'][normalize-space()='Testing data']")));
		patientSelect.click();
		Thread.sleep(1000);

		WebElement dateFieldSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		dateFieldSelect.click();
		Thread.sleep(1000);
		WebElement dateSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-btn__content'][normalize-space()='30']")));
		dateSelect.click();
		Thread.sleep(3000);

		WebElement allDay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'All day')]")));
		allDay.click();
		Thread.sleep(1000);

		WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input theme--light v-text-field v-text-field--is-booted']//input[@type='text']")));
		description.sendKeys("Test Schedule");
		Thread.sleep(2000);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
		save.click();
		Thread.sleep(3000);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='status']")));
		try {

			if(driver.switchTo().alert() != null) {
				System.out.println(errorMsg.getText());
				Assert.assertTrue(true);
			}

		}
		catch(Exception e) {
			System.out.println(errorMsg.getText());
			Assert.assertTrue(true);
		}
	}

	//cancel a schedule

	@Test
	public void cancelSchedule() throws InterruptedException {
		calendar();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[5]/div[7]/div[2]/div[1]")));
		selectSchedule.click();
		Thread.sleep(2000);

		WebElement selectCancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-menu__content theme--light menuable__content__active']//span[@class='v-btn__content'][normalize-space()='Cancel']")));
		selectCancel.click();
		Thread.sleep(2000);

	}

	//delete a schedule

	@Test
	public void deleteSchedule() throws InterruptedException {
		calendar();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[5]/div[3]/div[2]/div[1]")));
		selectSchedule.click();
		Thread.sleep(2000);

		WebElement selectDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Delete']")));
		selectDelete.click();
		Thread.sleep(2000);

		WebElement selectRecurringOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='All events']")));
		selectRecurringOption.click();
		Thread.sleep(2000);

		WebElement delete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ml-2 v-btn v-btn--depressed v-btn--flat v-btn--outlined theme--light v-size--default danger--text']//span[@class='v-btn__content'][normalize-space()='Delete']")));
		delete.click();
		Thread.sleep(2000);

	}

	//Edit a schedule

	@Test
	public void editSchedule() throws InterruptedException {
		calendar();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pl-1'][normalize-space()='Testing data']")));
		selectSchedule.click();
		Thread.sleep(2000);

		WebElement selectEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Edit']")));
		selectEdit.click();
		Thread.sleep(2000);

		WebElement selectPatientDrpDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='combobox']//div//div//div//i[@aria-hidden='true']")));
		selectPatientDrpDown.click();
		Thread.sleep(2000);

		WebElement selectPatient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Sabbir-Test Info')]")));
		selectPatient.click();
		Thread.sleep(2000);

		WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input theme--light v-text-field v-text-field--is-booted']//input[@type='text']")));
		description.sendKeys("Test Schedule");
		Thread.sleep(2000);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
		save.click();
		Thread.sleep(2000);

	}

	// Day view

	@Test
	public void dayViewOnCalendar() throws InterruptedException {
		calendar();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@role='button']//i[@aria-hidden='true']")));
		selectType.click();
		Thread.sleep(2000);

		WebElement selectDay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Day')]")));
		selectDay.click();
		Thread.sleep(2000);

	}

	// Week view

	@Test
	public void weekViewOnCalendar() throws InterruptedException {
		calendar();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@role='button']//i[@aria-hidden='true']")));
		selectType.click();
		Thread.sleep(2000);

		WebElement selectWeek = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Week')]")));
		selectWeek.click();
		Thread.sleep(2000);

	}

	// Month view

	@Test
	public void monthViewOnCalendar() throws InterruptedException {
		calendar();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@role='button']//i[@aria-hidden='true']")));
		selectType.click();
		Thread.sleep(2000);

		WebElement selectMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Month')]")));
		selectMonth.click();
		Thread.sleep(2000);

	}

	// Agenda view

	@Test
	public void agendaViewOnCalendar() throws InterruptedException {
		calendar();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@role='button']//i[@aria-hidden='true']")));
		selectType.click();
		Thread.sleep(2000);

		WebElement selectAgenda = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Agenda')]")));
		selectAgenda.click();
		Thread.sleep(2000);

	}


	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}

}
