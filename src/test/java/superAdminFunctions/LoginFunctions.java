package superAdminFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import superAdmin.Login;
import superAdminInputData.LoginInfoData;
import superAdminXpath.LoginXpath;

public class LoginFunctions extends Login{

	public static void verifyLogin() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.pass)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginXpath.login)));

		String user = LoginInfoData.user;
		String pass = LoginInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);
	}
}
