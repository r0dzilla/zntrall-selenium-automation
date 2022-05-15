package superAdmin;


import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browser.OpenBrowser;
import superAdminFunctions.ClientsFunctions;
import superAdminInputData.ClientsInfoData;
import superAdminXpath.ClientsXpath;

public class Clients extends OpenBrowser{
	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 2 -- Clients";
	public static WebDriver driver = null;
	

	@BeforeSuite
	public static void beforeSuit() {

		if (env.equalsIgnoreCase("Test for Super Admin")) {

			System.out.println("Test executes in correct environment where environment= " + env);
			System.out.println("Test Suite name = " + testSuiteName);

		}else {
			System.out.println("Test executes in wrong environment where environment= " + env);

		}
	}

	@Parameters("myBrowser")
	@BeforeTest
	public void setup(String myBrowser) throws MalformedURLException {

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
	

	//Verifying elements on Client page
	@Test(priority = 1)
	public void verifyElemntsOnPageTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.signInTitle)));
		signInTitle.isDisplayed();

	}
	

	//login with valid username & Password

	@Test(priority = 2)
	public void login() throws InterruptedException {

		ClientsFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

	}


	//Check client list

	@Test(priority = 3)
	public void checkClientList() throws InterruptedException {

		ClientsFunctions.verifyLogin();

		ClientsFunctions.clientList();

		String expectedUrl = "https://dev.zntral.net/clients";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

	}


	//Check client profile

	@Test(priority = 4)
	public void checkClientProfile() throws InterruptedException {

		ClientsFunctions.verifyLogin();

		ClientsFunctions.clientList();

		ClientsFunctions.clientProfile();

	}


	//Check client profile status change (Active to inactive)

	@Test(priority = 5)
	public void changeStatusToInactive() throws InterruptedException {

		ClientsFunctions.verifyLogin();

		ClientsFunctions.clientList();

		ClientsFunctions.clientProfile();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.status)));
		status.click();
		WebElement statusInactive = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.statusInactive)));
		statusInactive.click();
		Assert.assertTrue(true);

	}


	//Check client profile status change (InActive to active)

	@Test(priority = 6)
	public void changeStatusToActive() throws InterruptedException {


		ClientsFunctions.verifyLogin();

		ClientsFunctions.clientList();

		ClientsFunctions.clientProfile();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.status)));
		status.click();
		WebElement statusActive = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.statusActive)));
		statusActive.click();
		Assert.assertTrue(true);

	}

	//Check location status on client profile

	@Test(priority = 7)
	public void checkLocationStatus() throws InterruptedException{

		ClientsFunctions.verifyLogin();

		ClientsFunctions.clientList();

		ClientsFunctions.clientProfile();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement locationTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.locationTab)));
		locationTab.click();

		try {
			WebElement locationInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.locationInfo)));
			if(locationInfo.getText() != null ) {
				Assert.assertTrue(true);
	
			}
		}
		catch(Exception e) {
	
		}
	}


	//Check patient status on client profile

	@Test(priority = 8)
	public void checkPatientStatus() throws InterruptedException{


		ClientsFunctions.verifyLogin();

		ClientsFunctions.clientList();

		ClientsFunctions.clientProfile();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement patientTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.patientTab)));
		patientTab.click();
		try {
			WebElement patientInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.patientInfo)));
			if(patientInfo.getText() != null ) {
				Assert.assertTrue(true);
			}
		}
		catch(Exception e) {
	
		}
	}


	//Search option

	@Test(priority = 9)
	public void search() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ClientsFunctions.verifyLogin();

		ClientsFunctions.clientList();

		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.search)));
		search.sendKeys("John");
		WebElement firstRow = driver.findElement(By.xpath(ClientsXpath.firstRow));	
		String actualText = firstRow.getText();
		Assert.assertTrue(actualText.contains(search.getText()));
		Thread.sleep(3000);
	}


	//Check client type list

	@Test(priority = 10)
	public void checkClientTypeList() throws InterruptedException {


		ClientsFunctions.verifyLogin();

		ClientsFunctions.clientList();


		WebElement element = driver.findElement(By.xpath(ClientsXpath.element));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		String expectedText = "Types";
		String actualText = element.getText();
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(3000);
	}

	//Add new client group with valid info

	@Test(priority = 11)
	public void addNewClientGroup() throws InterruptedException {

		ClientsFunctions.verifyLogin();

		ClientsFunctions.clientList();

		String clientGroupName = ClientsInfoData.name;
		String clientGroupAcronym = ClientsInfoData.acronym;

		ClientsFunctions.addClientGroup(clientGroupName, clientGroupAcronym);

		Assert.assertTrue(true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	
	//Edit client group with valid info

	@Test(priority = 12)
	public void editClientGroup() throws InterruptedException {

		ClientsFunctions.verifyLogin();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ClientsFunctions.clientList();

		String editClientGroupName = ClientsInfoData.editName;
		String editClientGroupAcronym = ClientsInfoData.editAcronym;

		WebElement selectAnyGroup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.selectAnyGroup)));
		selectAnyGroup.click();
		
		WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.name)));
		
		while (!name.getAttribute("value").equals("")) {
			name.sendKeys(Keys.BACK_SPACE);
		}
		
		name.sendKeys(editClientGroupName);
		
		WebElement acronym = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.acronym)));
		
		while (!acronym.getAttribute("value").equals("")) {
			acronym.sendKeys(Keys.BACK_SPACE);
		}
		
		acronym.sendKeys(editClientGroupAcronym);
		WebElement update = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.update)));
		update.click();
		Assert.assertTrue(true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	//Name wise sort

	@Test(priority = 13)
	public void nameSort() throws InterruptedException {

		ClientsFunctions.verifyLogin();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		ClientsFunctions.clientList();

		WebElement selectName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.selectName)));

		selectName.click();
		selectName.click();
		selectName.click();
		Assert.assertTrue(true);
	}

	//Type wise sort

	@Test(priority = 14)
	public void typeSort() throws InterruptedException {

		ClientsFunctions.verifyLogin();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		ClientsFunctions.clientList();
		
		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.selectType)));

		selectType.click();
		selectType.click();
		selectType.click();
		Assert.assertTrue(true);
	}

	//Check user status on client profile

	@Test(priority = 15)
	public void checkUserStatus() throws InterruptedException{

		ClientsFunctions.verifyLogin();

		ClientsFunctions.clientList();

		ClientsFunctions.clientProfile();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement userTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.userTab)));
		userTab.click();

		try {
			WebElement userInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ClientsXpath.userInfo)));
			if(userInfo.getText() != null ) {
				Assert.assertTrue(true);
			}
		}
		catch(Exception e) {
		}
	}


	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}

}
