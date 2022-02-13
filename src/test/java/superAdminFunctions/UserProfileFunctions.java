package superAdminFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import superAdmin.User_profile;
import superAdminInputData.User_profileInfoData;
import superAdminXpath.User_profileXpath;

public class UserProfileFunctions extends User_profile{

	public static void verifyLogin() throws InterruptedException {
		WebElement username = driver.findElement(By.xpath(User_profileXpath.username));
		WebElement password = driver.findElement(By.xpath(User_profileXpath.pass));
		WebElement login = driver.findElement(By.xpath(User_profileXpath.login));

		String user = User_profileInfoData.user;
		String pass = User_profileInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);
	}
	
	
	public static void userList() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_profileXpath.userProfile)));
		userName.click();
		Thread.sleep(3000);
	}
}
