package NormalUserXpath;

public class RegistrationXpath {

	public static String signUpButton = "//span[normalize-space()='Create Account']";
	public static String signUpTitle = "//h2[@class='mb-3']";
	
	public static String firstName = "//body/div[@data-app='true']/div/div/div/div/div/form[@novalidate='novalidate']/div[1]/div[1]/div[1]/div[1]/input[1]";
	public static String LastName = "//body//div[@data-app='true']//div//div//div//div[2]//div[1]//div[1]//div[1]//input[1]";
	public static String email = "//div[3]//div[1]//div[1]//div[1]//input[1]";
	public static String verifiedemail = "//div[4]//div[1]//div[1]//div[1]//input[1]";
	public static String checkmark = "//div[9]//div[1]//div[1]";
	public static String termsOfServices = "//div[7]//div[1]//div[1]//div[1]//div[1]";
	public static String signUp = "//button[@class='v-btn v-btn--contained theme--light v-size--default primary']";
	
	public static String checkmark2 = "//div[@class='v-input theme--light v-input--selection-controls v-input--radio-group v-input--radio-group--column']//div[2]//div[1]//div[1]";
	public static String signUp2 = "//span[normalize-space()='Continue']";
	
	public static String userField = "//div[@class='v-messages__message']";
	public static String alert = "//div[@role='status']";
	
	public static String signUp3 = "//body/div[@data-app='true']/div/div/div/div/div/form[@novalidate='novalidate']/button[1]";
}
