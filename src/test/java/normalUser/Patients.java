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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import NormalUserXpath.PatientsXpath;
import browser.OpenBrowser;
import normalUserInputData.PatientsInfoData;

public class Patients extends OpenBrowser {

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 5 -- Patients";
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
		WebElement username = driver.findElement(By.xpath(PatientsXpath.username));
		WebElement password = driver.findElement(By.xpath(PatientsXpath.password));
		WebElement login = driver.findElement(By.xpath(PatientsXpath.login));

		username.sendKeys(PatientsInfoData.user);

		password.sendKeys(PatientsInfoData.pass);

		login.click();
		//		WebElement loginAs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Counsellors']")));
		//		loginAs.click();
		Thread.sleep(5000);

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);


	}


	//View the patients list
	@Test(priority = 2)
	public void patientsList() throws InterruptedException {

		loginUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.patients)));
		driver.findElement(By.xpath(PatientsXpath.patients)).click();

		String expectedUrl = "https://dev.zntral.net/patients";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//Search option

	@Test(priority = 3)
	public void search() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.search)));
		search.sendKeys(PatientsInfoData.search);


		WebElement firstRow = driver.findElement(By.xpath(PatientsXpath.firstRow));	
		String actualText = firstRow.getText();
		Assert.assertTrue(actualText.contains(PatientsInfoData.search));
		Thread.sleep(3000);
	}


	//Add new Patient -- Click "+" button

	@Test(priority = 4)
	public void addPatient() throws InterruptedException{
		patientsList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.add)));
		add.click();

		WebElement selectAddPatient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.selectAddPatient)));
		selectAddPatient.click();
		Thread.sleep(2000);

		Assert.assertTrue(true);


	}

	//Add patient with valid info

	@Test(priority = 5)
	public void validPatientData() throws InterruptedException {
		addPatient();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement prefix = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.prefix)));
		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.firstName)));
		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.lastName)));
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.ssn)));
		WebElement dob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.dob)));
		WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.gender)));
		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.phone)));
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.type)));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.email)));
		WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.location)));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.note)));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.save)));

		prefix.sendKeys(PatientsInfoData.prefix);
		firstName.sendKeys(PatientsInfoData.patientfirstName);
		lastName.sendKeys(PatientsInfoData.patientlastName);
		ssn.sendKeys(PatientsInfoData.ssn);
		dob.sendKeys(PatientsInfoData.dob);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.ok))).click();
		gender.click();
		WebElement genderMale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.genderMale)));
		genderMale.click();
		phone.sendKeys(PatientsInfoData.phone);
		type.click();
		WebElement typeMobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.typeMobile)));
		typeMobile.click();
		email.sendKeys(PatientsInfoData.patientemail);
		location.click();
		WebElement selectLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.selectLocation)));
		selectLocation.click();
		note.sendKeys(PatientsInfoData.patientnote);
		save.click();
		Thread.sleep(5000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/patients/"));
		Thread.sleep(3000);

	}

	//Adding patient without any info

	@Test(priority = 6)
	public void invalidPatientData() throws InterruptedException {
		addPatient();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement prefix = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.prefix)));
		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.firstName)));
		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.lastName)));
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.ssn)));
		WebElement dob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.dob)));
		WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.gender)));
		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.phone)));
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.type)));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.email)));
		WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.location)));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.note)));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.save)));

		prefix.sendKeys(PatientsInfoData.prefix2);
		firstName.sendKeys(PatientsInfoData.patientfirstName2);
		lastName.sendKeys(PatientsInfoData.patientlastName2);
		ssn.sendKeys(PatientsInfoData.ssn2);
		dob.sendKeys(PatientsInfoData.dob2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.ok))).click();
		gender.click();
		phone.sendKeys(PatientsInfoData.phone2);
		type.click();
		email.sendKeys(PatientsInfoData.patientemail2);
		location.click();
		note.sendKeys(PatientsInfoData.patientnote2);
		save.isEnabled();
		Thread.sleep(5000);

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.firstNameMsg)));
		WebElement lastNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.lastNameMsg)));

		String expectedText4 = "First name is required";
		String actualText4 = firstNameMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		String expectedText5 = "Last name is required";
		String actualText5 = lastNameMsg.getText();
		Assert.assertEquals(actualText5, expectedText5);

		Thread.sleep(1000);
	}


	//Edit Patient info

	@Test(priority = 7)
	public void editPatientInfo() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement patientSelect = driver.findElement(By.xpath(PatientsXpath.firstRow));
		patientSelect.click();
		Thread.sleep(2000);

		WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.editButton)));
		editButton.click();

		WebElement editOptionSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.editOptionSelect)));
		editOptionSelect.click();
		Thread.sleep(1000);

		WebElement editSSN = driver.findElement(By.xpath(PatientsXpath.editSSN));
		editSSN.clear();

		while (!editSSN.getAttribute("value").equals("")) {
			editSSN.sendKeys(Keys.BACK_SPACE);
		}
		editSSN.sendKeys(PatientsInfoData.validssn);

		WebElement update = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.update)));
		update.click();
		Thread.sleep(3000);

		Assert.assertTrue(true);
	}

	//Delete Patient info

	@Test(priority = 8)
	public void deletePatientInfo() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement patientSelect = driver.findElement(By.xpath(PatientsXpath.firstRow));
		patientSelect.click();
		Thread.sleep(2000);

		WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.editButton)));
		editButton.click();

		WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.deleteButton)));
		deleteButton.click();


		WebElement deletePopUpYes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.deletePopUpYes)));
		deletePopUpYes.click();

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/patients"));
		Thread.sleep(3000);
	}

	//Add contact info after adding new patient info

	@Test(priority = 9)
	public void addContactfromPatient() throws InterruptedException {
		validPatientData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath(PatientsXpath.addButton1));
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath(PatientsXpath.addContactButton));
		addContactButton.click();
		Thread.sleep(1000);

		WebElement FirstName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.FirstName)));
		FirstName.sendKeys(PatientsInfoData.firstName);

		WebElement LastName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.LastName)));
		LastName.sendKeys(PatientsInfoData.lastName);

		WebElement gender =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.Gender)));
		gender.click();

		WebElement genderMale =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.genderMale)));
		genderMale.click();

		WebElement relationship =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.relationship)));
		relationship.click();

		WebElement relationshipFather =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.relationshipFather)));
		relationshipFather.click();

		WebElement phone =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.Phone)));
		phone.sendKeys(PatientsInfoData.phonenumber);

		WebElement phoneDropdown =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.phoneDropdown)));
		phoneDropdown.click();

		WebElement phoneTypeSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.phoneTypeSelect)));
		phoneTypeSelect.click();

		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.save)));
		save.click();
		Thread.sleep(5000);

		String expectedText5 = "TestContact data";
		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.contactNameSelect)));
		String actualText5 = contactNameSelect.getText();
		Assert.assertEquals(actualText5, expectedText5);
	}

	//Add contact with empty fields after adding new patient 

	@Test(priority = 10)
	public void addContactWithEmptyData() throws InterruptedException {
		validPatientData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath(PatientsXpath.addButton1));
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath(PatientsXpath.addContactButton));
		addContactButton.click();
		Thread.sleep(1000);

		WebElement FirstName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.FirstName)));
		FirstName.sendKeys(PatientsInfoData.firstName2);

		WebElement LastName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.LastName)));
		LastName.sendKeys(PatientsInfoData.lastName2);

		WebElement relationship =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.relationship)));
		relationship.click();

		WebElement phone =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.Phone)));
		phone.sendKeys(PatientsInfoData.phone2);

		WebElement note =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.Note)));
		note.sendKeys(PatientsInfoData.note);

		WebElement phoneType =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.phoneDropdown)));
		phoneType.click();

		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.save)));
		save.isEnabled();
		Thread.sleep(5000);

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.FirstNameMsg)));
		String expectedText4 = "First name is required";
		String actualText4 = firstNameMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		WebElement relationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.relationMsg)));
		String expectedText = "Relationship is required";
		String actualText = relationMsg.getText();
		Assert.assertEquals(actualText, expectedText);
	}

	//Add contact to any patient from patients list 

	@Test(priority = 11)
	public void addContactFromPatientList() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebElement firstRow = driver.findElement(By.xpath(PatientsXpath.firstRow));	
		firstRow.click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath(PatientsXpath.addButton1));
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath(PatientsXpath.addContactButton));
		addContactButton.click();
		Thread.sleep(1000);

		WebElement FirstName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.FirstName)));
		FirstName.sendKeys(PatientsInfoData.firstName);

		WebElement LastName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.LastName)));
		LastName.sendKeys(PatientsInfoData.lastName);

		WebElement gender =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.Gender)));
		gender.click();

		WebElement genderMale =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.GenderMale)));
		genderMale.click();

		WebElement relationship =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.relationship)));
		relationship.click();

		WebElement relationshipFather =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.relationshipFather)));
		relationshipFather.click();

		WebElement phone =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.Phone)));
		phone.sendKeys(PatientsInfoData.phonenumber);

		WebElement phoneDropdown =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.phoneDropdown)));
		phoneDropdown.click();

		WebElement phoneTypeSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.phoneTypeSelect)));
		phoneTypeSelect.click();

		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.save)));
		save.click();
		Thread.sleep(5000);

		String expectedText5 = "TestContact2 data";
		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.contactNameSelect)));
		String actualText5 = contactNameSelect.getText();
		Assert.assertEquals(actualText5, expectedText5);

	}

	//Add Schedule info after adding new patient info
	@Test(priority = 12)
	public void addSchdeulefromPatient() throws InterruptedException {
		validPatientData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath(PatientsXpath.addButton1));
		addButton1.click();

		WebElement addScheduleButton = driver.findElement(By.xpath(PatientsXpath.addScheduleButton));
		addScheduleButton.click();
		Thread.sleep(1000);

		WebElement startDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.startDate)));
		startDate.click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.select1))).click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.select2))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.select3))).sendKeys("This is test");

		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.save)));
		save.click();
		Thread.sleep(5000);


		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.contactNameSelect2)));
		String actualText = contactNameSelect.getText();
		WebElement actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.actual)));
		String expectedText = actual.getText();
		Assert.assertTrue(expectedText.contains(actualText));
	}

	//Add schedule to any patient from patients list 

	@Test(priority = 13)
	public void addSheduleFromPatientList() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebElement firstRow = driver.findElement(By.xpath(PatientsXpath.firstRow));	
		firstRow.click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath(PatientsXpath.addButton1));
		addButton1.click();

		WebElement addScheduleButton = driver.findElement(By.xpath(PatientsXpath.addScheduleButton));
		addScheduleButton.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.select4))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.select5))).click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.select6))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.select3))).sendKeys("This is test");

		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.save)));
		save.click();
		Thread.sleep(5000);

		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.contactNameSelect2)));
		String actualText = contactNameSelect.getText();
		WebElement actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.actual)));
		String expectedText = actual.getText();
		Assert.assertTrue(expectedText.contains(actualText));

	}

	//Add schedule with empty fields after adding new patient 

	@Test(priority = 14)
	public void addSchdeuleWithEmpty() throws InterruptedException {
		validPatientData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath(PatientsXpath.addButton1));
		addButton1.click();

		WebElement addScheduleButton = driver.findElement(By.xpath(PatientsXpath.addScheduleButton));
		addScheduleButton.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.select3))).sendKeys("");

		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.save)));
		save.click();
		Thread.sleep(5000);


		WebElement status =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.status)));
		String actualText = status.getText();
		String expectedText = "Failed to add Schedule";
		Assert.assertTrue(actualText.contains(expectedText));
	}


	//Add schedule with empty fields from the patient list

	@Test(priority = 15)
	public void addEmptySheduleFromPatientList() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebElement firstRow = driver.findElement(By.xpath(PatientsXpath.firstRow));	
		firstRow.click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath(PatientsXpath.addButton1));
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath(PatientsXpath.addScheduleButton));
		addContactButton.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.select3))).sendKeys("");

		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.save)));
		save.click();
		Thread.sleep(5000);


		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.status)));
		String actualText = contactNameSelect.getText();
		String expectedText = "Failed to add Schedule";
		Assert.assertTrue(actualText.contains(expectedText));

	}

	//Contact form cancel button check

	@Test(priority = 16)
	public void contactFormCancelButton() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebElement firstRow = driver.findElement(By.xpath(PatientsXpath.firstRow));	
		firstRow.click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath(PatientsXpath.addButton1));
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath(PatientsXpath.addContactButton));
		addContactButton.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.cancel))).click();
		Thread.sleep(5000);

		Assert.assertTrue(true);

	}

	//Schedule form cancel button check

	@Test(priority = 17)
	public void sheduleFormCancelButton() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebElement firstRow = driver.findElement(By.xpath(PatientsXpath.firstRow));	
		firstRow.click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath(PatientsXpath.addButton1));
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath(PatientsXpath.addScheduleButton));
		addContactButton.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.close))).click();
		Thread.sleep(5000);

		Assert.assertTrue(true);

	}

	//import patient list

	@Test
	public void importPatientList() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.addButton1)));
		add.click();

		WebElement importButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.importButton)));
		importButton.click();

		WebElement dropFile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.dropFile)));
		dropFile.click();

		//check preview button

		WebElement preview = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.preview)));
		preview.click();
		Thread.sleep(2000);
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
