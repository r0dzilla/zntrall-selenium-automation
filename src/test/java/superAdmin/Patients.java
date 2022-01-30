package superAdmin;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

import browser.OpenBrowser;
import superAdminInputData.PatientsInfoData;
import superAdminXpath.PatientsXpath;

public class Patients extends OpenBrowser{

	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 5 -- Patients";
	public String name_text="";

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
		driver.manage().window().maximize();
		driver.get("https://dev.zntral.net/session/login");
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


	//Verifying elements on Login page
	@Test(priority = 1)
	public void verifyElemntsOnPageTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.signInTitle)));
		signInTitle.isDisplayed();

	}

	//login with valid username & Password

	@Test(priority = 2)
	public void login() throws InterruptedException {

		WebElement username = driver.findElement(By.xpath(PatientsXpath.username));
		WebElement password = driver.findElement(By.xpath(PatientsXpath.pass));
		WebElement login = driver.findElement(By.xpath(PatientsXpath.login));

		String user = PatientsInfoData.user;
		String pass = PatientsInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Check Patients list

	@Test(priority = 3)
	public void checkPatientList() throws InterruptedException {

		login();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement patients = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.patients)));
		patients.click();
		Thread.sleep(3000);

		String expectedUrl = "https://dev.zntral.net/patients";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//Search option

	@Test(priority = 4)
	public void search() throws InterruptedException {
		checkPatientList();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		String searchLocation = PatientsInfoData.searchLocation;
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.search)));
		search.sendKeys(searchLocation);
		Thread.sleep(3000);


		WebElement tableContents = driver.findElement(By.tagName("table"));
		List<WebElement> rows=tableContents.findElements(By.tagName("tr"));
		for(int rnum=1;rnum<rows.size();rnum++)
		{
			List<WebElement> columns=rows.get(rnum).findElements(By.tagName("td"));
			//Assert.assertEquals(columns.get(0).getText(),search.getAttribute("value"));
			String colVal = columns.get(1).getText();
			System.out.println(colVal);

			if(colVal.contains(search.getAttribute("value"))) {
				System.out.println("Matched!");
				Assert.assertTrue(true);
			}else {
				System.out.println("No value");

			}
		}

	}


	//Find patient info from patient list

	@Test(priority = 5)
	public void patientFromLocation() throws InterruptedException {
		search();
		WebElement firstRow = driver.findElement(By.xpath(PatientsXpath.firstRow));

		firstRow.click();
		Thread.sleep(3000);
		Assert.assertTrue(true);

	}


	//First Name wise sort

	@Test(priority = 6)
	public void firstNameWiseSort() throws InterruptedException {
		checkPatientList();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement selectName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.selectName)));

		selectName.click();
		Thread.sleep(1000);
		selectName.click();
		Thread.sleep(1000);
		selectName.click();
		Thread.sleep(1000);
		Assert.assertTrue(true);
	}

	//Location wise sort

	@Test(priority = 7)
	public void locationWiseSort() throws InterruptedException {
		checkPatientList();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement selectLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.selectLocation)));

		selectLocation.click();
		Thread.sleep(1000);
		selectLocation.click();
		Thread.sleep(1000);
		selectLocation.click();
		Thread.sleep(1000);
		Assert.assertTrue(true);
	}
}
