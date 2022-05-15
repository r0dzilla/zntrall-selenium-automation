package superAdminXpath;

public class ClientsXpath {

	public static String signInTitle = "//h2[@class='mb-3']";

	public static String username = "//input[@type='text']";
	public static String pass = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";

	public static String clients = "//span[normalize-space()='Clients']";
	public static String clientName = "//table[1]/tbody[1]/tr[1]/td[1]";
	public static String clientNameVerify = "//h2[contains(@class,'text-capitalize mb-0 header-title d-custom-flex')]";

	public static String status = "//button[@class='v-btn v-btn--flat v-btn--text theme--light v-size--default primary--text']";
	public static String statusInactive = "//div[contains(text(),'Inactive')]";

	public static String statusActive = "//div[contains(text(),'Active')]";

	public static String locationTab = "//a[contains(text(),'Locations')]";
	public static String locationInfo = "/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]";

	public static String patientTab = "//a[contains(text(),'Patients')]";
	public static String patientInfo = "/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]";

	public static String search = "//body/div[@data-app='true']/div/div/main[@data-booted='true']/div/div/div/div/div/div/div/div/div/div/div/div/div/input[1]";
	public static String firstRow = "//table[1]/tbody[1]/tr[1]/td[1]";

	public static String element = "//*[text()='Types']";
	public static String add = "//i[normalize-space()='add']";

	public static String clientGroup = "//strong[normalize-space()='Client Group']";
	public static String name = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String acronym = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String signUp = "//span[@class='v-chip__content'][normalize-space()='Sign Up']";
	public static String signUpStatus = "//span[@class='v-chip__content'][normalize-space()='Status']";
	public static String submit = "//span[normalize-space()='Add']";

	public static String selectAnyGroup = "//span[normalize-space()='Client Group 2']";
	public static String update = "//span[normalize-space()='Update']";
	public static String selectName = "//span[contains(text(),'Name')]";
	public static String selectType = "//span[contains(text(),'Type')]";
	public static String userTab = "//a[contains(text(),'Users')]";
	public static String userInfo = "//tbody//tr//td[1]";


}
