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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Locations {
	public static String env = "Test";
	public static String testSuiteName = "Test Suit 4 -- Locations";


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


	//View the location list

	@Test
	public void locationList() throws InterruptedException {

		loginUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Locations']")));
		driver.findElement(By.xpath("//span[normalize-space()='Locations']")).click();
		Thread.sleep(3000);
		String expectedUrl = "https://zntral.net/locations";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Search option

	@Test
	public void search() throws InterruptedException {
		locationList();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("Demo");

		WebElement firstRow = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
		String expectedText = "Demo";
		String actualText = firstRow.getText();
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(3000);
	}

	//Add new Location -- Click "+" button

	@Test
	public void addLocation() throws InterruptedException{
		loginUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Locations']")));
		driver.findElement(By.xpath("//span[normalize-space()='Locations']")).click();

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[normalize-space()='add']")));
		add.click();

		WebElement selectResidentType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/div/div/div/div[@role='radiogroup']/div[3]/div[1]")));
		selectResidentType.click();
		Thread.sleep(2000);

		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Continue']")));
		selectContinue.click();
		Thread.sleep(2000);

		Assert.assertTrue(selectResidentType.isEnabled());


		Thread.sleep(2000);

	}

	//Adding location with valid info

	@Test
	public void validLocationData() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[2]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//div[3]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input theme--light v-text-field v-text-field--is-booted v-select']//div[@class='v-select__selections']")));
		//WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[1]/input[1]")));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));

		Thread.sleep(2000);
		locationName.sendKeys("Rangpur");
		Thread.sleep(1000);
		licenceNumber.sendKeys("166578");
		Thread.sleep(1000);
		capacity.sendKeys("15");
		Thread.sleep(1000);
		address.sendKeys("Rangpur");
		Thread.sleep(1000);
		suiteUnit.sendKeys("65/89");
		Thread.sleep(1000);
		city.sendKeys("Rangpur");
		Thread.sleep(1000);
		state.sendKeys("Rangpur");
		Thread.sleep(1000);
		zip.sendKeys("8752");
		Thread.sleep(1000);
		phoneNumber.sendKeys("1294567894");
		Thread.sleep(1000);
		type.click();
		Thread.sleep(1000);
		//selectType.click();
		Thread.sleep(1000);
		email.sendKeys("Rangpur@email.com");
		Thread.sleep(1000);
		note.sendKeys("This is automation for valid text");
		Thread.sleep(2000);
		save.click();
		Thread.sleep(5000);


		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://zntral.net/locations/"));
		Thread.sleep(3000);

	}

	//Adding location without any info

	@Test
	public void invalidLocationData() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[2]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//div[3]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input theme--light v-text-field v-text-field--is-booted v-select']//div[@class='v-select__selections']")));
		//WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[1]/input[1]")));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));

		Thread.sleep(2000);
		locationName.sendKeys("");
		Thread.sleep(1000);
		licenceNumber.sendKeys("");
		Thread.sleep(1000);
		capacity.sendKeys("");
		Thread.sleep(1000);
		address.sendKeys("");
		Thread.sleep(1000);
		suiteUnit.sendKeys("");
		Thread.sleep(1000);
		city.sendKeys("");
		Thread.sleep(1000);
		state.sendKeys("");
		Thread.sleep(1000);
		zip.sendKeys("");
		Thread.sleep(1000);
		phoneNumber.sendKeys("");
		Thread.sleep(1000);
		type.click();
		Thread.sleep(1000);
		//selectType.click();
		Thread.sleep(1000);
		email.sendKeys("");
		Thread.sleep(1000);
		note.sendKeys("");
		Thread.sleep(2000);
		save.isEnabled();
		Thread.sleep(5000);

		WebElement addressMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Address is required')]")));
		WebElement cityMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'City is required')]")));
		WebElement stateMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'State is required')]")));
		WebElement phoneMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Phone is required')]")));
		WebElement emailMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'E-mail must be valid')]")));

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

		Thread.sleep(3000);

	}

	//Adding location form --- Phone number, Email validation check

	@Test
	public void validatePhoneEmailData() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[2]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//div[3]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		//WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input theme--light v-text-field v-text-field--is-booted v-select']//div[@class='v-select__selections']")));
		//WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[1]/input[1]")));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));

		Thread.sleep(2000);
		locationName.sendKeys("Sylhet");
		Thread.sleep(1000);
		licenceNumber.sendKeys("789456");
		Thread.sleep(1000);
		capacity.sendKeys("25");
		Thread.sleep(1000);
		address.sendKeys("Sylhet");
		Thread.sleep(1000);
		suiteUnit.sendKeys("15/78 rd");
		Thread.sleep(1000);
		city.sendKeys("Sylhet");
		Thread.sleep(1000);
		state.sendKeys("Sylhet");
		Thread.sleep(1000);
		zip.sendKeys("4562");
		Thread.sleep(1000);
		phoneNumber.sendKeys("87956");
		Thread.sleep(1000);
		//type.click();
		//Thread.sleep(1000);
		//selectType.click();
		//Thread.sleep(1000);
		email.sendKeys("@test.com");
		Thread.sleep(1000);
		note.sendKeys("test");
		Thread.sleep(2000);
		save.isEnabled();
		Thread.sleep(5000);


		WebElement phoneMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Phone Number minimum 10 digit required')]")));
		WebElement emailMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'E-mail must be valid')]")));

		String expectedText4 = "Phone Number minimum 10 digit required";
		String actualText4 = phoneMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

		String expectedText5 = "E-mail must be valid";
		String actualText5 = emailMsg.getText();
		Assert.assertEquals(actualText5, expectedText5);

		Thread.sleep(3000);

	}

	//Adding location form --- back button check

	@Test
	public void validateBackButton() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement back = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Back']")));
		Thread.sleep(2000);

		back.click();

		Assert.assertTrue(true);

		Thread.sleep(3000);

	}


	//Adding location form --- cancel button check

	@Test
	public void validateCancelButton() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Cancel']")));
		Thread.sleep(2000);
		cancel.click();

		WebElement cancelPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Yes']")));
		Thread.sleep(2000);
		cancelPopUp.click();

		Assert.assertTrue(true);

		Thread.sleep(3000);

	}

	//Add patient info after adding new location info

	@Test
	public void addPatientfromLocation() throws InterruptedException {

		validLocationData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement addButton1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/button[1]/span[1]/i[1]"));
		Thread.sleep(2000);
		addButton1.click();
		WebElement addPatientButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/span[1]/strong[1]"));
		Thread.sleep(1000);
		addPatientButton.click();
		Thread.sleep(1000);
		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/div[3]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[3]/button[2]/span[1]")));

		FirstName.sendKeys("Testing");
		Thread.sleep(2000);
		LastName.sendKeys("data");
		Thread.sleep(2000);
		ssn.sendKeys("1234568");
		Thread.sleep(2000);
		phoneNumber.sendKeys("1234567895");
		Thread.sleep(2000);
		email.sendKeys("test@email.com");
		Thread.sleep(2000);

		submit.click();
		Thread.sleep(5000);

		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://zntral.net/patients/"));
		Thread.sleep(3000);

	}

	//Add patient info after adding new location info -- Empty fields

	@Test
	public void emptyPatientInfofromLocation() throws InterruptedException {

		validLocationData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement addButton1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/button[1]/span[1]/i[1]"));
		Thread.sleep(2000);
		addButton1.click();
		WebElement addPatientButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/button[1]/span[1]/strong[1]"));
		Thread.sleep(1000);
		addPatientButton.click();
		Thread.sleep(1000);
		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/div[3]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[3]/button[2]/span[1]")));

		FirstName.sendKeys("");
		Thread.sleep(2000);
		LastName.sendKeys("");
		Thread.sleep(2000);
		ssn.sendKeys("");
		Thread.sleep(2000);
		phoneNumber.sendKeys("");
		Thread.sleep(2000);
		email.sendKeys("");
		Thread.sleep(2000);

		submit.isEnabled();
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

	//Add contact info after adding new location info

	@Test
	public void addContactfromLocation() throws InterruptedException {

		validLocationData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement addButton1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/button[1]/span[1]/i[1]"));
		Thread.sleep(2000);
		addButton1.click();
		WebElement addPatientButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]/span[1]/strong[1]"));
		Thread.sleep(1000);
		addPatientButton.click();
		Thread.sleep(1000);
		WebElement FirstName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		FirstName.sendKeys("Test2");
		Thread.sleep(1000);
		WebElement phoneNumber = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		phoneNumber.sendKeys("1234567895");
		Thread.sleep(1000);
		WebElement email = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		email.sendKeys("test@email.com");
		Thread.sleep(1000);
		WebElement save = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[3]/button[1]/span[1]"));

		save.click();
		Thread.sleep(5000);

		WebElement contactButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Contacts')]")));
		contactButton.click();

		WebElement firstRow = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
		String expectedText = "Test2";
		String actualText = firstRow.getText();
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(2000);

		String expectedText1 = firstRow.getText();
		firstRow.click();
		Thread.sleep(1000);
		WebElement firstRowInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-card__title headline font-weight-bold']")));
		String actualText1 = firstRowInfo.getText();
		Assert.assertEquals(actualText1, expectedText1);
		Thread.sleep(2000);

		WebElement closeInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='text-capitalize v-btn v-btn--flat v-btn--text theme--light v-size--default primary--text']")));
		closeInfo.click();

	}

	//Add contact info after adding new location info with empty fields

	@Test
	public void addContactEmptyLocation() throws InterruptedException {

		validLocationData();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement addButton1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/button[1]/span[1]/i[1]"));
		Thread.sleep(2000);
		addButton1.click();
		WebElement addPatientButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[1]/span[1]/strong[1]"));
		Thread.sleep(1000);
		addPatientButton.click();
		Thread.sleep(1000);
		WebElement FirstName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		FirstName.sendKeys("");
		Thread.sleep(1000);
		WebElement phoneNumber = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		phoneNumber.sendKeys("");
		Thread.sleep(1000);
		WebElement email = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		email.sendKeys("");
		Thread.sleep(1000);
		WebElement save = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[3]/button[1]/span[1]"));

		save.isEnabled();
		Thread.sleep(5000);

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-messages__message']")));
		String expectedText4 = "First name is required";
		String actualText4 = firstNameMsg.getText();
		Assert.assertEquals(actualText4, expectedText4);

	}

	//Edit location info

	@Test
	public void editLocationInfo() throws InterruptedException {
		locationList();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement locationSelect = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
		locationSelect.click();
		Thread.sleep(2000);

		WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='more_vert']")));
		editButton.click();
		Thread.sleep(1000);

		WebElement editOptionSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Edit')]")));
		editOptionSelect.click();
		Thread.sleep(1000);

		WebElement editLicenceNumber = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		editLicenceNumber.clear();
		Thread.sleep(2000);

		while (!editLicenceNumber.getAttribute("value").equals("")) {
			editLicenceNumber.sendKeys(Keys.BACK_SPACE);
		}
		Thread.sleep(1000);
		editLicenceNumber.sendKeys("7895213");

		WebElement editCapacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[8]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		editCapacity.clear();
		Thread.sleep(2000);

		while (!editCapacity.getAttribute("value").equals("")) {
			editCapacity.sendKeys(Keys.BACK_SPACE);
		}
		Thread.sleep(1000);
		editCapacity.sendKeys("5213");

		Thread.sleep(1000);

		WebElement update = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='update']")));
		update.click();
		Thread.sleep(3000);

		Assert.assertTrue(true);

	}

	//Delete location info

	@Test
	public void deleteLocationInfo() throws InterruptedException {
		locationList();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement locationSelect = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
		locationSelect.click();
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
		Assert.assertTrue(URL.contains("https://zntral.net/locations"));
		Thread.sleep(3000);
	}

	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}

}
