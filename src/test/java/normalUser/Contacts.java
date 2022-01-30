package normalUser;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import NormalUserXpath.ContactsXpath;
import browser.OpenBrowser;
import normalUserInputData.ContactsInfoData;

public class Contacts extends OpenBrowser{
	public static String env = "Test";
	public static String testSuiteName = "Test Suit 7 -- Contacts";
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

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.pass)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.login)));

		String user = ContactsInfoData.user;
		String pass = ContactsInfoData.pass;

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

	//View the contacts list

	@Test(priority = 2)
	public void contactList() throws InterruptedException {

		loginUser();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.contacts)));
		driver.findElement(By.xpath(ContactsXpath.contacts)).click();
		Thread.sleep(3000);
		String expectedUrl = "https://dev.zntral.net/contacts";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}

	//Search option

	@Test(priority = 3)
	public void search() throws InterruptedException {
		contactList();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.search)));
		String searchUser = ContactsInfoData.search;
		search.sendKeys(searchUser);
		WebElement firstRow = driver.findElement(By.xpath(ContactsXpath.firstRow));	
		String actualText = firstRow.getText();
		String expectedText = searchUser;
		Assert.assertEquals(actualText, expectedText);
		Thread.sleep(3000);
	}

	//Add new Contact -- Click "+" button

	@Test(priority = 4)
	public void addContact() throws InterruptedException{
		contactList();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.add)));
		add.click();
		Thread.sleep(2000);
		Assert.assertTrue(true);
	}

	//Add contact with valid info

	@Test(priority = 5)
	public void validContactData() throws InterruptedException {
		addContact();

		String first = ContactsInfoData.firstName;
		String last = ContactsInfoData.lastName;
		String phn = ContactsInfoData.phone;
		String mail = ContactsInfoData.email;
		String title2 = ContactsInfoData.title;
		String note2 = ContactsInfoData.note;

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.firstName)));
		firstName.sendKeys(first);

		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.lastName)));
		lastName.sendKeys(last);

		WebElement genderDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.genderDropDown)));
		genderDropDown.click();

		WebElement gender= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.gender)));
		gender.click();

		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.phone)));
		phone.sendKeys(phn);

		WebElement typeDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.typeDropDown)));
		typeDropDown.click();

		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.type)));
		type.click();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.email)));
		email.sendKeys(mail);

		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.title)));
		title.sendKeys(title2);

		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.note)));
		note.sendKeys(note2);

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.save)));
		save.click();
		Thread.sleep(5000);

		WebElement firstRow = driver.findElement(By.xpath(ContactsXpath.firstRow));	
		String actualText = firstRow.getText();
		String expectedText = first;
		Assert.assertTrue(actualText.contains(expectedText));
		Thread.sleep(3000);

	}

	//Add contact with empty/invalid field

	@Test(priority = 6)
	public void emptyContactData() throws InterruptedException {
		addContact();

		String first = ContactsInfoData.emptyfirstName;
		String last = ContactsInfoData.emptylastName;
		String phn = ContactsInfoData.emptyphone;
		String mail = ContactsInfoData.emptyemail;
		String title2 = ContactsInfoData.emptytitle;
		String note2 = ContactsInfoData.emptynote;


		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.firstName)));
		firstName.sendKeys(first);

		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.lastName)));
		lastName.sendKeys(last);

		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.phone)));
		phone.sendKeys(phn);

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.email)));
		email.sendKeys(mail);

		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.title)));
		title.sendKeys(title2);

		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.note)));
		note.sendKeys(note2);	

		//First name field check
		if(firstName.getText().isEmpty()) {
			WebElement firstNameMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.firstNameMsg)));
			String expectedText4 = "First name is required";
			String actualText4 = firstNameMsg.getText();
			Assert.assertEquals(actualText4, expectedText4);
			System.out.println(actualText4);
			Thread.sleep(1000);
		}
		else {
			System.out.println("First Name submitted successfully!");
		}


		//Last name field check
		if(lastName.getText().isEmpty()) {
			System.out.println("Last Name field is empty!");

		}
		else {
			System.out.println("Last Name submitted successfully!");

		}

		//Phone number field check		
		if(phone.getText().isEmpty()) {
			System.out.println("Phone number field is empty!");

		}
		else {
			System.out.println("Phone number submitted successfully!");

		}

		//Email field check		
		if(email.getText().isEmpty()) {
			System.out.println("Email field is empty!");

		}
		else {
			System.out.println("Data submitted successfully!");

		}

		Thread.sleep(3000);

		while (!email.getAttribute("value").equals("")) {
			email.sendKeys(Keys.BACK_SPACE);
		}

		String invalidmail = ContactsInfoData.invalidemail;
		email.sendKeys(invalidmail);

		note.sendKeys("");	

		//Title field check
		if(title.getText().isEmpty()) {
			System.out.println("Title field is empty!");
			Thread.sleep(1000);
		}
		else {
			System.out.println("Data submitted successfully!");

		}


		//Note field check
		if(note.getText().isEmpty()) {
			System.out.println("Note field is empty!");

		}
		else {
			System.out.println("Data submitted successfully!");

		}

		if(email.getAttribute("value").startsWith("@")){
			WebElement invalidMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.invalidMsg)));
			System.out.println(invalidMsg.getText());
			Assert.assertTrue(true);
			Thread.sleep(1000);
		}else {
			System.out.println("Email submitted successfully!");

		}

		//Save button check
		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.save)));

		try {
			save.click();

		} 
		catch (Exception e){
			System.out.println("Mandatory fields has no data !");
			save.isEnabled();
			Thread.sleep(2000);
		}


	}


	//Edit contact from the list

	@Test(priority = 7)
	public void editContact() throws InterruptedException {
		contactList();

		String editphone = ContactsInfoData.editphone;
		String edittitle = ContactsInfoData.edittitle;

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement dataForEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.dataForEdit)));
		dataForEdit.click();

		WebElement selectEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.selectEdit)));
		selectEdit.click();

		WebElement editPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.editPhone)));

		while (!editPhone.getAttribute("value").equals("")) {
			editPhone.sendKeys(Keys.BACK_SPACE);
		}
		editPhone.sendKeys(editphone);

		WebElement editTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.editTitle)));
		while (!editTitle.getAttribute("value").equals("")) {
			editTitle.sendKeys(Keys.BACK_SPACE);
		}

		editTitle.sendKeys(edittitle);
		Thread.sleep(3000);

		String expected = editphone;

		WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.save)));
		save.click();

		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.phoneNumber)));
		String actual = phoneNumber.getText();
		Thread.sleep(3000);
		System.out.println("actual: "+actual);
		System.out.println("expected: "+ expected);
		try {
			Assert.assertEquals(actual, expected);

		} catch(Exception e) {
			System.out.println("Phone number don't match");
		}

	}

	//Remove any contact from the list

	@Test(priority = 8)
	public void removeContact() throws InterruptedException {
		contactList();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement dataForEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.dataForEdit)));
		dataForEdit.click();

		WebElement selectEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.selectEdit)));
		selectEdit.click();

		WebElement remove = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.remove)));
		remove.click();
		Thread.sleep(2000);

		driver.switchTo().alert().accept();

		Assert.assertTrue(true);
	}

	//cancel button from Add new contact form

	@Test(priority = 9)
	public void cancelAddNewForm() throws InterruptedException {
		addContact();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.cancel)));
		cancel.click();
		Assert.assertTrue(true);
	}

	//close button from Add new contact form
	@Test(priority = 10)
	public void closeAddNewForm() throws InterruptedException {
		addContact();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.close)));
		close.click();
		Assert.assertTrue(true);
	}

	//close Edit contact form

	@Test(priority = 11)
	public void closeEditForm() throws InterruptedException {
		contactList();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement dataForEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.dataForEdit)));
		dataForEdit.click();
		WebElement selectEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.selectEdit)));
		selectEdit.click();
		WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.cancel)));
		cancel.click();
		WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.close)));
		close.click();
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
