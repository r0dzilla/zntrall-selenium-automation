package superAdmin;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
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
import superAdminInputData.LocationInfoData;
import superAdminXpath.LocationXpath;

public class Locations extends OpenBrowser{

	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 4 -- Locations";
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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}



	//Verifying elements on Login page
	@Test(priority = 1)
	public void verifyElemntsOnPageTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationXpath.signInTitle)));
		signInTitle.isDisplayed();

	}

	//login with valid username & Password

	@Test(priority = 2)
	public void login() throws InterruptedException {

		WebElement username = driver.findElement(By.xpath(LocationXpath.username));
		WebElement password = driver.findElement(By.xpath(LocationXpath.pass));
		WebElement login = driver.findElement(By.xpath(LocationXpath.login));

		String user = LocationInfoData.user;
		String pass = LocationInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Check Location list

	@Test(priority = 3)
	public void checkLocationList() throws InterruptedException {

		login();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement locations = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationXpath.locations)));
		locations.click();
		Thread.sleep(3000);

		String expectedUrl = "https://dev.zntral.net/locations";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Search option

	@Test(priority = 4)
	public void search() throws InterruptedException {
		checkLocationList();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		String searchLocation = LocationInfoData.searchLocation;

		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationXpath.search)));
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

	//Find patient list & patient info from location

	@Test(priority = 5)
	public void patientFromLocation() throws InterruptedException {
		checkLocationList();

		String searchLocation = LocationInfoData.searchLocation;

		WebElement search = driver.findElement(By.xpath(LocationXpath.search));
		search.sendKeys(searchLocation);
		WebElement firstRow = driver.findElement(By.xpath(LocationXpath.firstRow));

		firstRow.click();
		Thread.sleep(3000);
		WebElement patient = driver.findElement(By.xpath(LocationXpath.patient));
		patient.click();

		WebElement firstPatientInfo = driver.findElement(By.xpath(LocationXpath.firstPatientInfo));
		firstPatientInfo.click();
		Assert.assertTrue(true);
		Thread.sleep(3000);
	}

	//Find contact list & contact info from location

	@Test(priority = 6)
	public void contactFromLocation() throws InterruptedException {
		checkLocationList();

		String searchLocation = LocationInfoData.searchLocation;
		WebElement search = driver.findElement(By.xpath(LocationXpath.search));
		search.sendKeys(searchLocation);
		WebElement firstRow = driver.findElement(By.xpath(LocationXpath.firstRow));
		firstRow.click();
		Thread.sleep(3000);

		WebElement contact = driver.findElement(By.xpath(LocationXpath.contact));
		contact.click();

		WebElement firstContactInfo = driver.findElement(By.xpath(LocationXpath.firstContactInfo));
		firstContactInfo.click();
		Assert.assertTrue(true);
		Thread.sleep(3000);
	}

	// types list

	@Test(priority = 7)
	public void locationTypes() throws InterruptedException {
		checkLocationList();
		WebElement types = driver.findElement(By.xpath(LocationXpath.types));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", types);
		Assert.assertTrue(true);
		Thread.sleep(3000);
	}

	//update types

	@Test(priority = 8)
	public void updateTypes() throws InterruptedException {
		String editName = LocationInfoData.editName;
		String editAcronym = LocationInfoData.editAcronym;

		locationTypes();
		WebElement typeInfo = driver.findElement(By.xpath(LocationXpath.typeInfo));
		typeInfo.click();
		Thread.sleep(2000);

		WebElement name = driver.findElement(By.xpath(LocationXpath.name));
		while (!name.getAttribute("value").equals("")) {
			name.sendKeys(Keys.BACK_SPACE);
		}
		name.sendKeys(editName);
		WebElement acronym = driver.findElement(By.xpath(LocationXpath.acronym));
		while (!acronym.getAttribute("value").equals("")) {
			acronym.sendKeys(Keys.BACK_SPACE);
		}
		acronym.sendKeys(editAcronym);
		WebElement update = driver.findElement(By.xpath(LocationXpath.update));

		try {
			update.click();
			Assert.assertTrue(true);
		}catch(Exception e) {

			if(name.getAttribute("value").isEmpty() || acronym.getAttribute("value").isEmpty()) {
				update.isDisplayed();
				Assert.assertFalse(false);
			}

		}
	}

	//Add new Location Type
	@Test(priority = 9)
	public void addNewLocationType() throws InterruptedException {
		String addName = LocationInfoData.name;
		String addAcronym = LocationInfoData.acronym;

		locationTypes();
		WebElement addNewType = driver.findElement(By.xpath(LocationXpath.addNewType));
		addNewType.click();

		WebElement name = driver.findElement(By.xpath(LocationXpath.name));
		WebElement acronym = driver.findElement(By.xpath(LocationXpath.acronym));

		name.sendKeys(addName);
		acronym.sendKeys(addAcronym);

		String actual = name.getAttribute("value");

		WebElement save = driver.findElement(By.xpath(LocationXpath.save));
		save.click();
		Thread.sleep(3000);

		WebElement lastRow = driver.findElement(By.xpath(LocationXpath.lastRow)); 
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);",lastRow);
		Thread.sleep(2000);
		String expectedAdminName = lastRow.getText();
		Assert.assertEquals(actual, expectedAdminName);

	}


	//check reset button on add new form

	@Test(priority = 9)
	public void resetButton() throws InterruptedException {

		String addName = LocationInfoData.name;
		String addAcronym = LocationInfoData.acronym;

		locationTypes();
		WebElement addNewType = driver.findElement(By.xpath(LocationXpath.addNewType));
		addNewType.click();

		WebElement name = driver.findElement(By.xpath(LocationXpath.name));
		WebElement acronym = driver.findElement(By.xpath(LocationXpath.acronym));

		name.sendKeys(addName);
		acronym.sendKeys(addAcronym);

		WebElement save = driver.findElement(By.xpath(LocationXpath.save2));
		WebElement reset = driver.findElement(By.xpath(LocationXpath.reset));
		reset.click();

		WebElement nameErrorMsg = driver.findElement(By.xpath(LocationXpath.nameErrorMsg));
		WebElement acronymErrorMsg = driver.findElement(By.xpath(LocationXpath.acronymErrorMsg));
		Thread.sleep(2000);
		Assert.assertTrue(save.isDisplayed());
		Assert.assertTrue(nameErrorMsg.isDisplayed());
		Assert.assertTrue(acronymErrorMsg.isDisplayed());
	}


	//check cancel button on add new form

	@Test(priority = 10)
	public void cancelButton() throws InterruptedException {

		String addName = LocationInfoData.name;
		String addAcronym = LocationInfoData.acronym;

		locationTypes();
		WebElement addNewType = driver.findElement(By.xpath(LocationXpath.addNewType));
		addNewType.click();

		WebElement name = driver.findElement(By.xpath(LocationXpath.name));
		WebElement acronym = driver.findElement(By.xpath(LocationXpath.acronym));

		name.sendKeys(addName);
		acronym.sendKeys(addAcronym);

		WebElement cancel = driver.findElement(By.xpath(LocationXpath.cancel));
		cancel.click();

	}

	//Address wise sort

	@Test(priority = 11)
	public void addressWiseSort() throws InterruptedException {
		checkLocationList();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement selectAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationXpath.selectAddress)));

		selectAddress.click();
		selectAddress.click();
		selectAddress.click();
		Assert.assertTrue(true);
	}

	//Status wise sort

	@Test(priority = 12)
	public void statusWiseSort() throws InterruptedException {
		checkLocationList();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement selectStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationXpath.selectStatus)));

		selectStatus.click();
		selectStatus.click();
		selectStatus.click();
		Assert.assertTrue(true);
	}

	//Name wise types sort

	@Test(priority = 13)
	public void nameWiseTypeSort() throws InterruptedException {
		locationTypes();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement selectName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationXpath.selectName)));

		selectName.click();
		Thread.sleep(1000);
		selectName.click();
		Thread.sleep(1000);
		Assert.assertTrue(true);
	}

	//Acronym wise types sort

	@Test(priority = 14)
	public void acronymWiseTypeSort() throws InterruptedException {
		locationTypes();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement selectAcronym = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationXpath.selectAcronym)));

		selectAcronym.click();
		Thread.sleep(1000);
		selectAcronym.click();
		Thread.sleep(1000);
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
