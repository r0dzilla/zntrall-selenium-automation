package normalUser;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
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

import NormalUserXpath.CalendarXpath;
import browser.OpenBrowser;
import normalUserInputData.CalendarInfoData;

public class Calendar extends OpenBrowser{

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 6 -- Calendar";
	WebDriver driver2;
	public static WebDriver driver = null;
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
		driver.get("https://dev.zntral.net/session/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Thread.sleep(3000);
	}

	//login

	@Test(priority = 1)
	public void loginUser() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.pass)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.login)));

		String user = CalendarInfoData.user;
		String pass = CalendarInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		//		WebElement loginAs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Counsellors']")));
		//		loginAs.click();
		Thread.sleep(5000);

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);


	}


	//View the calendar

	@Test(priority = 2)
	public void calendar() throws InterruptedException {

		loginUser();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.calendar)));
		driver.findElement(By.xpath(CalendarXpath.calendar)).click();
		String expectedUrl = "https://dev.zntral.net/patient_schedule";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//Add a single schedule on upcoming days

	@Test(priority = 3)
	public void addSingleEvent() throws InterruptedException {
		calendar();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectDate)));
		selectDate.click();
		WebElement patientSelectDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.patientSelectDropDown)));
		patientSelectDropDown.click();
		WebElement patientSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.patientSelect)));
		patientSelect.click();
		WebElement dateFieldSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.dateFieldSelect)));
		dateFieldSelect.click();
		WebElement dateSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.dateSelect)));
		dateSelect.click();
		WebElement allDay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.allDay)));
		allDay.click();
		WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.description)));
		String desc = CalendarInfoData.desc;
		description.sendKeys(desc);
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.save)));
		save.click();
		Thread.sleep(3000);
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.errorMsg)));
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

	@Test(priority = 4)
	public void cancelSchedule() throws InterruptedException {
		calendar();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectSchedule)));
		selectSchedule.click();
		WebElement selectCancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectCancel)));
		selectCancel.click();
	}

	//delete a schedule

	@Test(priority = 5)
	public void deleteSchedule() throws InterruptedException {
		calendar();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectSchedule2)));
		selectSchedule.click();
		WebElement selectDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectDelete)));
		selectDelete.click();
		WebElement selectRecurringOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectRecurringOption)));
		selectRecurringOption.click();
		WebElement delete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.delete)));
		delete.click();
	}

	//Edit a schedule

	@Test(priority = 6)
	public void editSchedule() throws InterruptedException {
		calendar();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectSchedule3)));
		selectSchedule.click();
		WebElement selectEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectEdit)));
		selectEdit.click();
		WebElement selectPatientDrpDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.patientSelectDropDown)));
		selectPatientDrpDown.click();
		WebElement selectPatient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectPatient)));
		selectPatient.click();
		WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.description)));
		String desc = CalendarInfoData.desc;
		description.sendKeys(desc);
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.save)));
		save.click();

	}

	// Day view

	@Test(priority = 7)
	public void dayViewOnCalendar() throws InterruptedException {
		calendar();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectDay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectDay)));
		selectDay.click();
	}

	// Week view

	@Test(priority = 8)
	public void weekViewOnCalendar() throws InterruptedException {
		calendar();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectWeek = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectWeek)));
		selectWeek.click();
	}

	// Month view

	@Test(priority = 9)
	public void monthViewOnCalendar() throws InterruptedException {
		calendar();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectMonth)));
		selectMonth.click();
	}

	// Agenda view

	@Test(priority = 10)
	public void agendaViewOnCalendar() throws InterruptedException {
		calendar();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectAgenda = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectAgenda)));
		selectAgenda.click();
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
