package normalUserFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import NormalUserXpath.ContactsXpath;
import normalUser.Contacts;
import normalUserInputData.ContactsInfoData;

public class ContactsFunctions extends Contacts {
	static WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

	public static void verifyLogin() throws InterruptedException {

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.pass)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.login)));

		String user = ContactsInfoData.user;
		String pass = ContactsInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);
	}


	public static void contactlist() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactsXpath.contacts)));
		driver.findElement(By.xpath(ContactsXpath.contacts)).click();
		Thread.sleep(3000);
	}


	public static void addContacts(String first, String last, String phn, String mail, String title2, String note2) {

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
	}
}
