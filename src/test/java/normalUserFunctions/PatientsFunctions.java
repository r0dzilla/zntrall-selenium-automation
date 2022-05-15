package normalUserFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import NormalUserXpath.PatientsXpath;
import normalUser.Patients;
import normalUserInputData.PatientsInfoData;

public class PatientsFunctions extends Patients{

	static WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	public static void verifyLogin() throws InterruptedException {
		
		WebElement username = driver.findElement(By.xpath(PatientsXpath.username));
		WebElement password = driver.findElement(By.xpath(PatientsXpath.password));
		WebElement login = driver.findElement(By.xpath(PatientsXpath.login));

		username.sendKeys(PatientsInfoData.user);

		password.sendKeys(PatientsInfoData.pass);

		login.click();
		Thread.sleep(5000);
	}
	
	public static void viewPatientList() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.patients)));
		driver.findElement(By.xpath(PatientsXpath.patients)).click();
	}
	
	
	public static void addPatientData(String Pprefix, String PfirstName, String PlastName, String Pssn,String Pdob,String Pphone,String Pemail,String Pnote) throws InterruptedException {
		
		WebElement prefix = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.prefix)));
		prefix.sendKeys(Pprefix);
		
		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.firstName)));
		firstName.sendKeys(PfirstName);
		
		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.lastName)));
		lastName.sendKeys(PlastName);
		
		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.ssn)));
		ssn.sendKeys(Pssn);
		
		WebElement dob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.dob)));
		dob.sendKeys(Pdob);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.ok))).click();
		
		WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.gender)));
		gender.click();
		
		WebElement genderMale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.genderMale)));
		genderMale.click();
		
		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.phone)));
		phone.sendKeys(Pphone);
		
		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.type)));
		type.click();
		
		WebElement typeMobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.typeMobile)));
		typeMobile.click();
		
		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.email)));
		email.sendKeys(Pemail);
		
		WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.location)));
		location.click();

		WebElement selectLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.selectLocation)));
		selectLocation.click();
		
		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.note)));
		note.sendKeys(Pnote);
		
	}
	
	
	public static void addContact(String CFirstName,String Cphone2) {
		
		WebElement FirstName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.FirstName)));
		FirstName.sendKeys(CFirstName);

		WebElement relationship =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.relationship)));
		relationship.click();

		WebElement relationshipFather =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.relationshipFather)));
		relationshipFather.click();

		WebElement phone2 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.Phone)));
		phone2.sendKeys(Cphone2);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
