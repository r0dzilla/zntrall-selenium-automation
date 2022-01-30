package superAdminXpath;

public class PatientsXpath {

	public static String signInTitle = "//h2[@class='mb-3']";

	public static String username = "//input[@type='text']";
	public static String pass = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";
	
	public static String patients = "//span[normalize-space()='Patients']";
	public static String search = "//div[@class='v-text-field__slot']//input[@type='text']";
	public static String firstRow = "//table[1]/tbody[1]/tr[1]/td[2]/span[1]";
	public static String selectName = "//span[normalize-space()='First']";
	public static String selectLocation = "//span[normalize-space()='Location']";
//	public static String contact = "//*[text()='Contacts']";
}
