package normalUserFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import NormalUserXpath.CalendarXpath;
import normalUser.Calendar;
import normalUserInputData.CalendarInfoData;

public class CalendarFunctions extends Calendar{

	public static void verifyLogin() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.username)));
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.pass)));
		WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.login)));

		String user = CalendarInfoData.user;
		String pass = CalendarInfoData.pass;

		username.sendKeys(user);
		password.sendKeys(pass);

		login.click();
		Thread.sleep(5000);
	}

	public static void calendarList() {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CalendarXpath.calendar)));
		driver.findElement(By.xpath(CalendarXpath.calendar)).click();
	}
}
