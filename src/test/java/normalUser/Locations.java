package normalUser;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

import NormalUserXpath.LocationsXpath;
import browser.OpenBrowser;
import normalUserInputData.LocationInfoData;
import normalUserInputData.PatientsInfoData;

public class Locations extends OpenBrowser{
	public static String env = "Test";
	public static String testSuiteName = "Test Suit 4 -- Locations";
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
		WebElement username = driver.findElement(By.xpath(LocationsXpath.username));
		WebElement password = driver.findElement(By.xpath(LocationsXpath.pass));
		WebElement login = driver.findElement(By.xpath(LocationsXpath.login));

		String user = LocationInfoData.user;
		String pass = LocationInfoData.pass;

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


	//View the location list

	@Test(priority = 2)
	public void locationList() throws InterruptedException {

		loginUser();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.locations)));
		driver.findElement(By.xpath(LocationsXpath.locations)).click();
		String expectedUrl = "https://dev.zntral.net/locations";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Search option

	@Test(priority = 3)
	public void search() throws InterruptedException {
		locationList();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.search)));
		driver.findElement(By.xpath(LocationsXpath.search)).sendKeys(LocationInfoData.searchLocation);

		WebElement firstRow = driver.findElement(By.xpath(LocationsXpath.firstRow));
		String expectedText = LocationInfoData.searchLocation;
		String actualText = firstRow.getText();
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(3000);
	}

	//Add new Location -- Click "+" button

	@Test(priority = 4)
	public void addLocation() throws InterruptedException{
		loginUser();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.locations)));
		driver.findElement(By.xpath(LocationsXpath.locations)).click();

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.add)));
		add.click();

		WebElement selectResidentType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.selectResidentType)));
		selectResidentType.click();
		Thread.sleep(2000);
		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.selectContinue)));
		selectContinue.click();

		Assert.assertTrue(selectResidentType.isEnabled());

	}

	//Adding location with valid info

	@Test(priority = 5)
	public void validLocationData() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.locationName)));
		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.licenceNumber)));
		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.capacity)));
		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.address)));
		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.suiteUnit)));
		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.city)));
		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.state)));
		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.zip)));
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneNumber)));
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.type)));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.email)));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.note)));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));

		locationName.sendKeys(LocationInfoData.locationName);
		licenceNumber.sendKeys(LocationInfoData.licenceNumber);
		capacity.sendKeys(LocationInfoData.capacity);
		address.sendKeys(LocationInfoData.address);
		suiteUnit.sendKeys(LocationInfoData.suiteUnit);
		city.sendKeys(LocationInfoData.city);
		state.sendKeys(LocationInfoData.state);
		zip.sendKeys(LocationInfoData.zip);
		phoneNumber.sendKeys(LocationInfoData.phoneNumber);
		type.click();
		//selectType.click();
		email.sendKeys(LocationInfoData.email);
		note.sendKeys(LocationInfoData.note);
		save.click();
		Thread.sleep(5000);


		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/locations/"));

	}

	//Adding location without any info

	@Test(priority = 6)
	public void invalidLocationData() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.locationName)));
		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.licenceNumber)));
		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.capacity)));
		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.address)));
		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.suiteUnit)));
		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.city)));
		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.state)));
		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.zip)));
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneNumber)));
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.type)));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.email)));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.note)));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));

		locationName.sendKeys(LocationInfoData.locationName2);
		licenceNumber.sendKeys(LocationInfoData.licenceNumber2);
		capacity.sendKeys(LocationInfoData.capacity2);
		address.sendKeys(LocationInfoData.address2);
		suiteUnit.sendKeys(LocationInfoData.suiteUnit2);
		city.sendKeys(LocationInfoData.city2);
		state.sendKeys(LocationInfoData.state2);
		zip.sendKeys(LocationInfoData.zip2);
		phoneNumber.sendKeys(LocationInfoData.phoneNumber2);
		type.click();
		email.sendKeys(LocationInfoData.email2);
		note.sendKeys(LocationInfoData.note2);
		save.isEnabled();
		Thread.sleep(5000);

		WebElement addressMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.addressMsg)));
		WebElement cityMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.cityMsg)));
		WebElement stateMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.stateMsg)));
		WebElement phoneMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneMsg)));
		WebElement emailMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.emailMsg)));

		String expectedText1 = "Address is required";
		String actualText1 = addressMsg.getText();
		Assert.assertEquals(actualText1, expectedText1);

		String expectedText2 = "City is required";
		String actualText2 = cityMsg.getText();
		Assert.assertEquals(actualText2, expectedText2);

		String expectedText3 = "State is required";
		String actualText3 = stateMsg.getText();
		Assert.assertEquals(actualText3, expectedText3);

		String expectedText4 = "Phone is required";
		String actualText4 = phoneMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		String expectedText5 = "E-mail must be valid";
		String actualText5 = emailMsg.getText();
		Assert.assertEquals(actualText5, expectedText5);

	}

	//Adding location form --- Phone number, Email validation check

	@Test(priority = 7)
	public void validatePhoneEmailData() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.locationName)));
		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.licenceNumber)));
		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.capacity)));
		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.address)));
		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.suiteUnit)));
		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.city)));
		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.state)));
		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.zip)));
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneNumber)));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.email)));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.note)));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));

		locationName.sendKeys(LocationInfoData.locationName);
		licenceNumber.sendKeys(LocationInfoData.licenceNumber);
		capacity.sendKeys(LocationInfoData.capacity);
		address.sendKeys(LocationInfoData.address);
		suiteUnit.sendKeys(LocationInfoData.suiteUnit);
		city.sendKeys(LocationInfoData.city);
		state.sendKeys(LocationInfoData.state);
		zip.sendKeys(LocationInfoData.zip);
		phoneNumber.sendKeys(LocationInfoData.phoneNumber3);
		email.sendKeys(LocationInfoData.mail3);
		note.sendKeys(LocationInfoData.note);
		save.isEnabled();
		Thread.sleep(5000);


		WebElement phoneMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneMsg2)));
		WebElement emailMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.emailMsg)));

		String expectedText4 = "Phone Number minimum 10 digit required";
		String actualText4 = phoneMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		String expectedText5 = "E-mail must be valid";
		String actualText5 = emailMsg.getText();
		Assert.assertEquals(actualText5, expectedText5);

	}

	//Adding location form --- back button check

	@Test(priority = 8)
	public void validateBackButton() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement back = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.back)));
		back.click();

		Assert.assertTrue(true);
	}


	//Adding location form --- cancel button check

	@Test(priority = 9)
	public void validateCancelButton() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.cancel)));
		cancel.click();

		Thread.sleep(2000);
		Assert.assertTrue(true);
	}

	//Add patient info after adding new location info

	@Test(priority = 10)
	public void addPatientfromLocation() throws InterruptedException {

		validLocationData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement addButton1 = driver.findElement(By.xpath(LocationsXpath.add));
		addButton1.click();
		Thread.sleep(1000);
		WebElement addPatientButton = driver.findElement(By.xpath(LocationsXpath.addPatientButton));
		addPatientButton.click();
		Thread.sleep(1000);
		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.FirstName)));
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.LastName)));
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.ssn)));
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneNumber2)));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.email)));
		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.submit)));

		FirstName.sendKeys(LocationInfoData.patientfirstName);
		LastName.sendKeys(LocationInfoData.patientlastName);
		ssn.sendKeys(LocationInfoData.ssn);
		phoneNumber.sendKeys(LocationInfoData.phone);
		email.sendKeys(LocationInfoData.patientemail);
		submit.click();
		Thread.sleep(5000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/patients/"));

	}

	//Add patient info after adding new location info -- Empty fields

	@Test(priority = 11)
	public void emptyPatientInfofromLocation() throws InterruptedException {

		validLocationData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement addButton1 = driver.findElement(By.xpath("//i[normalize-space()='add']"));
		addButton1.click();
		Thread.sleep(1000);
		WebElement addPatientButton = driver.findElement(By.xpath("//strong[normalize-space()='Patient']"));
		addPatientButton.click();
		Thread.sleep(1000);
		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.FirstName)));
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.LastName)));
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.ssn)));
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneNumber2)));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.email)));
		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.submit)));

		FirstName.sendKeys(LocationInfoData.patientfirstName2);
		LastName.sendKeys(LocationInfoData.patientlastName2);
		ssn.sendKeys(LocationInfoData.ssn2);
		phoneNumber.sendKeys(LocationInfoData.phone2);
		email.sendKeys(LocationInfoData.patientemail2);

		submit.isEnabled();
		Thread.sleep(5000);

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.firstNameMsg)));
		WebElement lastNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.lastNameMsg)));

		String expectedText4 = "First name is required";
		String actualText4 = firstNameMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		String expectedText5 = "Last name is required";
		String actualText5 = lastNameMsg.getText();
		Assert.assertEquals(actualText5, expectedText5);

		Thread.sleep(1000);

	}

	//Add contact info after adding new location info

	@Test(priority = 12)
	public void addContactfromLocation() throws InterruptedException {

		validLocationData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement addButton1 = driver.findElement(By.xpath(LocationsXpath.add));
		addButton1.click();
		Thread.sleep(1000);
		WebElement addContactButton = driver.findElement(By.xpath(LocationsXpath.addContactButton));
		addContactButton.click();
		Thread.sleep(1000);

		WebElement FirstName = driver.findElement(By.xpath(LocationsXpath.FirstName2));
		FirstName.sendKeys(LocationInfoData.contactfirstName);
		WebElement phoneNumber = driver.findElement(By.xpath(LocationsXpath.phoneNumber2));
		phoneNumber.sendKeys(LocationInfoData.contactPhone);
		WebElement email = driver.findElement(By.xpath(LocationsXpath.email));
		email.sendKeys(LocationInfoData.contactEmail);
		WebElement save = driver.findElement(By.xpath(LocationsXpath.save));

		save.click();
		Thread.sleep(5000);

		WebElement contactButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.contactButton)));
		contactButton.click();

		WebElement firstRow = driver.findElement(By.xpath(LocationsXpath.firstRow));
		String expectedText = LocationInfoData.contactfirstName;
		String actualText = firstRow.getText();
		Assert.assertEquals(actualText, expectedText);

		String expectedText1 = firstRow.getText();
		firstRow.click();
		WebElement firstRowInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.firstRowInfo)));
		String actualText1 = firstRowInfo.getText();
		Assert.assertEquals(actualText1, expectedText1);
		Thread.sleep(2000);

		WebElement closeInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.closeInfo)));
		closeInfo.click();

	}

	//Add contact info after adding new location info with empty fields

	@Test(priority = 13)
	public void addContactEmptyLocation() throws InterruptedException {

		validLocationData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement addButton1 = driver.findElement(By.xpath(LocationsXpath.add));
		addButton1.click();
		Thread.sleep(1000);
		WebElement addContactButton = driver.findElement(By.xpath(LocationsXpath.addContactButton));
		addContactButton.click();
		Thread.sleep(1000);

		WebElement FirstName = driver.findElement(By.xpath(LocationsXpath.FirstName2));
		FirstName.sendKeys(LocationInfoData.contactfirstName2);
		WebElement phoneNumber = driver.findElement(By.xpath(LocationsXpath.phoneNumber2));
		phoneNumber.sendKeys(LocationInfoData.contactPhone2);
		WebElement email = driver.findElement(By.xpath(LocationsXpath.email));
		email.sendKeys(LocationInfoData.contactEmail2);
		WebElement save = driver.findElement(By.xpath(LocationsXpath.save));

		save.isEnabled();
		Thread.sleep(5000);

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.firstNameMsg2)));
		String expectedText4 = "First name is required";
		String actualText4 = firstNameMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

	}

	//Edit location info

	@Test(priority = 14)
	public void editLocationInfo() throws InterruptedException {
		locationList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement locationSelect = driver.findElement(By.xpath(LocationsXpath.firstRow));
		locationSelect.click();
		Thread.sleep(2000);

		WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.editButton)));
		editButton.click();

		WebElement editOptionSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.editOptionSelect)));
		editOptionSelect.click();

		WebElement editLicenceNumber = driver.findElement(By.xpath(LocationsXpath.editLicenceNumber));
		editLicenceNumber.clear();
		Thread.sleep(1000);

		while (!editLicenceNumber.getAttribute("value").equals("")) {
			editLicenceNumber.sendKeys(Keys.BACK_SPACE);
		}

		editLicenceNumber.sendKeys(LocationInfoData.editLicenceNumber);

		WebElement editCapacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.editCapacity)));
		editCapacity.clear();
		Thread.sleep(1000);

		while (!editCapacity.getAttribute("value").equals("")) {
			editCapacity.sendKeys(Keys.BACK_SPACE);
		}
		editCapacity.sendKeys(LocationInfoData.editCapacity);

		WebElement update = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.update)));
		update.click();
		Thread.sleep(3000);

		Assert.assertTrue(true);

	}

	//Delete location info

	@Test(priority = 15)
	public void deleteLocationInfo() throws InterruptedException {
		locationList();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement locationSelect = driver.findElement(By.xpath(LocationsXpath.firstRow));
		locationSelect.click();
		Thread.sleep(2000);

		WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.editButton)));
		editButton.click();

		WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.deleteButton)));
		deleteButton.click();
		Thread.sleep(1000);

		WebElement deletePopUpYes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.deletePopUpYes)));
		deletePopUpYes.click();
		Thread.sleep(3000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/locations"));
		Thread.sleep(3000);
	}


	//Add location From patient overview page

	@Test
	public void addLocationFromPatient() throws InterruptedException {
		loginUser();
		driver.findElement(By.xpath(LocationsXpath.patients)).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.add2)));
		add.click();
		WebElement selectAddPatient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.selectAddPatient)));
		selectAddPatient.click();

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.FirstName)));
		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.LastName)));
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.submit)));

		firstName.sendKeys(PatientsInfoData.patientfirstName);
		lastName.sendKeys(PatientsInfoData.patientlastName);
		save.click();

		WebElement addNewLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.addNewLocation)));
		addNewLocation.click();		
		WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.addButton)));
		addButton.click();
		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.selectType)));
		selectType.click();
		WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.continueButton)));
		continueButton.click();

		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.locationName)));
		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.licenceNumber)));
		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.capacity)));
		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.address)));
		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.suiteUnit)));
		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.city)));
		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.state)));
		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.zip)));
		WebElement save2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.save)));

		locationName.sendKeys(LocationInfoData.locationName);
		licenceNumber.sendKeys(LocationInfoData.licenceNumber);
		capacity.sendKeys(LocationInfoData.capacity);
		address.sendKeys(LocationInfoData.address);
		suiteUnit.sendKeys(LocationInfoData.suiteUnit);
		city.sendKeys(LocationInfoData.city);
		state.sendKeys(LocationInfoData.state);
		zip.sendKeys(LocationInfoData.zip);
		save2.click();
		Thread.sleep(5000);

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
