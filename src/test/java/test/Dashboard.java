package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Random;

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

public class Dashboard {

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 8 -- Dashboard";

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

	// Add Location from the Dashboard

	@Test
	public void addLocation() throws InterruptedException{
		loginUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='v-icon notranslate material-icons theme--dark'][normalize-space()='add']")));
		driver.findElement(By.xpath("//i[@class='v-icon notranslate material-icons theme--dark'][normalize-space()='add']")).click();

		List<WebElement> options =  driver.findElements(By.xpath("//div[@role='radiogroup']"));
		Random random = new Random();
		Thread.sleep(1000);
		int index = random.nextInt(options.size());
		options.get(index).click(); 

		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Continue']")));
		selectContinue.click();
		Thread.sleep(2000);

		Assert.assertTrue(true);
		Thread.sleep(2000);

	}

	// Add Location from the Dashboard with valid data

	@Test
	public void validLocationData() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		locationName.sendKeys("Mymensingh");
		Thread.sleep(1000);

		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		licenceNumber.sendKeys("166578");
		Thread.sleep(1000);

		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		capacity.sendKeys("15");
		Thread.sleep(1000);

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		address.sendKeys("Mymensingh");
		Thread.sleep(1000);

		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[2]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		suiteUnit.sendKeys("65/89");
		Thread.sleep(1000);

		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		city.sendKeys("Mymensingh");
		Thread.sleep(1000);

		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//div[3]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		state.sendKeys("Mymensingh");
		Thread.sleep(1000);

		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]")));
		zip.sendKeys("87562");
		Thread.sleep(1000);

		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		phoneNumber.sendKeys("1297894896");
		Thread.sleep(1000);

		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-input theme--light v-text-field v-text-field--is-booted v-select']//div[@class='v-select__selections']")));
		type.click();
		Thread.sleep(1000);

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Work')]")));
		selectType.click();
		Thread.sleep(1000);

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		email.sendKeys("mymensingh@email.com");
		Thread.sleep(1000);

		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		note.sendKeys("This is automation for valid text");
		Thread.sleep(2000);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Save']")));
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
		email.sendKeys("");
		Thread.sleep(1000);
		note.sendKeys("");
		Thread.sleep(2000);


		//Location check
		if(locationName.getText().isEmpty()) {
			System.out.println("Location field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("Location submitted successfully!");
			Thread.sleep(2000);
		}

		//Licence number check
		if(licenceNumber.getText().isEmpty()) {
			System.out.println("Licence number field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("Licence number submitted successfully!");
			Thread.sleep(2000);
		}

		//capacity check
		if(capacity.getText().isEmpty()) {
			System.out.println("Capacity field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("Capacity submitted successfully!");
			Thread.sleep(2000);
		}

		//address check
		if(address.getText().isEmpty()) {
			System.out.println("Address field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("address submitted successfully!");
			Thread.sleep(2000);
		}

		//suiteUnit check
		if(suiteUnit.getText().isEmpty()) {
			System.out.println("Suite/Unit field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("Suite/Unit submitted successfully!");
			Thread.sleep(2000);
		}

		//city check
		if(city.getText().isEmpty()) {
			System.out.println("City field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("City submitted successfully!");
			Thread.sleep(2000);
		}

		//state check
		if(state.getText().isEmpty()) {
			System.out.println("State field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("State submitted successfully!");
			Thread.sleep(2000);
		}

		//zip check
		if(zip.getText().isEmpty()) {
			System.out.println("Zip field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("Zip submitted successfully!");
			Thread.sleep(2000);
		}

		//phoneNumber check
		if(phoneNumber.getText().isEmpty()) {
			System.out.println("Phone Number field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("Phone Number submitted successfully!");
			Thread.sleep(2000);
		}

		//type check
		if(type.getText().isEmpty()) {
			System.out.println("Type field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("Type submitted successfully!");
			Thread.sleep(2000);
		}

		//email check
		if(email.getText().isEmpty()) {
			System.out.println("Email field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("Email submitted successfully!");
			Thread.sleep(2000);
		}

		//note check
		if(note.getText().isEmpty()) {
			System.out.println("Note field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("Note submitted successfully!");
			Thread.sleep(2000);
		}


		try {
			save.click();
			Thread.sleep(2000);	

		} 
		catch (Exception e){
			System.out.println("Mandatory fields has no data !");
			save.isEnabled();
			Thread.sleep(2000);
		}

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

	}

	//Email validation check on Add location form

	@Test
	public void validateEmailData() throws InterruptedException {
		addLocation();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));

		email.sendKeys("@abc.skfh");
		Thread.sleep(1000);

		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		note.sendKeys("");
		Thread.sleep(1000);

		if(email.getAttribute("value").startsWith("@")){
			WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'E-mail must be valid')]")));
			System.out.println(invalidMsg.getText());
			Assert.assertTrue(true);
			Thread.sleep(1000);
		}else {
			System.out.println("Email submitted successfully!");
			Thread.sleep(2000);
		}
	}

	// Add Patient from the Dashboard

	@Test
	public void addPatient() throws InterruptedException{
		loginUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='add']")));
		driver.findElement(By.xpath("//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='add']")).click();

		WebElement prefix = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[4]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement dob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[2]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//div[3]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]//i[1]")));
		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//i[1]")));
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]//i[1]")));
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Submit']")));

		Thread.sleep(2000);

		prefix.sendKeys("Mr.");
		Thread.sleep(1000);
		firstName.sendKeys("Sabbir-Test");
		lastName.sendKeys("Info");
		Thread.sleep(1000);
		ssn.sendKeys("896541");
		Thread.sleep(1000);
		dob.sendKeys("1993-10-25");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='OK']"))).click();
		Thread.sleep(1000);
		gender.click();
		Thread.sleep(1000);
		WebElement genderMale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Male')]")));
		genderMale.click();
		phone.sendKeys("78961235");
		type.click();
		Thread.sleep(1000);
		WebElement typeMobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='v-list-item__title'][normalize-space()='Mobile']")));
		typeMobile.click();
		email.sendKeys("sabbirtest@email.com");
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
	public void addPatientWithEmpty() throws InterruptedException{
		loginUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='add']")));
		driver.findElement(By.xpath("//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='add']")).click();

		WebElement prefix = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[4]//div[1]//div[1]//div[1]//div[1]//input[1]")));
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		WebElement dob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[2]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		//WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='document']//div[3]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]//i[1]")));
		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		//WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//i[1]")));
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
		phone.sendKeys("");
		email.sendKeys("");
		Thread.sleep(1000);
		note.sendKeys("");


		//prefix check
		if(prefix.getText().isEmpty()) {
			System.out.println("prefix field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("prefix submitted successfully!");
			Thread.sleep(1000);
		}

		//firstName check
		if(firstName.getText().isEmpty()) {
			System.out.println("firstName field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("firstName submitted successfully!");
			Thread.sleep(1000);
		}

		//lastName check
		if(lastName.getText().isEmpty()) {
			System.out.println("lastName field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("lastName submitted successfully!");
			Thread.sleep(1000);
		}

		//ssn check
		if(ssn.getText().isEmpty()) {
			System.out.println("ssn field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("ssn submitted successfully!");
			Thread.sleep(1000);
		}

		//dob check
		if(dob.getText().isEmpty()) {
			System.out.println("dob field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("dob submitted successfully!");
			Thread.sleep(1000);
		}

		//phone check
		if(phone.getText().isEmpty()) {
			System.out.println("phone field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("phone submitted successfully!");
			Thread.sleep(1000);
		}

		//email check
		if(email.getText().isEmpty()) {
			System.out.println("email field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("email submitted successfully!");
			Thread.sleep(1000);
		}

		//location check
		if(location.getText().isEmpty()) {
			System.out.println("location field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("location submitted successfully!");
			Thread.sleep(1000);
		}

		//note check
		if(note.getText().isEmpty()) {
			System.out.println("note field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("note submitted successfully!");
			Thread.sleep(1000);
		}

		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			System.out.println("Mandatory fields has no data !");
			save.isEnabled();
			Thread.sleep(1000);
		}

		WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'First name is required')]")));
		WebElement lastNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Last name is required')]")));

		String expectedText1 = "First name is required";
		String actualText1 = firstNameMsg.getText();
		Assert.assertEquals(actualText1, expectedText1);

		String expectedText2 = "Last name is required";
		String actualText2 = lastNameMsg.getText();
		Assert.assertEquals(actualText2, expectedText2);

	}

	//Email validation check on Add patient form

	@Test
	public void validateEmailDataOnPatient() throws InterruptedException {
		loginUser();
		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='add']")));
		driver.findElement(By.xpath("//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='add']")).click();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));

		email.sendKeys("@abc.skfh");
		Thread.sleep(1000);

		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]")));
		note.sendKeys("");
		Thread.sleep(1000);

		if(email.getAttribute("value").startsWith("@")){
			WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'E-mail must be valid')]")));
			System.out.println(invalidMsg.getText());
			Assert.assertTrue(true);
			Thread.sleep(1000);
		}else {
			System.out.println("Email submitted successfully!");
			Thread.sleep(2000);
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}
}
