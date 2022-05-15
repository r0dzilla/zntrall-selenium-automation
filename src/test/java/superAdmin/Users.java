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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browser.OpenBrowser;
import superAdminFunctions.UsersFunctions;
import superAdminInputData.UsersInfoData;
import superAdminXpath.UsersXpath;


public class Users extends OpenBrowser{

	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 3 -- Users";
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


	//Verifying elements on Login page
	@Test(priority = 1)
	public void verifyElemntsOnPageTest() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement signInTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.signInTitle)));
		signInTitle.isDisplayed();

	}

	//login with valid username & Password

	@Test(priority = 2)
	public void login() throws InterruptedException {

		UsersFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Check user list

	@Test(priority = 3)
	public void checkUserList() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		String expectedUrl = "https://dev.zntral.net/users";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}


	//Check user profile

	@Test(priority = 4)
	public void checkUserProfile() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebElement usersName = driver.findElement(By.xpath(UsersXpath.usersName));
		String userss = usersName.getText();
		usersName.click();
		Thread.sleep(5000);

		WebElement userNameVerify = driver.findElement(By.xpath(UsersXpath.userNameVerify));

		if(userNameVerify.getText().contains(userss)) {
			Assert.assertTrue(true);
		}else {
		}

	}

	//Check client profile from user profile

	@Test(priority = 5)
	public void checkClientProfileFromUser() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebElement usersName = driver.findElement(By.xpath(UsersXpath.usersName));
		usersName.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement clickUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.clickUser)));
		clickUser.click();
		Assert.assertTrue(true);

	}

	//Check client profile status change (Active to inactive)

	@Test(priority = 6)
	public void changeStatusToInactive() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebElement usersName = driver.findElement(By.xpath(UsersXpath.usersName));
		usersName.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement clickUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.clickUser)));
		clickUser.click();

		WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.status)));
		status.click();
		WebElement statusInactive = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.statusInactive)));
		statusInactive.click();
		Assert.assertTrue(true);

	}


	//Check client profile status change (InActive to active)

	@Test(priority = 7)
	public void changeStatusToActive() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebElement usersName = driver.findElement(By.xpath(UsersXpath.usersName));
		usersName.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement clickUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.clickUser)));
		clickUser.click();

		WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.status)));
		status.click();
		WebElement statusActive = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.statusActive)));
		statusActive.click();
		Assert.assertTrue(true);

	}

	//Search option

	@Test(priority = 8)
	public void search() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.search)));

		String searchUser = UsersInfoData.searchUser;
		search.sendKeys(searchUser);
		WebElement firstRow = driver.findElement(By.xpath(UsersXpath.firstRow));	
		String actualText = firstRow.getText();
		Assert.assertTrue(actualText.contains(search.getText()));
	}

	//First Name wise sort

	@Test(priority = 9)
	public void firstNameSort() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.selectName)));
		selectName.click();
		selectName.click();
		selectName.click();
		Assert.assertTrue(true);
	}

	//Last name wise sort

	@Test(priority = 10)
	public void lastNameSort() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.selectLastName)));
		selectName.click();
		selectName.click();;
		selectName.click();
		Assert.assertTrue(true);
	}

	//email wise sort

	@Test(priority = 11)
	public void emailSort() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement selectEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.selectEmail)));
		selectEmail.click();
		selectEmail.click();
		selectEmail.click();
		Assert.assertTrue(true);
	}

	//Check user type list

	@Test(priority = 12)
	public void checkUserTypeList() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebElement element = driver.findElement(By.xpath(UsersXpath.element));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		String expectedText = "Types";
		String actualText = element.getText();
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(3000);
	}



	//add user group

	@Test(priority = 13)
	public void userGroup() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		String name = UsersInfoData.admin;
		String acronym = UsersInfoData.acronym;
		String viewTitle = UsersInfoData.viewTitle;
		String viewAcronym = UsersInfoData.viewAcronym;
		String desc = UsersInfoData.description;

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUserGroup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUserGroup)));
		addUserGroup.click();

		WebElement adminName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.adminName)));
		adminName.sendKeys(name);

		WebElement adminAcronym = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.adminAcronym)));
		adminAcronym.sendKeys(acronym);

		WebElement statusCheck = driver.findElement(By.xpath(UsersXpath.statusCheck));

		if(statusCheck.isSelected()) {
			Assert.assertTrue(true);
		}else {
			statusCheck.click();
		}

		WebElement userViewTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.userViewTitle)));
		userViewTitle.sendKeys(viewTitle);

		WebElement userViewAcronym = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.userViewAcronym)));
		userViewAcronym.sendKeys(viewAcronym);

		WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.description)));
		description.sendKeys(desc);

		//user permission select
		UsersFunctions.permissionUsers();

		// Location permission select
		UsersFunctions.permissionLocation();

		//Patient permission select
		UsersFunctions.permissionPatient();

		//Calendar permission select
		UsersFunctions.permissionCalendar();

		//Contact permission select
		UsersFunctions.permissionContact();

		//Forms permission select
		UsersFunctions.permissionForms();


		WebElement addGroup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addGroup)));
		addGroup.click();
		Thread.sleep(5000);
		String actualAdminName = name;
		WebElement lastRow = driver.findElement(By.xpath(UsersXpath.lastRow)); 
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);",lastRow);
		Thread.sleep(5000);
		String expectedAdminName = lastRow.getText();

		//Admin name verification
		Assert.assertEquals(actualAdminName, expectedAdminName);

		Thread.sleep(2000);
	}

	//Update user group

	@Test(priority = 14)
	public void updateUserGroup() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement lastRowValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.lastRow)));
		js.executeScript("arguments[0].scrollIntoView(true);",lastRowValue);

		lastRowValue.click();

		WebElement adminName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.adminName)));
		while (!adminName.getAttribute("value").equals("")) {
			adminName.sendKeys(Keys.BACK_SPACE);
		}

		adminName.sendKeys("TEST");

		WebElement adminAcronym = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.adminAcronym)));
		while (!adminAcronym.getAttribute("value").equals("")) {
			adminAcronym.sendKeys(Keys.BACK_SPACE);
		}

		adminAcronym.sendKeys("TEST");

		WebElement update = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.update)));
		update.click();

		WebElement statusMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='status']")));
		statusMsg.isDisplayed();
		System.out.println(statusMsg.getText());
		
		Thread.sleep(2000);

	}

	//Check user card from user profile

	@Test(priority = 15)
	public void checkUserCardFromUser() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.userList();

		WebElement usersName = driver.findElement(By.xpath(UsersXpath.usersName));
		usersName.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement cardUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.cardUser)));
		String cardUserTitle = cardUser.getText();
		WebElement clickUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.clickUser2)));
		String UserTitle = clickUser.getText();
		Assert.assertEquals(UserTitle, cardUserTitle);
		Thread.sleep(3000);
	}
}
