package normalUserFunctions;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import NormalUserXpath.DashboardXpath;
import normalUser.Dashboard;
import normalUserInputData.DashboardInfoData;

public class DashboardFunctions extends Dashboard{

	static WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

	public static void verifyLogin() throws InterruptedException {

		WebElement username = driver.findElement(By.xpath(DashboardXpath.username));
		WebElement password = driver.findElement(By.xpath(DashboardXpath.pass));
		WebElement login = driver.findElement(By.xpath(DashboardXpath.login));

		String user = DashboardInfoData.user;
		String pass = DashboardInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);
	}

	public static void optionSelect() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.add)));
		driver.findElement(By.xpath(DashboardXpath.add)).click();

		Thread.sleep(2000);
		List<WebElement> options =  driver.findElements(By.xpath(DashboardXpath.options));
		Random random = new Random();
		int index = random.nextInt(options.size());
		options.get(index).click(); 
	}

	public static void addData(String LName, String LLicence, String LCapacity, String Laddress, String LsuiteUnit,String Lcity,String Lstate,String Lzip,String LphoneNumber, String Lemail,String Lnote) {

		WebElement locationName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.locationName)));
		locationName.sendKeys(LName);

		WebElement licenceNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.licenceNumber)));
		licenceNumber.sendKeys(LLicence);

		WebElement capacity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.capacity)));
		capacity.sendKeys(LCapacity);

		WebElement address = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.address)));
		address.sendKeys(Laddress);

		WebElement suiteUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.suiteUnit)));
		suiteUnit.sendKeys(LsuiteUnit);

		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.city)));
		city.sendKeys(Lcity);

		WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.state)));
		state.sendKeys(Lstate);

		WebElement zip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.zip)));
		zip.sendKeys(Lzip);

		WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.phoneNumber)));
		phoneNumber.sendKeys(LphoneNumber);

		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.type)));
		type.click();

		WebElement selectType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.selectType)));
		selectType.click();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.email)));
		email.sendKeys(Lemail);

		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.note)));
		note.sendKeys(Lnote);

	}

	public static void addPatientData(String Pprefix, String PfirstName, String PlastName, String Pssn,String Pdob,String Pphone,String Pemail,String Pnote) throws InterruptedException {

		WebElement prefix = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.prefix)));
		prefix.sendKeys(Pprefix);

		WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.firstName)));
		firstName.sendKeys(PfirstName);

		WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.lastName)));
		lastName.sendKeys(PlastName);

		WebElement ssn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.ssn)));
		ssn.sendKeys(Pssn);

		WebElement dob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.dob)));
		dob.sendKeys(Pdob);

		WebElement gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.gender)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.ok))).click();
		gender.click();

		WebElement genderMale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.genderMale)));
		genderMale.click();


		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.phone)));
		phone.sendKeys(Pphone);

		WebElement type = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.patientType)));
		type.click();

		WebElement typeMobile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.typeMobile)));
		typeMobile.click();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.email)));
		email.sendKeys(Pemail);

		WebElement note = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DashboardXpath.patientNote)));
		note.sendKeys(Pnote);
	}
}
