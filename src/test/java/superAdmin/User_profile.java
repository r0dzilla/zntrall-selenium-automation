package superAdmin;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class User_profile {

	public static String env = "Test for Super Admin";
	public static String testSuiteName = "Test Suit 6 -- Profile";
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

	@BeforeMethod
	public void openBrowser() throws InterruptedException {

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://dev.zntral.net/session/login");
		Thread.sleep(3000);
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


	//TC_53
	@Test(priority=1)
	public void verify_upload_picture_popup() throws InterruptedException{

		//Login to d1.zntral application
		WebElement email= driver.findElement(By.xpath("//input[@type='text']"));
		email.click();
		email.sendKeys("superadmin@mercury.com");
		WebElement password=driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys("qwerty");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Login_button=driver.findElement(By.xpath("//*[text()='Login Now ']"));
		Login_button.click();
		Thread.sleep(20000);

		//click on User Image and confirm upload picture popup

		WebElement user_profile_button=driver.findElement(By.xpath("//div[@class='v-list-item__title']//*[text()='John Doe']"));
		user_profile_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement User_profile_icon=driver.findElement(By.xpath("//div[@class='app-card-content']//img[contains(@class,'img')]"));
		User_profile_icon.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		Boolean popup_title=driver.findElement(By.xpath("//*[text()='Upload Profile Picture']")).isDisplayed();
		if(popup_title){
			System.out.println("Upload Picture pop is coming,TC_53 Passed");
		}
		else{
			System.out.println("Upload Picture popup is not coming,TC_53 Failed");
		}
	}

	//TC_54	
	@Test(priority=2)
	public void verify_UI_of_upload_picture_popup() throws InterruptedException{

		//verify the upload pictue canvas
		WebElement canvas=driver.findElement(By.tagName("canvas"));
		Boolean canvas_status=canvas.isDisplayed();
		if(canvas_status){
			System.out.println("Upload Picture Canvas is displaying");
		}
		else
		{
			System.out.println("Upload Picture Canvas is not displaying");
		}

		//Verify the Take a picture button
		WebElement take_a_picture=driver.findElement(By.xpath("//button[@type='button']//*[text()=' Take a picture ']"));
		Boolean take_a_picture_status=take_a_picture.isDisplayed();
		if(take_a_picture_status){
			System.out.println("Take a picture is displaying");
		}
		else
		{
			System.out.println("Take a picture is not displaying");
		}

		//Verify the submit(disabled) and close button
		WebElement submit_button=driver.findElement(By.xpath("//*[text()='Submit ']//parent::button[@disabled='disabled']"));
		if(submit_button.isDisplayed()){
			System.out.println("Submit button is displaying and is disabled by default");
		}else{
			System.out.println("Submit button is not displaying");
		}
		WebElement close_button=driver.findElement(By.xpath("//*[text()='Close ']"));
		if(close_button.isDisplayed()){
			System.out.println("close button is displaying,TC_54 Passed");
		}else{
			System.out.println("close button is not displaying,TC_54 Failed");
		}
	}

	//TC_55	
	@Test(priority=3,dependsOnMethods={"verify_upload_picture_popup"})
	public void add_user_profile_picture() throws InterruptedException{

		//close already existing uploaded user profile
		//	WebElement cross_button=driver.findElement(By.xpath("//*[@class='icon icon-remove']"));
		//	cross_button.click();
		//	WebElement canvas=driver.findElement(By.tagName("canvas"));
		//	canvas.click();
		//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement upload_picture=driver.findElement(By.xpath("//input[@type='file']"));
		upload_picture.sendKeys("C:\\Users\\ACER\\Desktop\\profile_pic.jpg");
		WebElement submit=driver.findElement(By.xpath("//*[text()='Submit ']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", submit);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		submit.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		System.out.println("Picture added successfully,TC_55 Passed");
	}

	//TC_56
	@Test(priority=4,dependsOnMethods={"add_user_profile_picture"})
	public void verify_user_profile_picture_after_refresh() throws InterruptedException{
		List<WebElement> user_profile_count=driver.findElements(By.xpath("//img[@alt='user profile']"));
		if(user_profile_count.size()==2){
			System.out.println("User profile picture exists successfully after refresh,TC_56 Passed");
		}
		else{
			System.out.println("User profile picture does not exists after refresh,TC_56 Failed");
		}
	}

	//TC_59
	@Test(priority=5,dependsOnMethods={"verify_user_profile_picture_after_refresh"})
	public void remove_user_profile_picture() throws InterruptedException{
		WebElement User_profile_icon=driver.findElement(By.xpath("//div[@class='app-card-content']//div[contains(@class,'img')]"));
		User_profile_icon.click();
		WebElement cross_button=driver.findElement(By.xpath("//*[@class='icon icon-remove']"));
		cross_button.click();
		WebElement close_button=driver.findElement(By.xpath("//*[text()='Close ']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", close_button);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		close_button.click();
		WebElement User_profile_xpath_without_pic=driver.findElement(By.xpath("div[@class='app-card-content']//*[text()='JD ']"));
		Boolean pic_is_displayed=User_profile_xpath_without_pic.isDisplayed();
		if(pic_is_displayed){
			System.out.println("Uploaded picture has been removed successfully,TC_59 Passed");
		}else{
			System.out.println("Uploaded picture has not been removed,TC_59 Failed");
		}
	}
	//TC_60
	@Test(priority=6,dependsOnMethods={"remove_user_profile_picture"})
	public void verify_remove_user_profile_picture_after_refresh() throws InterruptedException{

		driver.navigate().refresh();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement User_profile_xpath_without_pic=driver.findElement(By.xpath("div[@class='app-card-content']//*[text()='JD ']"));
		Boolean pic_is_displayed=User_profile_xpath_without_pic.isDisplayed();
		if(pic_is_displayed){
			System.out.println("No User profile shows after refresh,TC_60 Passed");
		}else{
			System.out.println("User profile shows after refresh,TC_60 Failed");
		}
	}

	//TC_61
	@Test(priority=7)
	public void verify_gender_pop_up() throws InterruptedException{

		WebElement gender=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Gender']"));
		gender.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 	
		Boolean gender_popup_title=driver.findElement(By.xpath("//div[@role='document']//*[text()='Gender']")).isDisplayed();
		if(gender_popup_title){
			System.out.println("Gender pop up is displaying,TC_61 Passed");
		}else{
			System.out.println("Gender pop up is not displaying,TC_61 Failed");
		}
	}

	//TC_62
	@Test(priority=8,dependsOnMethods={"verify_gender_pop_up"})
	public void verify_UI_of_Gender_popup() throws InterruptedException{
		//Verify the Male,Female,other radio button
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 	
		WebElement Male_button=driver.findElement(By.xpath("//*[text()='Male']"));
		Boolean Male_button_status=Male_button.isDisplayed();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 	
		WebElement Female_button=driver.findElement(By.xpath("//*[text()='Female']"));
		Boolean Female_button_status=Female_button.isDisplayed();
		WebElement other_button=driver.findElement(By.xpath("//*[text()='Other']"));
		Boolean other_button_status=other_button.isDisplayed();	
		if(Male_button_status&&Female_button_status){
			if(other_button_status){	
				System.out.println("Male,Female,Other Radio button is present");
			}else{
				System.out.println("Male,Female or Other Radio button is missing");
			}
		}	
		//Verify Submit and Cancel button
		WebElement submit_button=driver.findElement(By.xpath("//*[text()='Submit']"));
		Boolean submit_button_status=submit_button.isDisplayed();
		WebElement Cancel_button=driver.findElement(By.xpath("//*[text()='Cancel']"));
		Boolean Cancel_button_status=Cancel_button.isDisplayed();
		if(submit_button_status&&Cancel_button_status){
			System.out.println("Submit and Cancel button is present,TC_62 Passed");
		}else{
			System.out.println("Submit and Cancel Radio button is missing,TC_62 Failed");
		}
	}

	//TC_64
	@Test(priority=9,dependsOnMethods={"verify_UI_of_Gender_popup"})
	public void verify_modify_Gender() throws InterruptedException{

		//Verify Text box on selecting Other option in Gender
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 	
		WebElement other_button=driver.findElement(By.xpath("//*[text()='Other']"));
		other_button.click();
		WebElement Gender_other_text_box=driver.findElement(By.xpath("//*[text()='Gender(others)']"));
		Boolean Gender_other_text_box_status=Gender_other_text_box.isDisplayed();
		if(Gender_other_text_box_status){
			System.out.println("Gender(Other) Text box is present");
		}else{
			System.out.println("Gender(Other) Text box is not present");
		}

		//Verify if User is able to update male option
		WebElement male_button=driver.findElement(By.xpath("//div[@role='document']//*[text()='Male']"));
		male_button.click();
		Thread.sleep(2000);
		WebElement Submit_button=driver.findElement(By.xpath("//*[text()='Submit']"));
		Submit_button.click();
		Thread.sleep(2000);
		String gender_text=driver.findElement(By.xpath("//*[text()='Gender']//parent::div/span")).getText();
		if(gender_text.equalsIgnoreCase("Male")){
			System.out.println("Gender Male selected successfully");
		}else{
			System.out.println("Gender Male not selected");
		}
		//Verify if User is able to update Female option
		WebElement gender=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Gender']"));
		gender.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement female_button=driver.findElement(By.xpath("//div[@role='document']//*[text()='Female']"));
		female_button.click();
		Thread.sleep(2000);
		WebElement submit_button=driver.findElement(By.xpath("//*[text()='Submit']"));
		submit_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		String Gender_text=driver.findElement(By.xpath("//*[text()='Gender']//parent::div/span")).getText();
		if(Gender_text.equalsIgnoreCase("Female")){
			System.out.println("Gender Female selected successfully");
		}else{
			System.out.println("Gender Female not selected");
		}
		//Verify if User is able to update other option
		gender=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Gender']"));
		gender.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		other_button=driver.findElement(By.xpath("//*[text()='Other']"));
		other_button.click();
		Thread.sleep(2000);
		WebElement clear_icon=driver.findElement(By.xpath("//*[@aria-label='clear icon']"));
		clear_icon.click();
		Thread.sleep(2000);
		WebElement gender_text_box=driver.findElement(By.xpath("//*[text()='Gender(others)']//parent::div/input"));
		gender_text_box.sendKeys("Test");
		submit_button=driver.findElement(By.xpath("//*[text()='Submit']"));
		submit_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		gender_text=driver.findElement(By.xpath("//*[text()='Gender']//parent::div/span")).getText();
		if(gender_text.equalsIgnoreCase("Test")){
			System.out.println("Gender Other selected successfully,TC_64 Passed");
		}else{
			System.out.println("Gender Other not selected,TC_64 Failed");
		}					
	}

	//TC_65		
	@Test(priority=10,dependsOnMethods={"verify_modify_Gender"})
	public void verify_modify_Gender_after_Refresh() throws InterruptedException{

		driver.navigate().refresh();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		String gender_text=driver.findElement(By.xpath("//*[text()='Gender']//parent::div/span")).getText();
		if(gender_text.equalsIgnoreCase("Test")){
			System.out.println("Gender Changes exsist after Refresh,TC_65 Passed");
		}else{
			System.out.println("Gender Changes does not exsist after Refresh,TC_65 Failed");
		}					
	}

	//TC_66
	@Test(priority=11)
	public void verify_Address_pop_up() throws InterruptedException{
		WebElement Address_popup=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Address']"));
		Address_popup.click();
		Boolean Address_popup_title=driver.findElement(By.xpath("//div[@role='document']//*[text()='Address']")).isDisplayed();
		if(Address_popup_title){
			System.out.println("Address popup is displaying,TC_66 Passed");
		}else{
			System.out.println("Address popup is not displaying,TC_66 Failed");
		}
	}	

	//TC_67
	@Test(priority=12,dependsOnMethods={"verify_Address_pop_up"})
	public void verify_UI_of_Address_pop_up() throws InterruptedException{
		//Verify all the fields of the Address popup
		WebElement Street_Address_field=driver.findElement(By.xpath("//*[text()='Street address']"));
		Boolean Street_Address_field_status=Street_Address_field.isDisplayed();
		if(Street_Address_field_status){
			System.out.println("Street Address field is displaying");
		}else{
			System.out.println("Street Address field is not displaying");
		}
		WebElement Unit_field=driver.findElement(By.xpath("//*[text()='Unit']"));
		Boolean Unit_field_status=Unit_field.isDisplayed();
		if(Unit_field_status){
			System.out.println("Unit field is displaying");
		}else{
			System.out.println("Unit field is not displaying");
		}
		WebElement Zip_field=driver.findElement(By.xpath("//*[text()='Zip']"));
		Boolean Zip_field_status=Zip_field.isDisplayed();
		if(Unit_field_status){
			System.out.println("Zip field is displaying");
		}else{
			System.out.println("Zip field is not displaying");
		}
		WebElement City_field=driver.findElement(By.xpath("//*[text()='City']"));
		Boolean City_field_status=City_field.isDisplayed();
		if(City_field_status){
			System.out.println("City field is displaying");
		}else{
			System.out.println("City field is not displaying");
		}
		WebElement State_field=driver.findElement(By.xpath("//*[text()='State']"));
		Boolean State_field_status=State_field.isDisplayed();
		if(State_field_status){
			System.out.println("State field is displaying");
		}else{
			System.out.println("State field is not displaying");
		}
		WebElement PO_Box_field=driver.findElement(By.xpath("//*[text()='PO Box']"));
		Boolean PO_Box_field_status=PO_Box_field.isDisplayed();
		if(PO_Box_field_status){
			System.out.println("PO Box field is displaying");
		}else{
			System.out.println("PO Box field is not displaying");
		}
		WebElement Submit=driver.findElement(By.xpath("//button[@disabled='disabled']//*[text()='Submit']"));
		Boolean Submit_status=Submit.isDisplayed();
		if(Submit_status){
			System.out.println("Submit button is displaying and disabled");
		}else{
			System.out.println("Submit button is not displaying");
		}
		WebElement Cancel=driver.findElement(By.xpath("//*[text()='Cancel']"));
		Boolean Cancel_status=Submit.isDisplayed();
		if(Cancel_status){
			System.out.println("Cancel button is displaying,TC_67 Passed");
		}else{
			System.out.println("Cancel button is not displaying,TC_67 Failed");
		}
	}

	//TC_68
	@Test(priority=13,dependsOnMethods={"verify_UI_of_Address_pop_up"})
	public void verify_only_mandatory_field_Address() throws InterruptedException{
		//Fill only Mandatory Field(Street address)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement clear_icon=driver.findElement(By.xpath("//*[text()='Street address']//following::button[1]"));
		clear_icon.click();
		Thread.sleep(2000);
		WebElement Street_Address_field=driver.findElement(By.xpath("//*[text()='Street address']//parent::div/input"));
		Street_Address_field.sendKeys("121 Potter Street");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Submit=driver.findElement(By.xpath("//*[text()='Submit']"));
		Submit.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		String Address_text=driver.findElement(By.xpath("//*[text()='Address']//parent::div/span")).getText();
		if(Address_text.equalsIgnoreCase("121 Potter Street")){
			System.out.println("Address updated successfully,TC_68 Passed");
		}else{
			System.out.println("Address not updated successfully,TC_68 Failed");
		}
	}

	//TC_69
	@Test(priority=14)
	public void verify_only_non_mandatory_field_Address() throws InterruptedException{
		//Fill only Non-Mandatory Field(except Street address)
		WebElement Address_popup=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Address']"));
		Address_popup.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement clear_icon=driver.findElement(By.xpath("//*[text()='Street address']//following::button[1]"));
		clear_icon.click();
		Thread.sleep(2000);
		WebElement Unit_field=driver.findElement(By.xpath("//*[text()='Unit']//following::input"));
		Unit_field.sendKeys("123");
		Thread.sleep(2000);
		WebElement Zip_field=driver.findElement(By.xpath("//*[text()='Zip']//following::input"));
		Zip_field.sendKeys("273");
		Thread.sleep(2000);
		WebElement City_field=driver.findElement(By.xpath("//*[text()='City']//following::input"));
		City_field.sendKeys("Dellas");
		Thread.sleep(2000);
		WebElement State_field=driver.findElement(By.xpath("//*[text()='State']//following::input"));
		State_field.sendKeys("US");
		Thread.sleep(2000);
		WebElement PO_Box_field=driver.findElement(By.xpath("//*[text()='PO Box']//following::input"));
		PO_Box_field.sendKeys("121");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement Submit=driver.findElement(By.xpath("//button[@disabled='disabled']//*[text()='Submit']"));
		Boolean Submit_status=Submit.isDisplayed();
		if(Submit_status){
			System.out.println("Submit button is displaying and disabled.TC_69 Passed");
		}else{
			System.out.println("Submit button is not displaying.TC_69 Failed");
		}
	}

	//TC_70
	@Test(priority=15,dependsOnMethods={"verify_only_non_mandatory_field_Address"})
	public void verify_cancel_button_field() throws InterruptedException{
		WebElement Cancel=driver.findElement(By.xpath("//*[text()='Cancel']"));
		Cancel.click();
		//verify the window title
		String page_title= driver.getTitle();
		if(!page_title.equalsIgnoreCase("Address")){
			System.out.println("Adress popup closed,TC_70 Passed");
		}else{
			System.out.println("Adress popup is not closed,TC_70 Failed");
		}
	}
	//TC_71
	@Test(priority=16,dependsOnMethods={"verify_cancel_button_field"})
	public void verify_address_changes_after_refresh()throws InterruptedException{
		driver.navigate().refresh();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		String Address_text=driver.findElement(By.xpath("//*[text()='Address']//parent::div/span")).getText();
		if(Address_text.equalsIgnoreCase("121 Potter Street")){
			System.out.println("Updated Address visible after refresh,TC_71 Passed");
		}else{
			System.out.println("Updated Address not visible after refresh,TC_71 Failed");
		}
	}

	//TC_72
	@Test(priority=17,dependsOnMethods={"verify_address_changes_after_refresh"})
	public void verify_phone_pop()throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement phone=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Phone']"));
		phone.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		Boolean phone_popup_title=driver.findElement(By.xpath("//div[@role='document']//*[text()='Phone']")).isDisplayed();
		if(phone_popup_title){
			System.out.println("Phone pop up is displaying,TC_72 Passed");
		}else{
			System.out.println("Phone pop up is not displaying,TC_72 Failed");
		}	
	}
	//TC_73
	@Test(priority=18,dependsOnMethods={"verify_phone_pop"})
	public void verify_UI_of_phone_pop_up()throws InterruptedException{
		//verify phone1,phone2,phone3 fields
		WebElement phone1=driver.findElement(By.xpath("//*[text()='Phone 1']"));
		Boolean phone1_status=phone1.isDisplayed();
		if(phone1_status){
			System.out.println("phone1 is displaying");
		}else{
			System.out.println("phone1 is not displaying");
		}
		WebElement phone2=driver.findElement(By.xpath("//*[text()='Phone 2']"));
		Boolean phone2_status=phone2.isDisplayed();
		if(phone2_status){
			System.out.println("phone2 is displaying");
		}else{
			System.out.println("phone2 is not displaying");
		}
		WebElement phone3=driver.findElement(By.xpath("//*[text()='Phone 3']"));
		Boolean phone3_status=phone3.isDisplayed();
		if(phone3_status){
			System.out.println("phone3 is displaying");
		}else{
			System.out.println("phone3 is not displaying");
		}
		List<WebElement> Type=driver.findElements(By.xpath("//*[text()='Type']"));
		int Type_count=Type.size();
		if(Type_count==3){
			System.out.println("Type field is present,TC_73 Passed");
		}else{
			System.out.println("Type field is not present,TC_73 Failed");
		}
	}
	//TC_74
	@Test(priority=19,dependsOnMethods={"verify_UI_of_phone_pop_up"})
	public void verify_error_message_in_phone1_field()throws InterruptedException{
		WebElement cross_button=driver.findElement(By.xpath("//*[text()='Phone 1']//following::div//button"));
		cross_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		String error_message=driver.findElement(By.xpath("//div[@class='v-messages__message']")).getText();
		System.out.println("Actual message"+error_message);
		String expected_message="Phone number is required";
		if(error_message.equalsIgnoreCase(expected_message)){
			System.out.println("Error message is displaying,TC_74 Passed");
		}else{
			System.out.println("Error message is not displaying,TC_74 Failed");
		}
	}
	//TC_75
	@Test(priority=20,dependsOnMethods={"verify_error_message_in_phone1_field"})
	public void verify_add_phone()throws InterruptedException{
		WebElement phone1_field=driver.findElement(By.xpath("//*[text()='Phone 1']//following::input[1]"));
		phone1_field.clear();
		phone1_field.sendKeys("1234");
		WebElement submit_button=driver.findElement(By.xpath("//*[text()='Submit']"));
		submit_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement phone_in_user_field=driver.findElement(By.xpath("//*[text()='Phone']//parent::div/span[1]"));
		String phone_in_user_field_text=phone_in_user_field.getText();
		if(phone_in_user_field_text.contains("1234")){
			System.out.println("TC_75 Passed");
		}else{
			System.out.println("TC_75 Failed");
		}
	}
	//TC_76
	@Test(priority=21,dependsOnMethods={"verify_add_phone"})
	public void verify_add_multiple_phone()throws InterruptedException{
		WebElement phone=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Phone']"));
		phone.click();
		Thread.sleep(2000);
		WebElement phone2_clear_icon=driver.findElement(By.xpath("//*[text()='Phone 1']//following::button[2]"));
		phone2_clear_icon.click();
		Thread.sleep(2000);
		WebElement phone2_field=driver.findElement(By.xpath("//*[text()='Phone 2']//following::input[1]"));
		phone2_field.sendKeys("8989");
		WebElement phone3_clear_icon=driver.findElement(By.xpath("//*[text()='Phone 1']//following::button[3]"));
		phone3_clear_icon.click();
		Thread.sleep(2000);
		WebElement phone3_field=driver.findElement(By.xpath("//*[text()='Phone 3']//following::input[1]"));
		phone3_field.sendKeys("4949");
		Thread.sleep(2000);
		WebElement submit_button=driver.findElement(By.xpath("//*[text()='Submit']"));
		submit_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement phone2_in_user_field=driver.findElement(By.xpath("//*[text()='Phone']//parent::div/span[2]"));
		String phone2_in_user_field_text=phone2_in_user_field.getText();
		WebElement phone3_in_user_field=driver.findElement(By.xpath("//*[text()='Phone']//parent::div/span[3]"));
		String phone3_in_user_field_text=phone3_in_user_field.getText();
		if(phone2_in_user_field_text.contains("8989")&&phone3_in_user_field_text.contains("4949")){
			System.out.println("TC_76 Passed");
		}else{
			System.out.println("TC_76 Failed");
		}
	}
	//TC_77
	@Test(priority=22,dependsOnMethods={"verify_add_multiple_phone"})
	public void verify_type_dropdown_phone()throws InterruptedException{
		WebElement phone=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Phone']"));
		phone.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement dropdown=driver.findElement(By.xpath("//*[text()='Type']//parent::div//i"));
		dropdown.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		List<WebElement> dropdown_list= driver.findElements(By.xpath("//div[@class='v-list-item__title']"));
		for(int i=0;i<dropdown_list.size();i++){
			if(dropdown_list.get(i).getText().equalsIgnoreCase("Mobile")){
				dropdown_list.get(i).click();
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement submit_button=driver.findElement(By.xpath("//*[text()='Submit']"));
		submit_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement phone_in_user_field=driver.findElement(By.xpath("//*[text()='Phone']//parent::div/span[1]"));
		String phone_in_user_field_text=phone_in_user_field.getText();
		if(phone_in_user_field_text.contains("Mobile")){
			System.out.println("Value from Type dropdown selected successfully,TC_77 Passed");
		}else{
			System.out.println("Value from Type dropdown not selected,TC_77 Failed");
		}
	}
	//TC_78
	@Test(priority=23,dependsOnMethods={"verify_type_dropdown_phone"})
	public void verify_cancel_button_phone_dropdown()throws InterruptedException{
		WebElement phone=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Phone']"));
		phone.click();
		// Clicking on Cancel button
		WebElement Cancel_button=driver.findElement(By.xpath("//*[text()='Cancel']"));
		Cancel_button.click(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		Boolean phone_popup_title=driver.findElement(By.xpath("//div[@role='document']//*[text()='Phone']")).isDisplayed();
		if(!phone_popup_title){
			System.out.println("Phone popup closed,TC_78 Passed");
		}else{
			System.out.println("Phone popup not closed,TC_78 Failed");
		}	
	}
	//TC_79
	@Test(priority=24,dependsOnMethods={"verify_cancel_button_phone_dropdown"})
	public void verify_modify_phone()throws InterruptedException{
		WebElement phone_in_user_field=driver.findElement(By.xpath("//*[text()='Phone']//parent::div/span[1]"));
		String original_phone_number=phone_in_user_field.getText();
		System.out.println("original_phone_number"+original_phone_number);
		WebElement phone=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Phone']"));
		phone.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement phone1_field_clear=driver.findElement(By.xpath("//*[text()='Phone 1']//following::button[1]"));
		phone1_field_clear.click();
		Thread.sleep(2000);
		WebElement phone1_field=driver.findElement(By.xpath("//*[text()='Phone 1']//following::input[1]"));
		phone1_field.sendKeys("827819");
		WebElement submit_button=driver.findElement(By.xpath("//*[text()='Submit']"));
		submit_button.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		phone_in_user_field=driver.findElement(By.xpath("//*[text()='Phone']//parent::div/span[1]"));
		String updated_phone_number=phone_in_user_field.getText();
		System.out.println("updated_phone_number"+updated_phone_number);
		if(!updated_phone_number.contains(original_phone_number)){
			System.out.println("Mobile number updated,TC_79 Passed");
		}else{
			System.out.println("Mobile number not updated,TC_79 Failed");
		}
	}

	//TC_80
	@Test(priority=25,dependsOnMethods={"verify_modify_phone"})
	public void verify_delete_phone()throws InterruptedException{
		WebElement phone=driver.findElement(By.xpath("//div[@class='app-card']//*[text()='Phone']"));
		phone.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement phone2_field_clear=driver.findElement(By.xpath("//*[text()='Phone 1']//following::button[2]"));
		phone2_field_clear.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebElement submit_button=driver.findElement(By.xpath("//*[text()='Submit']"));
		submit_button.click();
		System.out.println("Phone deleted successfully,TC_80 Passed");
	}

	//TC_81
	@Test(priority=26,dependsOnMethods={"verify_delete_phone"})
	public void verify_phone_data_after_Refresh()throws InterruptedException{
		WebElement phone_in_user_field=driver.findElement(By.xpath("//*[text()='Phone']//parent::div/span[1]"));
		String phone_number_before_refresh=phone_in_user_field.getText();
		driver.navigate().refresh();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		phone_in_user_field=driver.findElement(By.xpath("//*[text()='Phone']//parent::div/span[1]"));
		String phone_number_after_refresh=phone_in_user_field.getText();
		if(phone_number_before_refresh.equals(phone_number_after_refresh)){
			System.out.println("Phone changes remains unaffected after refresh,TC_81 Passed");
		}else{
			System.out.println("Phone changes remains unaffected after refresh,TC_81 Failed");	
		}
	}

}


