package NormalUserXpath;

public class User_ProfileXpath {

	public static String username = "//input[@type='text']";
	public static String password = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";
	
	public static String userProfile = "//body/div[@data-app='true']/div/div/div/nav[@data-booted='true']/div/div/section/header[@data-booted='true']/div/div/div/div[2]/div[1]";

	public static String phoneNumber = "//h5[normalize-space()='Phone']";
	public static String selectPhoneNumber = "//input[@required='required']";
	public static String phoneNumberType = "//body//div[@data-app='true']//div[@role='document']//div//div//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]//i[1]";
	public static String selectPhoneNumberType = "//div[contains(text(),'Work')]";
	public static String save = "//span[normalize-space()='Submit']";
	public static String invalidMsg = "//div[@role='alert']//div//div";
	
	public static String gender = "//h5[normalize-space()='Gender']";
	public static String selectGender = "//div[@role='radiogroup']//div[1]//div[1]//div[1]";
	
	public static String email = "//h5[normalize-space()='Email']";
	public static String newEmail = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[1]//input[1]";
	public static String confirmEmail = "//div[@role='document']//div[3]//div[1]//div[1]//div[1]//input[1]";
	public static String save2 = "//span[@class='v-btn__content'][normalize-space()='Submit']";
	public static String invalidMsg2 = "//div[@role='alert']";
	
	public static String address = "//h5[normalize-space()='Address']";
	public static String streetAddress = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/input[1]";
	public static String unit = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/div[1]/input[1]";
	public static String zip = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[1]/div[1]/input[1]";
	public static String city = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/form[1]/div[4]/div[1]/div[1]/div[1]/input[1]";
	public static String state = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/form[1]/div[5]/div[1]/div[1]/div[1]/input[1]";
	public static String poBox = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/form[1]/div[6]/div[1]/div[1]/div[1]/input[1]";
	public static String submit = "//span[@class='v-btn__content'][normalize-space()='Submit']";
	public static String invalidMsg3 = "//div[@class='v-messages__message']";
	
	public static String password2 = "//h5[normalize-space()='Password']";
	public static String requestOTP = "//a[@class='font-sm mt-auto primary--text font-weight-bold']";
	public static String currentPass = "//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[1]//input[1]";
	public static String newPass = "//div[@role='document']//div[3]//div[1]//div[1]//div[1]//input[1]";
	
}
