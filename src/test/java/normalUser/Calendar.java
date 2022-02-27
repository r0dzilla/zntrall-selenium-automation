package normalUser;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
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
import org.testng.annotations.Test;

import NormalUserXpath.CalendarXpath;
import browser.OpenBrowser;
import normalUserFunctions.CalendarFunctions;
import normalUserInputData.CalendarInfoData;

public class Calendar extends OpenBrowser{

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 6 -- Calendar";
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

	public static String myBrowser = "chrome";
	
	@BeforeTest
	public void setup() throws MalformedURLException {

		driver = start(myBrowser);

	}

	@AfterTest
	public void tearDown() throws Exception {

		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}



	//Opening browser with the given URL and navigate to Registration Page

	@BeforeMethod
	public void openBrowser() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.get("https://dev.zntral.net/session/login");
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}



	//login

	@Test(priority = 1)
	public void loginUser() throws InterruptedException {


		CalendarFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

	}


	//View the calendar

	@Test(priority = 2)
	public void calendar() throws InterruptedException {

		CalendarFunctions.verifyLogin();

		CalendarFunctions.calendarList();

		String expectedUrl = "https://dev.zntral.net/patient_schedule";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//Add a single schedule on upcoming days

	@Test(priority = 3)
	public void addSingleEvent() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();

		CalendarFunctions.calendarList();

		WebElement selectDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectDate)));
		selectDate.click();
		WebElement patientSelectDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.patientSelectDropDown)));
		patientSelectDropDown.click();
		WebElement patientSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.patientSelect)));
		patientSelect.click();
		//		WebElement dateFieldSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.dateFieldSelect)));
		//		dateFieldSelect.click();
		//		WebElement dateSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.dateSelect)));
		//		dateSelect.click();
		WebElement allDay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.allDay)));
		allDay.click();
		WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.description)));
		String desc = CalendarInfoData.desc;
		description.sendKeys(desc);
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.save)));
		save.click();
		Thread.sleep(3000);

		try {
			WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.errorMsg)));
			if(driver.switchTo().alert() != null) {
				Assert.assertTrue(false);
			}

		}
		catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	//cancel a schedule

	@Test(priority = 4)
	public void cancelSchedule() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();

		CalendarFunctions.calendarList();

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectSchedule)));
		selectSchedule.click();
		WebElement selectThreeDotOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectThreeDotMenu)));
		selectThreeDotOption.click();
		WebElement selectCancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectCancel)));
		selectCancel.click();
		Assert.assertTrue(true);

	}

	//delete a schedule

	@Test(priority = 5)
	public void deleteSchedule() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();

		CalendarFunctions.calendarList();

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectSchedule)));
		selectSchedule.click();
		WebElement selectThreeDotOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectThreeDotMenu)));
		selectThreeDotOption.click();
		WebElement selectDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectDelete)));
		selectDelete.click();
		WebElement selectRecurringOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectRecurringOption)));
		selectRecurringOption.click();
		WebElement delete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.delete)));
		delete.click();
		Assert.assertTrue(true);
	}

	//Edit a schedule

	@Test(priority = 6)
	public void editSchedule() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();

		CalendarFunctions.calendarList();

		WebElement selectSchedule = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectSchedule)));
		selectSchedule.click();
		WebElement selectThreeDotOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectThreeDotMenu)));
		selectThreeDotOption.click();
		WebElement selectEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectEdit)));
		selectEdit.click();
		Thread.sleep(3000);
		WebElement selectPatientDrpDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.patientSelectDropDown)));
		selectPatientDrpDown.click();
		WebElement selectPatient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectPatient)));
		selectPatient.click();
		WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.description)));
		String desc = CalendarInfoData.desc;
		description.sendKeys(desc);
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.save)));
		save.click();
		Thread.sleep(3000);
	}

	// Day view

	@Test(priority = 7)
	public void dayViewOnCalendar() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();

		CalendarFunctions.calendarList();

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectDay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectDay)));
		selectDay.click();
	}

	// Week view

	@Test(priority = 8)
	public void weekViewOnCalendar() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();

		CalendarFunctions.calendarList();

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectWeek = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectWeek)));
		selectWeek.click();
	}

	// Month view

	@Test(priority = 9)
	public void monthViewOnCalendar() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();

		CalendarFunctions.calendarList();

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectMonth)));
		selectMonth.click();
	}

	// Agenda view

	@Test(priority = 10)
	public void agendaViewOnCalendar() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		CalendarFunctions.verifyLogin();

		CalendarFunctions.calendarList();

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectType)));
		selectType.click();
		WebElement selectAgenda = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.selectAgenda)));
		selectAgenda.click();
	}


	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}

}
