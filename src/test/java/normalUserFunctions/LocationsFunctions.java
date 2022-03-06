package normalUserFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import NormalUserXpath.LocationsXpath;
import normalUser.Locations;
import normalUserInputData.LocationInfoData;
import superAdminXpath.UsersXpath;

public class LocationsFunctions extends Locations {

	static WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	public static void verifyLogin() throws InterruptedException {
		WebElement username = driver.findElement(By.xpath(LocationsXpath.username));
		WebElement password = driver.findElement(By.xpath(LocationsXpath.pass));
		WebElement login = driver.findElement(By.xpath(LocationsXpath.login));

		String user = LocationInfoData.user;
		String pass = LocationInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);
	}
	
	
	public static void verifySuperAdminLogin() throws InterruptedException {
		
		WebElement username = driver.findElement(By.xpath(LocationsXpath.username));
		WebElement password = driver.findElement(By.xpath(LocationsXpath.pass));
		WebElement login = driver.findElement(By.xpath(LocationsXpath.login));

		String user = LocationInfoData.adminUser;
		String pass = LocationInfoData.adminPass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);
	}
	
	public static void userList() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement users = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.users)));
		users.click();
		Thread.sleep(7000);
	}
	
	public static void viewLocationList() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.locations)));
		driver.findElement(By.xpath(LocationsXpath.locations)).click();
	}
	
	public static void addResidentType() throws InterruptedException {
		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.add)));
		add.click();
		
		WebElement selectResidentTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.selectResidentDropdown)));
		selectResidentTypeDropdown.click();

		WebElement selectResidentType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.selectResidentType)));
		selectResidentType.click();
		
		WebElement selectContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.selectContinue)));
		selectContinue.click();

		Assert.assertTrue(selectResidentType.isEnabled());
	}
	
	
	public static void addLocationData(String LName, String LlicenceNumber,String Lcapacity,String Laddress,String LsuiteUnit,String Lcity,String Lstate,String Lzip,String LphoneNumber,String Lemail,String Lnote) {
		
		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.locationName)));
		locationName.sendKeys(LName);
		
		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.licenceNumber)));
		licenceNumber.sendKeys(LlicenceNumber);
		
		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.capacity)));
		capacity.sendKeys(Lcapacity);
		
		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.address)));
		address.sendKeys(Laddress);
		
		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.suiteUnit)));
		suiteUnit.sendKeys(LsuiteUnit);
		
		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.city)));
		city.sendKeys(Lcity);
		
		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.state)));
		state.sendKeys(Lstate);
		
		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.zip)));
		zip.sendKeys(Lzip);
		
		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneNumber)));
		phoneNumber.sendKeys(LphoneNumber);
		
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.type)));
		type.click();
		
		WebElement typeSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.typeSelect)));
		typeSelect.click();
		
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.email)));
		email.sendKeys(Lemail);
		
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.note)));
		note.sendKeys(Lnote);
	}
	
	public static void addPatient(String PFirstName, String PLastName,String Pssn,String PphoneNumber2,String Pemail2) {
		
		WebElement FirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.FirstName)));
		FirstName.sendKeys(PFirstName);
		WebElement LastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.LastName)));
		LastName.sendKeys(PLastName);
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.ssn)));
		ssn.sendKeys(Pssn);
		WebElement phoneNumber2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.phoneNumber2)));
		phoneNumber2.sendKeys(PphoneNumber2);
		WebElement email2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocationsXpath.email)));
		email2.sendKeys(Pemail2);
	}
}
