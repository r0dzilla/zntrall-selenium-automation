package normalUserFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import NormalUserXpath.User_ProfileXpath;
import normalUser.User_Profile;
import normalUserInputData.User_ProfileInfoData;

public class UserProfileFunctions extends User_Profile {

	static WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	
	public static void verifyLogin() throws InterruptedException {
		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.password)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.login)));

		username.sendKeys(User_ProfileInfoData.user);
		password.sendKeys(User_ProfileInfoData.pass);

		login.click();
		Thread.sleep(5000);
	}
	
	public static void viewUserProfile() throws InterruptedException {
		
		WebElement username2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(User_ProfileXpath.userProfile)));
		username2.click();
		Thread.sleep(3000);
	}
	
	
}
