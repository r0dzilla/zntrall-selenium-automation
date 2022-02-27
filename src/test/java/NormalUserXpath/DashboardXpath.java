package NormalUserXpath;

public class DashboardXpath {

	public static String username = "//input[@type='text']";
	public static String pass = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";

	public static String add = "//i[@class='v-icon notranslate material-icons theme--dark'][normalize-space()='add']";
	public static String add2 = "//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='add']";

	public static String options = "//div[@role='option']";
	public static String radioDropDown = "//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div/div[@role='button']/div/div/div/i[1]";
	public static String selectContinue = "//span[normalize-space()='Continue']";

	public static String locationName = "//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]";
	public static String licenceNumber = "//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]";
	public static String capacity = "//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/input[1]";
	public static String address = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String suiteUnit = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[2]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String city = "//div[@role='document']//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String state = "//div[@role='document']//div[3]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String zip = "//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/div/div/div/div/div[3]/div[1]/div[1]/div[1]/div[1]/input[1]";
	public static String phoneNumber = "//div[4]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String type = "//div[@class='v-input theme--light v-text-field v-text-field--is-booted v-select']//div[@class='v-select__selections']";
	public static String selectType = "//div[contains(text(),'Work')]";
	public static String email = "//input[@type='email']";
	public static String note = "//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String save = "//span[normalize-space()='Save']";

	public static String addressMsg = "//div[contains(text(),'Address is required')]";
	public static String cityMsg = "//div[contains(text(),'City is required')]";
	public static String stateMsg = "//div[contains(text(),'State is required')]";
	public static String phoneMsg = "//div[contains(text(),'Phone is required')]";
	public static String emailMsg = "//div[contains(text(),'E-mail must be valid')]";

	public static String invalidMsg = "//div[contains(text(),'E-mail must be valid')]";

	public static String prefix = "//input[@type='text']";
	public static String firstName = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String lastName = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[4]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String ssn = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String dob = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[2]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String gender = "//div[@role='document']//div[3]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]//i[1]";
	public static String phone = "//div[@role='document']//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String patientType = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//i[1]";
	public static String location = "//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]//i[1]";
	public static String patientNote = "//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String Submit = "//span[normalize-space()='Submit']";
	public static String ok = "//span[normalize-space()='OK']";
	public static String genderMale = "//div[contains(text(),'Male')]";
	public static String typeMobile = "//div[contains(text(),'Mobile')]";

	public static String firstNameMsg = "//div[contains(text(),'First name is required')]";
	public static String lastNameMsg = "//div[contains(text(),'Last name is required')]";
}
