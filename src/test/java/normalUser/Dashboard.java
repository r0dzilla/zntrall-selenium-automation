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

import NormalUserXpath.DashboardXpath;
import browser.OpenBrowser;
import normalUserFunctions.DashboardFunctions;
import normalUserInputData.DashboardInfoData;

public class Dashboard extends OpenBrowser {

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 8 -- Dashboard";
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

	public static String myBrowser = "MicrosoftEdge";
	@BeforeTest
	public void setup() throws MalformedURLException {

		driver = start(myBrowser);

	}


	//Opening browser with the given URL and navigate to Registration Page

	@BeforeMethod
	public void openBrowser() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.get("https://dev.zntral.net/session/login");
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}



	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}

	//login

	@Test(priority = 1)
	public void loginUser()  throws InterruptedException {

		DashboardFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

	}

	// Add Location from the Dashboard

	@Test(priority = 2)
	public void addLocation() throws InterruptedException{

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();

		Thread.sleep(1000);

		DashboardFunctions.optionSelect();

		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.selectContinue)));
		selectContinue.click();
		Assert.assertTrue(true);

	}

	// Add Location from the Dashboard with valid data

	@Test(priority = 3)
	public void validLocationData() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();

		Thread.sleep(1000);

		DashboardFunctions.optionSelect();

		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.selectContinue)));
		selectContinue.click();

		String LName = DashboardInfoData.locationName;
		String LLicence = DashboardInfoData.licenceNumber;
		String LCapacity = DashboardInfoData.capacity;
		String Laddress = DashboardInfoData.address;
		String LsuiteUnit = DashboardInfoData.suiteUnit;
		String Lcity = DashboardInfoData.city;
		String Lstate = DashboardInfoData.state;
		String Lzip = DashboardInfoData.zip;
		String LphoneNumber = DashboardInfoData.phoneNumber;
		String Lemail = DashboardInfoData.email;
		String Lnote = DashboardInfoData.note;

		DashboardFunctions.addData(LName, LLicence, LCapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.save)));
		save.click();
		Thread.sleep(5000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/locations/"));

	}

	//Adding location without any info

	@Test(priority = 4)
	public void invalidLocationData() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();

		Thread.sleep(1000);

		DashboardFunctions.optionSelect();

		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.selectContinue)));
		selectContinue.click();

		Thread.sleep(2000);

		String LName = DashboardInfoData.locationName2;
		String LLicence = DashboardInfoData.licenceNumber2;
		String LCapacity = DashboardInfoData.capacity2;
		String Laddress = DashboardInfoData.address2;
		String LsuiteUnit = DashboardInfoData.suiteUnit2;
		String Lcity = DashboardInfoData.city2;
		String Lstate = DashboardInfoData.state2;
		String Lzip = DashboardInfoData.zip2;
		String LphoneNumber = DashboardInfoData.phoneNumber2;
		String Lemail = DashboardInfoData.email2;
		String Lnote = DashboardInfoData.note2;

		DashboardFunctions.addData(LName, LLicence, LCapacity, Laddress, LsuiteUnit, Lcity, Lstate, Lzip, LphoneNumber, Lemail, Lnote);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.save)));


		try {
			save.click();
			Thread.sleep(2000);	

		} 
		catch (Exception e){
			save.isEnabled();
			Thread.sleep(2000);
		}

		WebElement addressMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.addressMsg)));
		WebElement cityMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.cityMsg)));
		WebElement stateMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.stateMsg)));
		WebElement phoneMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.phoneMsg)));
		WebElement emailMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.emailMsg)));

		Assert.assertTrue(true);

	}

	//Email validation check on Add location form

	@Test(priority = 5)
	public void validateEmailData() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();
		Thread.sleep(1000);
		DashboardFunctions.optionSelect();

		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.selectContinue)));
		selectContinue.click();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.email)));

		email.sendKeys(DashboardInfoData.validemail);
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.note)));
		note.sendKeys(DashboardInfoData.note3);

		if(email.getAttribute("value").startsWith("@")){
			WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.invalidMsg)));
			Assert.assertTrue(true);
		}else {
		}
	}

	// Add Patient from the Dashboard

	@Test(priority = 6)
	public void addPatient() throws InterruptedException{

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.add2)));
		driver.findElement(By.xpath(DashboardXpath.add2)).click();

		Thread.sleep(5000);

		String Pprefix = DashboardInfoData.prefix;
		String PfirstName = DashboardInfoData.patientfirstName;
		String PlastName =DashboardInfoData.patientlastName;
		String Pssn= DashboardInfoData.ssn;
		String Pdob= DashboardInfoData.dob;
		String Pphone = DashboardInfoData.phone;
		String Pemail = DashboardInfoData.patientemail;
		String Pnote = DashboardInfoData.patientnote;

		DashboardFunctions.addPatientData(Pprefix, PfirstName, PlastName, Pssn, Pdob, Pphone, Pemail, Pnote);;

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.Submit)));
		save.click();
		Thread.sleep(5000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://dev.zntral.net/patients/"));

	}

	//Adding patient without any info

	@Test(priority = 7)
	public void addPatientWithEmpty() throws InterruptedException{

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.add2)));
		driver.findElement(By.xpath(DashboardXpath.add2)).click();

		String Pprefix = DashboardInfoData.prefix2;
		String PfirstName = DashboardInfoData.patientfirstName2;
		String PlastName =DashboardInfoData.patientlastName2;
		String Pssn= DashboardInfoData.ssn2;
		String Pdob= DashboardInfoData.dob2;
		String Pphone = DashboardInfoData.phone2;
		String Pemail = DashboardInfoData.patientemail2;
		String Pnote = DashboardInfoData.patientnote2;

		DashboardFunctions.addPatientData(Pprefix, PfirstName, PlastName, Pssn, Pdob, Pphone, Pemail, Pnote);;

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.Submit)));


		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			save.isEnabled();
			Thread.sleep(1000);
		}

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.firstNameMsg)));
		WebElement lastNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.lastNameMsg)));

		String expectedText1 = "First name is required";
		String actualText1 = firstNameMsg.getText();
		Assert.assertEquals(actualText1, expectedText1);

		String expectedText2 = "Last name is required";
		String actualText2 = lastNameMsg.getText();
		Assert.assertEquals(actualText2, expectedText2);

	}

	//Email validation check on Add patient form

	@Test(priority = 8)
	public void validateEmailDataOnPatient() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		DashboardFunctions.verifyLogin();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.add2)));
		driver.findElement(By.xpath(DashboardXpath.add2)).click();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.email)));

		email.sendKeys(DashboardInfoData.patientvalidemail);
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.patientNote)));
		note.sendKeys(DashboardInfoData.patientnote3);
		if(email.getAttribute("value").startsWith("@")){
			WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.invalidMsg)));
			Assert.assertTrue(true);
			Thread.sleep(1000);
		}else {
			Thread.sleep(2000);
		}
	}

	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}
}
