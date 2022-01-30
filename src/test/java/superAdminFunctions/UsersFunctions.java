package superAdminFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import superAdmin.Users;
import superAdminXpath.UsersXpath;

public class UsersFunctions extends Users {

	//Permission selection functions section starts //
	//permission for users

	public static void permissionUsers() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement userRead = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.userRead)));
		WebElement userAdd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.userAdd)));
		WebElement userStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.userStatus)));
		WebElement userClient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.userClient)));
		WebElement userChat = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.userChat)));

		//		if(userRead.isSelected()) {
		//			userRead.click();
		//			Assert.assertTrue(true);
		//
		//
		//		}else {
		//			userRead.click();
		//		}


		if(userAdd.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			userAdd.click();
		}

		if(userStatus.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			userStatus.click();
		}


		if(userClient.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			userClient.click();
		}


		//		if(userChat.isSelected()) {
		//			System.out.println("Already Selected !!");
		//			Assert.assertTrue(true);
		//		}else {
		//			userChat.click();
		//		}
	}

	//permission for location

	public static void permissionLocation() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement locationRead = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.locationRead)));
		WebElement locationAdd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.locationAdd)));
		WebElement locationEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.locationEdit)));
		WebElement locationDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.locationDelete)));

		if(locationRead.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			locationRead.click();
		}

		if(locationAdd.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			locationAdd.click();
		}

		if(locationEdit.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			locationEdit.click();
		}

		if(locationDelete.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			locationDelete.click();
		}

	}

	//permission for Patient

	public static void permissionPatient() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement patientRead = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.patientRead)));
		WebElement patientAdd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.patientAdd)));
		WebElement patientEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.patientEdit)));
		WebElement patientField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.patientField)));
		WebElement patientDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.patientDelete)));

		if(patientRead.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			patientRead.click();
		}

		if(patientAdd.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			patientAdd.click();
		}

		if(patientEdit.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			patientEdit.click();
		}

		if(patientField.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			patientField.click();
		}

		if(patientDelete.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			patientDelete.click();
		}
	}


	//permission for calendar

	public static void permissionCalendar() {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement calendarRead = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.calendarRead)));
		WebElement calendarEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.calendarEdit)));

		if(calendarRead.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			calendarRead.click();
		}

		if(calendarEdit.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			calendarEdit.click();
		}
	}

	//permission for contact

	public static void permissionContact() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement contactRead = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.contactRead)));
		WebElement contactAdd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.contactAdd)));
		WebElement contactEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.contactEdit)));
		WebElement contactAddProvider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.contactAddProvider)));
		WebElement contactDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.contactDelete)));


		if(contactRead.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			contactRead.click();
		}

		if(contactAdd.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			contactAdd.click();
		}

		if(contactEdit.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			contactEdit.click();
		}

		if(contactAddProvider.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			contactAddProvider.click();
		}

		if(contactDelete.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			contactDelete.click();
		}
	}

	//permission for Forms

	public static void permissionForms() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement endOfCareForms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.endOfCareForms)));
		WebElement startOfCareForms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.startOfCareForms)));
		WebElement form4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.form4)));
		WebElement testForm3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.testForm3)));
		WebElement testForm2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.testForm2)));
		WebElement testForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UsersXpath.testForm)));

		if(endOfCareForms.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			endOfCareForms.click();
		}

		if(startOfCareForms.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			startOfCareForms.click();
		}

		if(form4.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			form4.click();
		}

		if(testForm3.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			testForm3.click();
		}

		if(testForm2.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			testForm2.click();
		}

		if(testForm.isSelected()) {
			System.out.println("Already Selected !!");
			Assert.assertTrue(true);
		}else {
			testForm.click();
		}
	}
	//Permission selection functions section ends//
}
