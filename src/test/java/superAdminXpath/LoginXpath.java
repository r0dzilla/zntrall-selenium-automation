package superAdminXpath;

public class LoginXpath {
	
	public static String signInTitle = "//h2[@class='mb-3']";

	public static String username = "//input[@type='text']";
	public static String pass = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";

	public static String userFieldMsg = "//div[@class='v-messages__message']";

	public static String checkbox = "//div[@class='v-input--selection-controls__ripple']";
	public static String error = "//div[@role='status']";
}
