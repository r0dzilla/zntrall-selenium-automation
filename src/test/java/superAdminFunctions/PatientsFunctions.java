package superAdminFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import superAdmin.Patients;
import superAdminInputData.PatientsInfoData;
import superAdminXpath.PatientsXpath;

public class PatientsFunctions extends Patients {

	public static void verifyLogin() throws InterruptedException {

		WebElement username = driver.findElement(By.xpath(PatientsXpath.username));
		WebElement password = driver.findElement(By.xpath(PatientsXpath.pass));
		WebElement login = driver.findElement(By.xpath(PatientsXpath.login));

		String user = PatientsInfoData.user;
		String pass = PatientsInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);
	}

	public static void patientList() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement patients = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PatientsXpath.patients)));
		patients.click();
		Thread.sleep(3000);
	}
}
