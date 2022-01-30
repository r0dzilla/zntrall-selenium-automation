package superAdminXpath;

public class LocationXpath {

	public static String signInTitle = "//h2[@class='mb-3']";

	public static String username = "//input[@type='text']";
	public static String pass = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";

	public static String locations = "//span[normalize-space()='Locations']";
	public static String search = "//div[@class='v-text-field__slot']//input[@type='text']";
	public static String firstRow = "//table[1]/tbody[1]/tr[1]/td[2]/span[1]";
	public static String patient = "//*[text()='Patients']";
	public static String firstPatientInfo = "//table[1]/tbody[1]/tr[1]/td[1]/span[1]";
	public static String contact = "//*[text()='Contacts']";
	public static String firstContactInfo = "/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]";

	public static String types = "//*[text()='Types']";

	public static String typeInfo = "//body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]";
	public static String name = "//div[@class='v-card v-sheet theme--light']//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String acronym = "//div[@class='v-card__text']//div[2]//div[1]//div[1]//div[1]//input[1]";
	public static String update = "//*[text()='Update']";

	public static String addNewType = "//i[normalize-space()='add']";
	public static String save = "//span[normalize-space()='Save']";
	public static String lastRow = "(//table[1]//tbody[1]//tr)[last()]//td[2]";

	public static String save2 = "//*[text()='Save']";
	public static String reset = "//*[text()='Reset']";

	public static String nameErrorMsg = "//div[contains(text(),'Name is required')]";
	public static String acronymErrorMsg = "//div[contains(text(),'Acronym is required')]";

	public static String cancel = "//*[text()='Cancel']";

	public static String selectAddress = "//span[contains(text(),'Address')]";

	public static String selectStatus = "//span[contains(text(),'Status')]";
	public static String selectName = "//div[@class='container location-section pt-4 container--fluid grid-list-xl']//th[1]";

	public static String selectAcronym = "//div[@class='container location-section pt-4 container--fluid grid-list-xl']//th[2]";
}
