package normalUser;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Random;

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

import NormalUserXpath.UsersXpath;
import browser.OpenBrowser;
import normalUserFunctions.UsersFunctions;
import normalUserInputData.UsersInfoData;

public class Users  extends OpenBrowser{

	public static String env = "Test";
	public static String testSuiteName = "Test Suit 3 -- Users";
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

	public static String myBrowser = "chrome";
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
	public void loginUser() throws InterruptedException {

		UsersFunctions.verifyLogin();

		String expectedUrl = "https://dev.zntral.net/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//View the user list

	@Test(priority = 2)
	public void userList() throws InterruptedException {

		UsersFunctions.verifyLogin();

		UsersFunctions.usersList();

		String expectedUrl = "https://dev.zntral.net/users";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Add new user form

	@Test(priority = 3)
	public void addUser() throws InterruptedException{

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();

		UsersFunctions.usersList();

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();
		Thread.sleep(2000);

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		Thread.sleep(2000);
		addUser.click();

		Assert.assertTrue(true);
		Thread.sleep(3000);

	}

	//Adding user with valid info

	@Test(priority = 4)
	public void validUserData() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();

		UsersFunctions.usersList();

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.save)));


		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000);  

		String firstName = UsersInfoData.FirstName;
		String lastName = UsersInfoData.LastName;
		String EmailId = "username"+ randomInt +"@gmail.com";

		UsersFunctions.addUsers(firstName, lastName, EmailId);


		try {
			save.click();
			Thread.sleep(7000);

			WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.search)));
			search.sendKeys(EmailId);
			Thread.sleep(5000);

			WebElement firstRow = driver.findElement(By.xpath(UsersXpath.firstRow));
			String expectedText = EmailId;
			String actualText = firstRow.getText();
			Assert.assertEquals(actualText, expectedText);

		}catch(Exception e) {
			Assert.assertTrue(false);
		}
	}

	//Already existed user

	@Test(priority = 5)
	public void existedUser() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();

		UsersFunctions.usersList();

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();


		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.save)));

		String firstName = UsersInfoData.FirstName;
		String lastName = UsersInfoData.LastName;
		String EmailId = UsersInfoData.emailId;

		UsersFunctions.addUsers(firstName, lastName, EmailId);

		save.click();
		Thread.sleep(2000);

		try {
			WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.status)));
			status.isDisplayed();

		} catch (Exception e){
		}

	}
	//Add new user form -- validation

	@Test(priority = 6)
	public void validateNewUser() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();

		UsersFunctions.usersList();

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();


		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.FirstName)));
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.LastName)));
		WebElement emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.emailId)));
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.save)));

		String firstName = UsersInfoData.invalidFirstName;
		String lastName = UsersInfoData.invalidLastName;
		String EmailId = UsersInfoData.invalidemailId;

		UsersFunctions.addUsers(firstName, lastName, EmailId);

		try {
			save.click();
			Thread.sleep(1000);	

		} 
		catch (Exception e){
			if(FirstName.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.invalidMsg)));
				Assert.assertTrue(true);

			}
			else if(LastName.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.invalidMsg)));

				Assert.assertTrue(true);

			}
			else if(emailId.getAttribute("value").isEmpty()) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.invalidMsg)));

				Assert.assertTrue(true);
			}
			else if(emailId.getAttribute("value").startsWith("@")) {
				save.isDisplayed();
				WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.invalidMsg)));

				Assert.assertTrue(true);
			}
			else {
				save.isDisplayed();
				Assert.assertTrue(true);
			}
		}
	}



	// Check Cancel button on add new user form

	@Test(priority = 7)
	public void cancelButton() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();

		UsersFunctions.usersList();

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();

		WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.cancel)));
		cancel.click();
		Assert.assertTrue(true);
	}

	//Check close button on add new user form

	@Test(priority = 8)
	public void closeButton() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();

		UsersFunctions.usersList();

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.add)));
		add.click();

		WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.addUser)));
		addUser.click();

		WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.close)));
		close.click();
		Assert.assertTrue(true);

	}

	//Search option

	@Test(priority = 9)
	public void search() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();

		UsersFunctions.usersList();

		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.search)));
		search.sendKeys(UsersInfoData.search);
		Thread.sleep(5000);

		WebElement firstRow = driver.findElement(By.xpath(UsersXpath.firstRow));
		String expectedText = UsersInfoData.search;
		String actualText = firstRow.getText();
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(3000);
	}

	//User profile 

	@Test(priority = 10)
	public void profileOfUser() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		UsersFunctions.verifyLogin();

		UsersFunctions.usersList();

		WebElement firstRowInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.firstRowInfo)));
		firstRowInfo.click();

		String actualText = "Users";

		WebElement user =driver.findElement(By.xpath(UsersXpath.user));
		String ExpectedText =user.getText();	
		Assert.assertTrue(ExpectedText.contains(actualText));
		Thread.sleep(3000);
	}


	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}

}
