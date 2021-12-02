package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Patients {

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 5 -- Patients";

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


	//View the patients list
	@Test
	public void patientsList() throws InterruptedException {

		loginUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Patients']")));
		driver.findElement(By.xpath("//span[normalize-space()='Patients']")).click();
		Thread.sleep(3000);
		String expectedUrl = "https://zntral.net/patients";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//Search option

	@Test
	public void search() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("Sabbir");
		Thread.sleep(3000);

		WebElement firstRow = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));	
		String actualText = firstRow.getText();
		String expectedText = "Sabbir";
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(3000);
	}


	//Add new Patient -- Click "+" button

	@Test
	public void addPatient() throws InterruptedException{
		patientsList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='v-btn v-btn--bottom v-btn--contained v-btn--fab v-btn--fixed v-btn--right v-btn--round theme--light v-size--default primary']")));
		add.click();

		WebElement selectAddPatient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Add New Patient')]")));
		selectAddPatient.click();
		Thread.sleep(2000);

		Assert.assertTrue(true);


	}

	//Add patient with valid info

	@Test
	public void validPatientData() throws InterruptedException {
		addPatient();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement prefix = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[4]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement dob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[2]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]")));
		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//i[1]")));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]//i[1]")));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Submit']")));

		Thread.sleep(2000);

		prefix.sendKeys("Mr.");
		Thread.sleep(1000);
		firstName.sendKeys("Test");
		lastName.sendKeys("Info");
		Thread.sleep(1000);
		ssn.sendKeys("1234564");
		Thread.sleep(1000);
		dob.sendKeys("1993-10-25");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='OK']"))).click();
		Thread.sleep(1000);
		gender.click();
		Thread.sleep(1000);
		WebElement genderMale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Male')]")));
		genderMale.click();
		phone.sendKeys("7894561235");
		type.click();
		Thread.sleep(1000);
		WebElement typeMobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-list-item__title'][normalize-space()='Mobile']")));
		typeMobile.click();
		email.sendKeys("sabbir@email.com");
		Thread.sleep(1000);
		location.click();
		Thread.sleep(1000);
		WebElement selectLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Test Location 2']")));
		selectLocation.click();
		note.sendKeys("This is demo data");
		save.click();
		Thread.sleep(5000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://zntral.net/patients/"));
		Thread.sleep(3000);

	}

	//Adding patient without any info

	@Test
	public void invalidPatientData() throws InterruptedException {
		addPatient();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement prefix = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[4]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement dob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[2]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]")));
		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//i[1]")));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]//i[1]")));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Submit']")));

		Thread.sleep(2000);

		prefix.sendKeys("");
		Thread.sleep(1000);
		firstName.sendKeys("");
		lastName.sendKeys("");
		Thread.sleep(1000);
		ssn.sendKeys("");
		Thread.sleep(1000);
		dob.sendKeys("");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='OK']"))).click();
		Thread.sleep(1000);
		gender.click();
		Thread.sleep(1000);
		phone.sendKeys("");
		type.click();
		Thread.sleep(1000);
		email.sendKeys("");
		Thread.sleep(1000);
		location.click();
		Thread.sleep(1000);
		note.sendKeys("");
		save.isEnabled();
		Thread.sleep(5000);

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'First name is required')]")));
		WebElement lastNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Last name is required')]")));

		String expectedText4 = "First name is required";
		String actualText4 = firstNameMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		String expectedText5 = "Last name is required";
		String actualText5 = lastNameMsg.getText();
		Assert.assertEquals(actualText5, expectedText5);

		Thread.sleep(1000);
	}


	//Edit Patient info

	@Test
	public void editPatientInfo() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement patientSelect = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));
		patientSelect.click();
		Thread.sleep(2000);

		WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='more_vert']")));
		editButton.click();
		Thread.sleep(1000);

		WebElement editOptionSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Edit')]")));
		editOptionSelect.click();
		Thread.sleep(1000);

		WebElement editSSN = driver.findElement(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]"));
		editSSN.clear();
		Thread.sleep(2000);

		while (!editSSN.getAttribute("value").equals("")) {
			editSSN.sendKeys(Keys.BACK_SPACE);
		}
		Thread.sleep(1000);
		editSSN.sendKeys("7895213");
		Thread.sleep(1000);

		WebElement update = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Update']")));
		update.click();
		Thread.sleep(3000);

		Assert.assertTrue(true);
	}

	//Delete Patient info

	@Test
	public void deletePatientInfo() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement patientSelect = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));
		patientSelect.click();
		Thread.sleep(2000);

		WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='more_vert']")));
		editButton.click();
		Thread.sleep(1000);

		WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Delete')]")));
		deleteButton.click();
		Thread.sleep(1000);

		WebElement deletePopUpYes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='yes']")));
		deletePopUpYes.click();
		Thread.sleep(3000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://zntral.net/patients"));
		Thread.sleep(3000);
	}

	//Add contact info after adding new patient info
	@Test
	public void addContactfromPatient() throws InterruptedException {
		validPatientData();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath("//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']"));
		Thread.sleep(2000);
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath("//div[normalize-space()='Contact']"));
		Thread.sleep(1000);
		addContactButton.click();
		Thread.sleep(1000);

		WebElement FirstName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		FirstName.sendKeys("TestContact");
		Thread.sleep(1000);

		WebElement LastName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[3]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		LastName.sendKeys("data");
		Thread.sleep(1000);

		WebElement gender =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//div//div//div//div//div//div//div[@role='button']//div//div//div//i[@aria-hidden='true']")));
		gender.click();
		Thread.sleep(1000);

		WebElement genderMale =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]")));
		genderMale.click();
		Thread.sleep(1000);

		WebElement relationship =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div/div/div[@role='button']/div/div/div/i[1]")));
		relationship.click();
		Thread.sleep(1000);

		WebElement relationshipFather =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Father')]")));
		relationshipFather.click();
		Thread.sleep(1000);

		WebElement phone =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		phone.sendKeys("753951");
		Thread.sleep(1000);

		WebElement phoneDropdown =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div/div[@role='button']/div/div/div/i[1]")));
		phoneDropdown.click();
		Thread.sleep(1000);

		WebElement phoneTypeSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Home')]")));
		phoneTypeSelect.click();
		Thread.sleep(1000);

		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
		save.click();
		Thread.sleep(5000);

		String expectedText5 = "TestContact data";
		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[last()]/td[1]")));
		String actualText5 = contactNameSelect.getText();
		Assert.assertEquals(actualText5, expectedText5);
	}

	//Add contact with empty fields after adding new patient 

	@Test
	public void addContactWithEmptyData() throws InterruptedException {
		validPatientData();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath("//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']"));
		Thread.sleep(2000);
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath("//div[normalize-space()='Contact']"));
		Thread.sleep(1000);
		addContactButton.click();
		Thread.sleep(1000);

		WebElement FirstName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		FirstName.sendKeys("");
		Thread.sleep(1000);

		WebElement LastName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[3]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		LastName.sendKeys("");
		Thread.sleep(1000);

		WebElement relationship =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div/div/div[@role='button']/div/div/div/i[1]")));
		relationship.click();
		Thread.sleep(1000);

		WebElement phone =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		phone.sendKeys("");
		Thread.sleep(2000);

		WebElement note =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[7]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		note.sendKeys("");
		Thread.sleep(2000);

		WebElement phoneType =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div/div[@role='button']/div/div/div/i[1]")));
		phoneType.click();
		Thread.sleep(2000);

		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
		save.isEnabled();
		Thread.sleep(5000);

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-messages__message']")));
		String expectedText4 = "First name is required";
		String actualText4 = firstNameMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		WebElement relationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Relationship is required')]")));
		String expectedText = "Relationship is required";
		String actualText = relationMsg.getText();
		Assert.assertEquals(actualText, expectedText);
	}

	//Add contact to any patient from patients list 

	@Test
	public void addContactFromPatientList() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebElement firstRow = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));	
		firstRow.click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath("//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']"));
		Thread.sleep(2000);
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath("//div[normalize-space()='Contact']"));
		Thread.sleep(1000);
		addContactButton.click();
		Thread.sleep(1000);

		WebElement FirstName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		FirstName.sendKeys("TestContact2");
		Thread.sleep(1000);

		WebElement LastName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[3]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		LastName.sendKeys("data");
		Thread.sleep(1000);

		WebElement gender =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@novalidate='novalidate']//div//div//div//div//div//div//div[@role='button']//div//div//div//i[@aria-hidden='true']")));
		gender.click();
		Thread.sleep(1000);

		WebElement genderMale =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]")));
		genderMale.click();
		Thread.sleep(1000);

		WebElement relationship =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div/div/div[@role='button']/div/div/div/i[1]")));
		relationship.click();
		Thread.sleep(1000);

		WebElement relationshipFather =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Father')]")));
		relationshipFather.click();
		Thread.sleep(1000);

		WebElement phone =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		phone.sendKeys("753951");
		Thread.sleep(1000);

		WebElement phoneDropdown =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div/div[@role='button']/div/div/div/i[1]")));
		phoneDropdown.click();
		Thread.sleep(1000);

		WebElement phoneTypeSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Home')]")));
		phoneTypeSelect.click();
		Thread.sleep(1000);

		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
		save.click();
		Thread.sleep(5000);

		String expectedText5 = "TestContact2 data";
		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[last()]/td[1]")));
		String actualText5 = contactNameSelect.getText();
		Assert.assertEquals(actualText5, expectedText5);

	}

	//Add Schedule info after adding new patient info
	@Test
	public void addSchdeulefromPatient() throws InterruptedException {
		validPatientData();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath("//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']"));
		Thread.sleep(2000);
		addButton1.click();

		WebElement addScheduleButton = driver.findElement(By.xpath("//div[contains(text(),'Schedule')]"));
		Thread.sleep(1000);
		addScheduleButton.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[4]/td[4]/button[1]"))).click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]//i[1]"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]"))).sendKeys("This is test");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]"))).click();
		Thread.sleep(1000);
		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
		save.click();
		Thread.sleep(5000);


		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//div[7]")));
		String actualText = contactNameSelect.getText();
		WebElement actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]")));
		String expectedText = actual.getText();
		Assert.assertTrue(expectedText.contains(actualText));
	}

	//Add schedule to any patient from patients list 

	@Test
	public void addSheduleFromPatientList() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebElement firstRow = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));	
		firstRow.click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath("//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']"));
		Thread.sleep(2000);
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath("//div[contains(text(),'Schedule')]"));
		Thread.sleep(1000);
		addContactButton.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[4]/td[4]/button[1]"))).click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]//i[1]"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]"))).sendKeys("This is test");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]"))).click();
		Thread.sleep(1000);
		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
		save.click();
		Thread.sleep(5000);

		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//div[7]")));
		String actualText = contactNameSelect.getText();
		WebElement actual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]")));
		String expectedText = actual.getText();
		Assert.assertTrue(expectedText.contains(actualText));

	}

	//Add schedule with empty fields after adding new patient 

	@Test
	public void addSchdeuleWithEmpty() throws InterruptedException {
		validPatientData();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath("//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']"));
		Thread.sleep(2000);
		addButton1.click();

		WebElement addScheduleButton = driver.findElement(By.xpath("//div[contains(text(),'Schedule')]"));
		Thread.sleep(1000);
		addScheduleButton.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]"))).sendKeys("");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]"))).click();
		Thread.sleep(1000);
		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
		save.click();
		Thread.sleep(5000);


		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='status']")));
		String actualText = contactNameSelect.getText();
		String expectedText = "Failed to add Schedule";
		Assert.assertTrue(actualText.contains(expectedText));
	}


	//Add schedule with empty fields from the patient list

	@Test
	public void addEmptySheduleFromPatientList() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebElement firstRow = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));	
		firstRow.click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath("//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']"));
		Thread.sleep(2000);
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath("//div[contains(text(),'Schedule')]"));
		Thread.sleep(1000);
		addContactButton.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]"))).sendKeys("");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]"))).click();
		Thread.sleep(1000);
		WebElement save =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
		save.click();
		Thread.sleep(5000);


		WebElement contactNameSelect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='status']")));
		String actualText = contactNameSelect.getText();
		String expectedText = "Failed to add Schedule";
		Assert.assertTrue(actualText.contains(expectedText));

	}

	//Contact form cancel button check

	@Test
	public void contactFormCancelButton() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebElement firstRow = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));	
		firstRow.click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath("//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']"));
		Thread.sleep(2000);
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath("//div[normalize-space()='Contact']"));
		Thread.sleep(1000);
		addContactButton.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='float-right text-capitalize align-cancel v-btn v-btn--flat v-btn--text theme--light v-size--default']//span[@class='v-btn__content'][normalize-space()='Cancel']"))).click();
		Thread.sleep(5000);

		Assert.assertTrue(true);

	}

	//Schedule form cancel button check

	@Test
	public void sheduleFormCancelButton() throws InterruptedException {
		patientsList();
		Thread.sleep(2000);

		WebElement firstRow = driver.findElement(By.xpath("//tbody/tr[1]/td[2]"));	
		firstRow.click();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement addButton1 = driver.findElement(By.xpath("//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']"));
		Thread.sleep(2000);
		addButton1.click();

		WebElement addContactButton = driver.findElement(By.xpath("//div[normalize-space()='Schedule']"));
		Thread.sleep(1000);
		addContactButton.click();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='v-btn v-btn--flat v-btn--text theme--light v-size--default blue--text text--darken-1']//span[@class='v-btn__content'][normalize-space()='Close']"))).click();
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

}
