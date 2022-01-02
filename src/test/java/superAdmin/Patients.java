package superAdmin;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Patients {

	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 5 -- Patients";
	public String name_text="";

	public static RemoteWebDriver driver = null;
	@Parameters({"myBrowser", "myOS", "hubLink"})


	@BeforeTest
	public static void setup(String myBrowser, String myOS, String hubLink) throws MalformedURLException {

		if((myBrowser.equalsIgnoreCase("chrome")) && (myOS.equalsIgnoreCase("windows"))){
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setBrowserName(myBrowser);
			caps.setPlatform(Platform.WINDOWS);
			ChromeOptions options = new ChromeOptions();
			options.merge(caps);
			driver = new RemoteWebDriver(new URL(hubLink),options);

		}
		
		if((myBrowser.equalsIgnoreCase("chrome")) && (myOS.equalsIgnoreCase("linux"))){
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setBrowserName(myBrowser);
			caps.setPlatform(Platform.LINUX);
			ChromeOptions options = new ChromeOptions();
			options.merge(caps);
			driver = new RemoteWebDriver(new URL(hubLink),options);

		}

		if((myBrowser.equalsIgnoreCase("firefox")) && (myOS.equalsIgnoreCase("windows"))) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setBrowserName(myBrowser);
			caps.setPlatform(Platform.WINDOWS);
			FirefoxOptions options = new FirefoxOptions();
			options.merge(caps);
			driver = new RemoteWebDriver(new URL(hubLink),options);

		}
		
		if((myBrowser.equalsIgnoreCase("firefox")) && (myOS.equalsIgnoreCase("linux"))) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setPlatform(Platform.LINUX);
			FirefoxOptions options = new FirefoxOptions();
			options.merge(caps);
			driver = new RemoteWebDriver(new URL(hubLink),options);

		}

	}
	@BeforeSuite
	public static void beforeSuit() {

		if (env.equalsIgnoreCase("Test for Super Admin")) {

			System.out.println("Test executes in correct environment where environment= " + env);
			System.out.println("Test Suite name = " + testSuiteName);

		}else {
			System.out.println("Test executes in wrong environment where environment= " + env);

		}
	}

	//Opening browser with the given URL and navigate to Registration Page

//	@BeforeMethod
//	public void openBrowser() throws InterruptedException {
//
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get("https://dev.zntral.net/session/login");
//		Thread.sleep(3000);
//	}


	//TC_136

	@Test(priority=1)
	public void verify_super_User_set_access() throws InterruptedException{

		//Login to d1.zntral application
		WebElement email= driver.findElement(By.xpath("//input[@type='text']"));
		email.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		email.sendKeys("superadmin@mercury.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement password=driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("qwerty");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login_button=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 


		List<WebElement> menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<menu_list.size();i++){
			String href_text=menu_list.get(i).getAttribute("href");
			if(href_text.contains("users")){
				menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); 
				WebElement element = driver.findElement(By.xpath("//*[text()='Types']"));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
				List<WebElement> type_row= driver.findElements(By.xpath("//div[@class='app-card-content']//tbody/tr/td[1]"));
				for(int j=0;j<type_row.size();j++){
					if(type_row.get(j).getText().contains("3name")){
						type_row.get(j).click();
						//	WebElement type_row= driver.findElement(By.xpath("//*[@class='app-card-content']/h5//following::tbody/tr[1]"));
						//	type_row.click();	
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
						WebElement patient_element = driver.findElement(By.xpath("//*[text()=' Patient ']"));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", patient_element);
						WebElement patient_edit_button = driver.findElement(By.xpath("//*[text()=' Patient ']//following::div[@class='v-slide-group__content'][1]/span[3]"));
						String edit_button_colour_before_click= driver.findElement(By.xpath("//*[text()=' Patient ']//following::div[@class='v-slide-group__content'][1]/span[3]")).getCssValue("color");
						patient_edit_button.click();
						String edit_button_colour_after_click= driver.findElement(By.xpath("//*[text()=' Patient ']//following::div[@class='v-slide-group__content'][1]/span[3]")).getCssValue("color");
						if(!edit_button_colour_before_click.equals(edit_button_colour_after_click)){
							System.out.println("Edit button selected successfully");
						}
						WebElement patient_delete_button = driver.findElement(By.xpath("//div[@class='v-card__text']//following::div[40]//span[5]"));
						String delete_button_colour_before_click= driver.findElement(By.xpath("//div[@class='v-card__text']//following::div[40]//span[5]")).getCssValue("color");
						patient_delete_button.click();
						String delete_button_colour_after_click= driver.findElement(By.xpath("//div[@class='v-card__text']//following::div[40]//span[5]")).getCssValue("color");
						if(!delete_button_colour_before_click.equals(delete_button_colour_after_click)){
							System.out.println("delete button selected successfully");
						}
						WebElement update_button = driver.findElement(By.xpath("//*[text()='Update ']"));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", update_button);
						update_button.click();
						System.out.println("TC_136 Passed");
						break;
					}
				}
				break;	
			}
		}
	}

	//TC_137

	@Test(priority=2,dependsOnMethods={"verify_super_User_set_access"})
	public void verify_changes_after_refresh_logout() throws InterruptedException{

		// Check changes after refresh

//		driver.navigate().refresh();	
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); 
		WebElement element = driver.findElement(By.xpath("//*[text()='Types']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
		List<WebElement> type_row= driver.findElements(By.xpath("//div[@class='app-card-content']//tbody/tr/td[1]"));
		for(int j=0;j<type_row.size();j++){
			if(type_row.get(j).getText().contains("3name")){
				type_row.get(j).click();
				//    WebElement type_row= driver.findElement(By.xpath("//*[@class='app-card-content']/h5//following::tbody/tr[1]"));
				//	type_row.click();				
				WebElement patient_element = driver.findElement(By.xpath("//*[text()=' Patient ']"));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", patient_element);
				WebElement patient_edit_button = driver.findElement(By.xpath("//*[text()=' Patient ']//following::div[@class='v-slide-group__content'][1]/span[3]"));
				String edit_button_colour= patient_edit_button.getCssValue("color");
				WebElement patient_delete_button = driver.findElement(By.xpath("//div[@class='v-card__text']//following::div[40]//span[5]"));
				String delete_button_colour= patient_delete_button.getCssValue("color");
				if(edit_button_colour.equals("rgba(255, 255, 255, 1)")&&delete_button_colour.equals("rgba(255, 255, 255, 1)")){
					System.out.println("Changes persist after refresh");
				}
				else{
					System.out.println("Changes does not persist after refresh,TC_137 Failed");
				}
				break;
			}
		}
		WebElement pop_up_close_button = driver.findElement(By.xpath("//*[text()='close']"));
		pop_up_close_button.click();

		//check changes after log out

		WebElement three_vertical_dot_button = driver.findElement(By.xpath("//*[text()='more_vert']"));   // logout the application
		three_vertical_dot_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement logout_button = driver.findElement(By.xpath("//*[text()='Log Out']"));
		logout_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		WebElement email1= driver.findElement(By.xpath("//input[@type='text']"));
		email1.sendKeys("superadmin@mercury.com");        //Log In 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement password1=driver.findElement(By.xpath("//input[@type='password']"));
		password1.sendKeys("qwerty");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login_button1=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login_button1.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		List<WebElement> left_menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<left_menu_list.size();i++){
			String href_text=left_menu_list.get(i).getAttribute("href");
			if(href_text.contains("users")){
				left_menu_list.get(i).click();
				Thread.sleep(8000);
				WebElement element1 = driver.findElement(By.xpath("//*[text()='Types']"));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element1);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
				List<WebElement> type_row1= driver.findElements(By.xpath("//div[@class='app-card-content']//tbody/tr/td[1]"));
				for(int j=0;j<type_row1.size();j++){
					if(type_row1.get(j).getText().contains("3name")){
						type_row1.get(j).click();
						//		WebElement type_row1= driver.findElement(By.xpath("//*[@class='app-card-content']/h5//following::tbody/tr[1]"));
						//		type_row1.click();	
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
						WebElement patient_element1 = driver.findElement(By.xpath("//*[text()=' Patient ']"));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", patient_element1);
						WebElement patient_edit_button1 = driver.findElement(By.xpath("//*[text()=' Patient ']//following::div[@class='v-slide-group__content'][1]/span[3]"));
						String edit_button_colour1= patient_edit_button1.getCssValue("color");
						WebElement patient_delete_button1 = driver.findElement(By.xpath("//div[@class='v-card__text']//following::div[40]//span[5]"));
						String delete_button_colour1= patient_delete_button1.getCssValue("color");
						if(edit_button_colour1.equals("rgba(255, 255, 255, 1)")&&delete_button_colour1.equals("rgba(255, 255, 255, 1)")){
							System.out.println("Changes persist even after log out");
							System.out.println("TC_137 Passed");
						}
						break;
					}
				}
				break;
			}
		}
	}

	//TC_138

	@Test(priority=3,dependsOnMethods={"verify_changes_after_refresh_logout"})
	public void verify_User_Group_details_in_types_table() throws InterruptedException{

		WebElement pop_up_close_button1 = driver.findElement(By.xpath("//*[text()='close']"));
		pop_up_close_button1.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		List<WebElement> type_row1= driver.findElements(By.xpath("//div[@class='app-card-content']//tbody/tr/td[1]"));
		for(int j=0;j<type_row1.size();j++){
			if(type_row1.get(j).getText().contains("3name")){
				WebElement Access_Module= driver.findElement(By.xpath("//*[contains(text(),'3name')]//following::td[3]/span"));
				String Access_Module_text= Access_Module.getText();
				if(Access_Module_text.contains("Patient[Read, Add, Edit, Field: CO, Delete]")){
					System.out.println("Updated changes for respective User group is reflecting in the Types Table,TC_138 Passed");
				}
				else{
					System.out.println("Updated changes for respective User group is not reflecting in the Types Table,TC_138 Failed");
				}
				break;
			}
		}
	}

	//TC_139

	@Test(priority=4,dependsOnMethods={"verify_User_Group_details_in_types_table"})
	public void verify_edit_delete_enable_normal_user() throws InterruptedException{

		//logout application as Super admin User

		WebElement three_vertical_dot_button = driver.findElement(By.xpath("//*[text()='more_vert']"));   // logout the application
		three_vertical_dot_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement logout_button = driver.findElement(By.xpath("//*[text()='Log Out']"));
		logout_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		//log in application as Normal User

		WebElement email= driver.findElement(By.xpath("//input[@type='text']"));
		email.click();
		email.sendKeys("usert9846@gmail.com");
		WebElement password=driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("ab234cd346");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login_button=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		//Navigate to Patient Menu

		List<WebElement> menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<menu_list.size();i++){
			String href_text=menu_list.get(i).getAttribute("href");
			if(href_text.contains("patients")){
				menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); 
				WebElement Patient_data= driver.findElement(By.xpath("//*[@class='app-card-content']//following::tbody/tr[1]"));
				Patient_data.click();	
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); 	
				WebDriverWait three_vertical_dot_wait= new WebDriverWait(driver,30);
				three_vertical_dot_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='navbar-right']//span//*[text()='more_vert']")));
				WebElement three_vertical_dot=driver.findElement(By.xpath("//div[@class='navbar-right']//span//*[text()='more_vert']"));
				three_vertical_dot.click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				WebDriverWait menu_wait= new WebDriverWait(driver,30);
				menu_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='menu']")));
				WebElement menu=driver.findElement(By.xpath("//div[@role='menu']"));
				Boolean menu_status=menu.isDisplayed();
				if(menu_status){
					System.out.println("Edit and Delete option is displaying,TC_139 Passed");
				}
				else
				{
					System.out.println("Edit and Delete option is not displaying,TC_139 Failed");
				}
				break;
			}	
		}
	}

	//TC_141

	@Test(priority=5,dependsOnMethods={"verify_edit_delete_enable_normal_user"})
	public void verify_user_edit_data_Patient_page() throws InterruptedException{
		WebElement edit_option=driver.findElement(By.xpath("//div[@role='menu']//*[text()='Edit']"));
		edit_option.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement name_field=driver.findElement(By.xpath("//*[text()=' First ']//following-sibling::input[@type='text']"));
		name_field.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		name_field.sendKeys(Keys.SPACE, Keys.BACK_SPACE);
		name_field.sendKeys("Tom");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement update=driver.findElement(By.xpath("//span[text()='Update ']"));
		update.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement name= driver.findElement(By.xpath("//div[@class='info-card'][1]"));
		String name_text=name.getText();
		if(name_text.contains("Tom")){
			System.out.println("TC_141 Passed");
		}
	} 

	//TC_142

	@Test(priority=6,dependsOnMethods={"verify_user_edit_data_Patient_page"})
	public void verify_edit_logs_Patient_page() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		List<WebElement> time_stamp_row=driver.findElements(By.xpath("//div[@class='text-right timeAgo']"));
		for(int i=0;i<time_stamp_row.size();i++){
			if(time_stamp_row.get(i).getText().equalsIgnoreCase("a few seconds ago")){
				System.out.println("Logs has been updated,TC_142 passed");
			}
		}

	}

	//TC_143

	@Test(priority=7,dependsOnMethods={"verify_edit_logs_Patient_page"})
	public void verify_edit_changes_after_refresh_logout() throws InterruptedException{

		//Verify changes after Refresh

		driver.navigate().refresh();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); 
		WebElement name_field=driver.findElement(By.xpath("//div[@class='info-card'][1]"));
		String text_in_name_field= name_field.getText();
		if(text_in_name_field.contains("Tom")){
			System.out.println("changes_persist_after_refresh");
		}

		//Verify changes after logout

		WebElement three_vertical_dot_button = driver.findElement(By.xpath("//*[text()='more_vert']"));   // logout the application
		three_vertical_dot_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement logout_button = driver.findElement(By.xpath("//*[text()='Log Out']"));
		logout_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		WebElement email1= driver.findElement(By.xpath("//input[@type='text']"));
		email1.sendKeys("usert9846@gmail.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement password=driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("ab234cd346");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login_button1=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login_button1.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		//Navigate to Patient Menu

		List<WebElement> menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<menu_list.size();i++){
			String href_text=menu_list.get(i).getAttribute("href");
			if(href_text.contains("patients")){
				menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				WebElement Patient_data= driver.findElement(By.xpath("//*[@class='app-card-content']//following::tbody/tr[1]"));
				Patient_data.click();	
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 		
				WebElement name= driver.findElement(By.xpath("//div[@class='info-card'][1]"));
				String name_text=name.getText();
				if(name_text.contains("Tom")){
					System.out.println("Changes_persists_after_logout_TC_143_passed");
				}
				break;
			}
		}

	}

	//TC_144

	@Test(priority=8,dependsOnMethods={"verify_edit_changes_after_refresh_logout"})
	public void verify_edit_access_after_revoked() throws InterruptedException{

		//logout of normal user
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement three_vertical_dot_button = driver.findElement(By.xpath("//*[text()='more_vert']"));   // logout the application
		three_vertical_dot_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement logout_button = driver.findElement(By.xpath("//*[text()='Log Out']"));
		logout_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		//log in as Super admin
		WebElement email= driver.findElement(By.xpath("//input[@type='text']"));
		email.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		email.sendKeys("superadmin@mercury.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement password=driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("qwerty");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login_button=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		List<WebElement> menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<menu_list.size();i++){
			String href_text=menu_list.get(i).getAttribute("href");
			if(href_text.contains("users")){
				menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				WebElement element = driver.findElement(By.xpath("//*[text()='Types']"));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
				List<WebElement> type_row1= driver.findElements(By.xpath("//div[@class='app-card-content']//tbody/tr/td[1]"));
				for(int j=0;j<type_row1.size();j++){
					if(type_row1.get(j).getText().contains("3name")){
						type_row1.get(j).click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
						WebElement patient_element = driver.findElement(By.xpath("//*[text()=' Patient ']"));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", patient_element);
						WebElement patient_edit_button = driver.findElement(By.xpath("//*[text()=' Patient ']//following::div[@class='v-slide-group__content'][1]/span[3]"));
						patient_edit_button.click();
						WebElement patient_delete_button = driver.findElement(By.xpath("//div[@class='v-card__text']//following::div[40]//span[5]"));
						patient_delete_button.click();
						WebElement update_button = driver.findElement(By.xpath("//*[text()='Update ']"));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", update_button);
						update_button.click();
						break;
					}
				}
				break;
			}
		}

		// log out as Super admin

		WebElement vertical_dot_button = driver.findElement(By.xpath("//*[text()='more_vert']"));   // logout the application
		vertical_dot_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement logout = driver.findElement(By.xpath("//*[text()='Log Out']"));
		logout.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		//log in as Normal user

		WebElement email_field= driver.findElement(By.xpath("//input[@type='text']"));
		email_field.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		email_field.sendKeys("usert9846@gmail.com");
		WebElement password_field=driver.findElement(By.xpath("//input[@type='password']"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		password_field.sendKeys("ab234cd346");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		List<WebElement> left_menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<left_menu_list.size();i++){
			String href_text=left_menu_list.get(i).getAttribute("href");
			if(href_text.contains("patients")){
				left_menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				WebElement Patient_data= driver.findElement(By.xpath("//*[@class='app-card-content']//following::tbody/tr[1]"));
				Patient_data.click();	
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 		
				List<WebElement> three_vertical_dot=driver.findElements(By.xpath("//div[@class='navbar-right']//span//*[text()='more_vert']"));
				if(three_vertical_dot.size()!=0){
					System.out.println("Edit and Delete option is displaying,TC_144 Failed");
				}
				else
				{
					System.out.println("Edit and Delete option is not displaying,TC_144 Passed");
				}
				break;
			}	
		}
	}

	// TC_145

	@Test(priority=9,dependsOnMethods={"verify_edit_access_after_revoked"})
	public void verify_user_delete_data_Patient_page() throws InterruptedException{

		//log out as normal user
		WebElement vertical_dot_button = driver.findElement(By.xpath("//*[text()='more_vert']"));   // logout the application
		vertical_dot_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement logout = driver.findElement(By.xpath("//*[text()='Log Out']"));
		logout.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		//log in as Super admin
		WebElement email= driver.findElement(By.xpath("//input[@type='text']"));
		email.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		email.sendKeys("superadmin@mercury.com");
		WebElement password=driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("qwerty");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login_button=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		List<WebElement> menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<menu_list.size();i++){
			String href_text=menu_list.get(i).getAttribute("href");
			if(href_text.contains("users")){
				menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				WebElement element = driver.findElement(By.xpath("//*[text()='Types']"));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
				List<WebElement> type_row= driver.findElements(By.xpath("//div[@class='app-card-content']//tbody/tr/td[1]"));
				for(int j=0;j<type_row.size();j++){
					if(type_row.get(i).getText().contains("3name")){
						type_row.get(i).click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
						WebElement patient_delete_button = driver.findElement(By.xpath("//div[@class='v-card__text']//following::div[40]//span[5]"));
						patient_delete_button.click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
						WebElement update_button = driver.findElement(By.xpath("//*[text()='Update ']"));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", update_button);
						update_button.click();
						break;
					}
				}
				break;
			}
		}

		//log out as super admin

		WebElement three_vertical_dot_button = driver.findElement(By.xpath("//*[text()='more_vert']"));   // logout the application
		three_vertical_dot_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement logout_button = driver.findElement(By.xpath("//*[text()='Log Out']"));
		logout_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		//log in application as Normal User

		email= driver.findElement(By.xpath("//input[@type='text']"));
		email.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		email.sendKeys("usert9846@gmail.com");
		password=driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("ab234cd346");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		Login_button=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		List<WebElement> left_menu_list= driver.findElements(By.xpath("//a[@href]"));

		for(int i=0;i<left_menu_list.size();i++){
			String href_text=left_menu_list.get(i).getAttribute("href");
			if(href_text.contains("patients")){
				left_menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				WebElement Patient_data= driver.findElement(By.xpath("//*[@class='app-card-content']//following::tbody/tr[1]"));
				Patient_data.click();	
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				WebElement Patient_name_to_be_deleted= driver.findElement(By.xpath("//div[@class='info-card'][1]"));
				name_text=Patient_name_to_be_deleted.getText();
				WebElement three_vertical_dot=driver.findElement(By.xpath("//div[@class='navbar-right']//span//*[text()='more_vert']"));
				three_vertical_dot.click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				WebElement delete_option=driver.findElement(By.xpath("//div[@role='menu']//*[text()='Delete']"));
				delete_option.click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				driver.switchTo().alert().accept();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				List<WebElement> list_of_patient_name= driver.findElements(By.xpath("//div[@class='app-card-content']//tbody//td[2]/span"));
				for(int j=0;j<list_of_patient_name.size();i++){
					if(list_of_patient_name.get(i).getText().equalsIgnoreCase(name_text)){
						System.out.println("Delete operation not successful,TC_145 Failed");
					}
					else{
						System.out.println("Delete operation successful,TC_145 Passed");
					}
				}
				break;
			}
		}
	}	
	//TC_146

	@Test(priority=10,dependsOnMethods={"verify_user_delete_data_Patient_page"})
	public void verify_delete_logs_Patient_page() throws InterruptedException{

		List<WebElement> time_stamp_row=driver.findElements(By.xpath("//div[@class='text-right timeAgo']"));
		for(int j=0;j<time_stamp_row.size();j++){
			if(time_stamp_row.get(j).getText().equalsIgnoreCase("a few seconds ago")){
				System.out.println("Delete Logs has been updated,TC_142 passed");
			}
		}
	}

	//TC_147

	@Test(priority=11,dependsOnMethods={"verify_delete_logs_Patient_page"})
	public void verify_delete_changes_after_refresh_logout() throws InterruptedException{
		//Verify changes after Refresh

		driver.navigate().refresh();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		List<WebElement> left_menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<left_menu_list.size();i++){
			String href_text=left_menu_list.get(i).getAttribute("href");
			if(href_text.contains("patients")){
				left_menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				List<WebElement> list_of_patient_name= driver.findElements(By.xpath("//div[@class='app-card-content']//tbody//td[2]/span"));
				for(int j=0;j<list_of_patient_name.size();i++){
					if(list_of_patient_name.get(j).getText().equalsIgnoreCase(name_text)){
						System.out.println("Delete Changes does not persist after refresh,TC_147 Failed");
					}
					else{
						System.out.println("Delete Changes persist after refresh,TC_147 Passed");
					}
				}
				break;
			}
		}	

		//Verify changes after logout

		WebElement three_vertical_dot_button = driver.findElement(By.xpath("//*[text()='more_vert']"));   // logout the application
		three_vertical_dot_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement logout_button = driver.findElement(By.xpath("//*[text()='Log Out']"));
		logout_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		//log in as Normal User 	
		WebElement email1= driver.findElement(By.xpath("//input[@type='text']"));
		email1.sendKeys("usert9846@gmail.com");
		WebElement password=driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("ab234cd346");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login_button1=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login_button1.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		//Navigate to Patient Menu

		List<WebElement> menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<menu_list.size();i++){
			String href_text=menu_list.get(i).getAttribute("href");
			if(href_text.contains("patients")){
				menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 	
				List<WebElement> list_of_patient_name= driver.findElements(By.xpath("//div[@class='app-card-content']//tbody//td[2]/span"));
				for(int j=0;j<list_of_patient_name.size();i++){
					if(list_of_patient_name.get(j).getText().equalsIgnoreCase(name_text)){
						System.out.println("Delete Changes does not persist after logout,TC_147 Failed");
					}
					else{
						System.out.println("Delete Changes persist after logout,TC_147 Passed");
					}
				}
				break;
			}
		}
	}	

	//TC_148

	@Test(priority=12,dependsOnMethods={"verify_delete_changes_after_refresh_logout"})
	public void verify_delete_access_after_revoked() throws InterruptedException{
		//logout of normal user

		WebElement three_vertical_dot_button = driver.findElement(By.xpath("//*[text()='more_vert']"));   // logout the application
		three_vertical_dot_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement logout_button = driver.findElement(By.xpath("//*[text()='Log Out']"));
		logout_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		//log in as Super admin
		WebElement email= driver.findElement(By.xpath("//input[@type='text']"));
		email.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		email.sendKeys("superadmin@mercury.com");
		WebElement password=driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("qwerty");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login_button=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		List<WebElement> menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<menu_list.size();i++){
			String href_text=menu_list.get(i).getAttribute("href");
			if(href_text.contains("users")){
				menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				WebElement element = driver.findElement(By.xpath("//*[text()='Types']"));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
				List<WebElement> type_row1= driver.findElements(By.xpath("//div[@class='app-card-content']//tbody/tr/td[1]"));
				for(int j=0;j<type_row1.size();j++){
					if(type_row1.get(j).getText().contains("3name")){
						type_row1.get(j).click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
						WebElement patient_element = driver.findElement(By.xpath("//*[text()=' Patient ']"));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", patient_element);
						WebElement patient_delete_button = driver.findElement(By.xpath("//div[@class='v-card__text']//following::div[40]//span[5]"));
						patient_delete_button.click();
						WebElement update_button = driver.findElement(By.xpath("//*[text()='Update ']"));
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", update_button);
						update_button.click();
						break;
					}
				}
				break;
			}
		}

		// log in as Nomal User
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement email_field= driver.findElement(By.xpath("//input[@type='text']"));
		email_field.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		email_field.sendKeys("usert9846@gmail.com");
		WebElement password_field=driver.findElement(By.xpath("//input[@type='password']"));
		password_field.sendKeys("ab234cd346");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		List<WebElement> left_menu_list= driver.findElements(By.xpath("//a[@href]"));
		for(int i=0;i<left_menu_list.size();i++){
			String href_text=left_menu_list.get(i).getAttribute("href");
			if(href_text.contains("patients")){
				left_menu_list.get(i).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
				WebElement Patient_data= driver.findElement(By.xpath("//*[@class='app-card-content']//following::tbody/tr[1]"));
				Patient_data.click();	
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 		
				List<WebElement> three_vertical_dot=driver.findElements(By.xpath("//div[@class='navbar-right']//span//*[text()='more_vert']"));
				if(three_vertical_dot.size()!=0){
					System.out.println("Delete option is displaying,TC_148 Failed");
				}
				else
				{
					System.out.println("Delete option is not displaying,TC_148 Passed");
				}
				break;
			}	
		}

	}


	@AfterTest
	public void tearDown() throws Exception {
		if (driver != null) {
			System.out.println("Test Done!!!");
			driver.quit();
		}
	}

	@AfterSuite
	public static void afterSuit() {

		System.out.println( testSuiteName + " execution Complete");
	}
}
