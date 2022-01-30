package NormalUserXpath;

public class LoginTestXpath {

	public static String signInTitle = "//h2[@class='mb-3']";
	public static String emailTitle = "//input[@type='text']";
	public static String passTitle = "//input[@type='password']";
	public static String loginButton = "//button[@disabled='disabled']";
	
	public static String username = "//input[@type='text']";
	public static String password = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";
	
	public static String loginAs = "//span[normalize-space()='Counsellors']";
	public static String userField = "//div[@class='v-messages__message']";
	
	public static String checkbox = "//div[@class='v-input--selection-controls__ripple']";
	
	public static String forgotPass = "//a[normalize-space()='Forgot Password?']";
	public static String emailId = "//input[@type='text']";
	public static String sendEmail = "//body/div[@data-app='true']/div/div/div/div/div/form[@novalidate='novalidate']/button[1]";
}
