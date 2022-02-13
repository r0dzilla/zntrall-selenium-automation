package NormalUserXpath;

public class PatientsXpath {

	public static String username = "//input[@type='text']";
	public static String password = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";

	public static String patients = "//span[normalize-space()='Patients']";

	public static String search = "//div[@class='v-text-field__slot']//input[@type='text']";
	public static String firstRow = "//tbody/tr[1]/td[2]";

	public static String add = "//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']";
	public static String selectAddPatient = "//div[contains(text(),'Add New Patient')]";

	public static String prefix = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String firstName = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String lastName = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[4]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String ssn = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String dob = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div[2]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String gender = "//div[3]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]";
	public static String phone = "//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String type = "//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//i[1]";
	public static String email = "//input[@type='email']";
	public static String location = "//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//div[1]//i[1]";
	public static String note = "//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String save = "//span[normalize-space()='Submit']";
	public static String save2 = "//span[normalize-space()='Save']";
	public static String ok = "//span[normalize-space()='OK']";
	public static String genderMale = "//div[contains(text(),'Male')]";
	public static String genderMale2 = "//body/div[@class='v-application v-application--is-ltr theme--light']/div[@class='v-menu__content theme--light v-menu__content--fixed menuable__content__active']/div[@role='listbox']/div[1]/div[1]";
	public static String typeMobile = "//div[@class='v-list-item__title'][normalize-space()='Mobile']";
	public static String selectLocation = "//div[@class='v-menu__content theme--light v-menu__content--fixed menuable__content__active']//div[2]";

	public static String firstNameMsg = "//div[contains(text(),'First name is required')]";
	public static String lastNameMsg = "//div[contains(text(),'Last name is required')]";

	public static String editButton = "//i[@class='v-icon notranslate material-icons theme--light'][normalize-space()='more_vert']";
	public static String editOptionSelect = "//div[contains(text(),'Edit')]";
	public static String editSSN = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String update = "//span[normalize-space()='Update']";

	public static String deleteButton = "//div[contains(text(),'Delete')]";
	public static String deletePopUpYes = "//span[normalize-space()='yes']";

	public static String addButton1 = "//i[@class='v-icon notranslate zmdi zmdi zmdi zmdi-plus theme--light']";
	public static String addContactButton = "//div[normalize-space()='Contact']";
	public static String FirstName = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String LastName = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div//div[1]//div[3]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String Gender = "//form[@novalidate='novalidate']//div//div//div//div//div//div//div[@role='button']//div//div//div//i[@aria-hidden='true']";
	public static String GenderMale = "//div[@class='v-list-item__title'][normalize-space()='Male']";
	public static String relationship = "//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div/div/div[@role='button']/div/div/div/i[1]";
	public static String relationshipFather = "//div[contains(text(),'Father')]";
	public static String Phone = "//div[@role='document']//div[4]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String phoneDropdown = "//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div/div[@role='button']/div/div/div/i[1]";
	public static String phoneTypeSelect = "//div[contains(text(),'Home')]";
	public static String contactNameSelect = "//tbody//tr";

	public static String Note = "//div[7]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String FirstNameMsg = "//div[@class='v-messages__message']";
	public static String relationMsg = "//div[contains(text(),'Relationship is required')]";

	public static String addScheduleButton = "//div[contains(text(),'Schedule')]";
	public static String startDate = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String select1 = "//tbody/tr[5]/td[2]/button[1]";
	public static String select2 = "//body//div[@id='inspire']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[1]//div[1]//div[1]";
	public static String select3 = "//div[5]//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String contactNameSelect2 = "//section//div[7]";
	public static String actual = "/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]";
	
	public static String select4 = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String select5 = "//tbody/tr[4]/td[4]/button[1]";
	public static String select6 = "//body//div[@id='inspire']//div[@role='document']//div//div//div//div//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]//i[1]";

	public static String status = "//div[@role='status']";
	public static String cancel = "//button[@class='float-right text-capitalize align-cancel v-btn v-btn--flat v-btn--text theme--light v-size--default']//span[@class='v-btn__content'][normalize-space()='Cancel']";
	public static String close = "//button[@class='v-btn v-btn--flat v-btn--text theme--light v-size--default blue--text text--darken-1']//span[@class='v-btn__content'][normalize-space()='Close']";

	public static String importButton = "//div[@class='v-list-item__content'][normalize-space()='Import']";
	public static String dropFile = "//div[@id='dropzone']";
	public static String preview = "//div[@role='document']//div[3]//div[1]//button[1]";
	
	public static String addLocation = "//body/div[@id='inspire']/div/div/main[@data-booted='true']/div/div/div/div/div/div/div/div/div/div/div/div/button[1]";

	public static String selectNewLocation = "//div[@role='menu']//div[2]";
}
