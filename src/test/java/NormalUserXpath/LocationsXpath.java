package NormalUserXpath;

public class LocationsXpath {
	public static String username = "//input[@type='text']";
	public static String pass = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";

	public static String locations = "//span[normalize-space()='Locations']";

	public static String search = "//div[@class='v-text-field__slot']//input[@type='text']";
	public static String firstRow = "//tbody/tr[1]/td[1]";

	public static String add = "//i[normalize-space()='add']";
	public static String selectResidentDropdown = "//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/div/div/div/div[@role='button']/div/div/div/i[1]";
	public static String selectResidentType = "//div[@role='listbox']//div[3]//div[1]//div[1]";
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
	public static String typeSelect = "//body/div[@data-app='true']/div[5]/div[1]/div[2]/div[1]/div[1]";
	public static String email = "//input[@type='email']";
	public static String note = "//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String save = "//span[normalize-space()='Save']";

	public static String addressMsg = "//div[contains(text(),'Address is required')]";
	public static String cityMsg = "//div[contains(text(),'City is required')]";
	public static String stateMsg = "//div[contains(text(),'State is required')]";
	public static String phoneMsg = "//div[contains(text(),'Phone is required')]";
	public static String emailMsg = "//div[contains(text(),'E-mail must be valid')]";

	public static String phoneMsg2 = "//div[contains(text(),'Phone Number minimum 10 digit required')]";

	public static String back = "//span[normalize-space()='Back']";

	public static String cancel = "//span[normalize-space()='Cancel']";

	public static String addPatientButton = "//strong[normalize-space()='Patient']";

	public static String FirstName = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String LastName = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[4]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String ssn = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String phoneNumber2 = "//div[@role='document']//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";

	public static String submit = "//span[normalize-space()='Submit']";

	public static String firstNameMsg = "//div[contains(text(),'First name is required')]";
	public static String lastNameMsg = "//div[contains(text(),'Last name is required')]";

	public static String addContactButton = "//strong[normalize-space()='Contact']";
	public static String FirstName2 = "//input[@required='required']";

	public static String contactButton = "//a[contains(text(),'Contacts')]";

	public static String firstRowInfo = "//div[@class='v-card__title headline font-weight-bold']";
	public static String closeInfo = "//button[@class='text-capitalize v-btn v-btn--flat v-btn--text theme--light v-size--default primary--text']";

	public static String firstNameMsg2 = "//div[@class='v-messages__message']";

	public static String editButton = "//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='more_vert']";
	public static String editOptionSelect = "//body/div[@data-app='true']/div[@role='menu']/div/div[1]/div[1]";
	public static String editLicenceNumber = "//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String editCapacity = "//div[8]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String update = "//span[normalize-space()='update']";

	public static String deleteButton = "//div[contains(text(),'Delete')]";
	public static String deletePopUpYes = "//span[normalize-space()='yes']";

	public static String patients = "//span[normalize-space()='Patients']";
	public static String add2 = "//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']";
	public static String selectAddPatient = "//div[contains(text(),'Add New Patient')]";

	public static String addNewLocation = "//span[normalize-space()='Add / Set a location']";
	public static String addButton = "//strong[1]";
	public static String selectType = "//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div//div//div//div//div[3]//div[1]//div[1]";

	public static String continueButton = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//button[1]";

	
	public static String elements = "//tbody/tr[23]/td[1]";
	public static String view = "//div[6]//div[1]//div[2]//div[1]//div[2]//div[1]//span[1]//span[1]";
	public static String updates = "//form[@novalidate='novalidate']//button[1]";
	public static String add3 = "//div[6]//div[1]//div[2]//div[1]//div[2]//div[1]//span[2]//span[1]";
	public static String edit = "//div[6]//div[1]//div[2]//div[1]//div[2]//div[1]//span[3]//span[1]";
	public static String delete = "//div[6]//div[1]//div[2]//div[1]//div[2]//div[1]//span[4]//span[1]";
}
