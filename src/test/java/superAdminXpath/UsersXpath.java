package superAdminXpath;

public class UsersXpath{

	public static String signInTitle = "//h2[@class='mb-3']";

	public static String username = "//input[@type='text']";
	public static String pass = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";

	public static String users = "//span[normalize-space()='Users']";
	public static String usersName = "//table[1]/tbody[1]/tr[2]/td[1]";
	public static String userNameVerify = "//div[@class='media-body']";

	public static String clickUser = "//td[1]";

	public static String status = "//button[@class='v-btn v-btn--flat v-btn--text theme--light v-size--default primary--text']";
	public static String statusInactive = "//div[contains(text(),'Inactive')]";

	public static String statusActive = "//div[contains(text(),'Active')]";

	public static String search = "//body/div[@data-app='true']/div/div/main[@data-booted='true']/div/div/div/div/div/div/div/div/div/div/div/div/div/input[1]";
	public static String firstRow = "//table[1]/tbody[1]/tr[1]/td[1]";

	public static String selectName = "//span[contains(text(),'First Name')]";
	public static String selectLastName = "//span[contains(text(),'Last Name')]";
	public static String selectEmail = "//span[contains(text(),'Email')]";

	public static String element = "//*[text()='Types']";

	public static String userRead = "//body/div[@id='inspire']/div[@role='document']/div/div/div/form[@novalidate='novalidate']/div[4]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]";
	public static String userAdd = "//div[4]//div[1]//div[2]//div[1]//div[2]//div[1]//span[2]";
	public static String userStatus = "//div[4]//div[1]//div[2]//div[1]//div[2]//div[1]//span[3]";
	public static String userClient = "//div[4]//div[1]//div[2]//div[1]//div[2]//div[1]//span[4]";
	public static String userChat = "//div[4]//div[1]//div[2]//div[1]//div[2]//div[1]//span[5]";

	public static String locationRead = "//body/div[@id='inspire']/div[@role='document']/div/div/div/form[@novalidate='novalidate']/div[5]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]";
	public static String locationAdd = "//div[5]//div[1]//div[2]//div[1]//div[2]//div[1]//span[2]";
	public static String locationEdit = "//div[5]//div[1]//div[2]//div[1]//div[2]//div[1]//span[3]";
	public static String locationDelete = "//div[5]//div[1]//div[2]//div[1]//div[2]//div[1]//span[4]";

	public static String patientRead = "//body/div[@id='inspire']/div[@role='document']/div/div/div/form[@novalidate='novalidate']/div[6]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]";
	public static String patientAdd = "//div[6]//div[1]//div[2]//div[1]//div[2]//div[1]//span[2]";
	public static String patientEdit = "//div[6]//div[1]//div[2]//div[1]//div[2]//div[1]//span[3]";
	public static String patientField = "//div[6]//div[1]//div[2]//div[1]//div[2]//div[1]//span[4]";
	public static String patientDelete = "//div[6]//div[1]//div[2]//div[1]//div[2]//div[1]//span[5]";

	public static String calendarRead = "//body/div[@id='inspire']/div[@role='document']/div/div/div/form[@novalidate='novalidate']/div[7]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]";
	public static String calendarEdit = "//div[7]//div[1]//div[2]//div[1]//div[2]//div[1]//span[2]";

	public static String contactRead = "//body/div[@id='inspire']/div[@role='document']/div/div/div/form[@novalidate='novalidate']/div[8]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]";
	public static String contactAdd = "//div[8]//div[1]//div[2]//div[1]//div[2]//div[1]//span[2]";
	public static String contactEdit = "//div[8]//div[1]//div[2]//div[1]//div[2]//div[1]//span[3]";
	public static String contactAddProvider = "//div[8]//div[1]//div[2]//div[1]//div[2]//div[1]//span[4]";
	public static String contactDelete = "//div[8]//div[1]//div[2]//div[1]//div[2]//div[1]//span[5]";

	public static String endOfCareForms = "//body/div[@id='inspire']/div[@role='document']/div/div/div/form[@novalidate='novalidate']/div[9]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]";
	public static String startOfCareForms = "//div[9]//div[1]//div[2]//div[1]//div[2]//div[1]//span[2]";
	public static String form4 = "//div[9]//div[1]//div[2]//div[1]//div[2]//div[1]//span[3]";
	public static String testForm3 = "//div[9]//div[1]//div[2]//div[1]//div[2]//div[1]//span[4]";
	public static String testForm2 = "//div[9]//div[1]//div[2]//div[1]//div[2]//div[1]//span[5]";
	public static String testForm = "//div[9]//div[1]//div[2]//div[1]//div[2]//div[1]//span[6]";

	public static String add = "//i[normalize-space()='add']";
	public static String addUserGroup = "//button[@class='pl-2 pr-2 v-btn v-btn--contained theme--dark v-size--default primary']//span[@class='v-btn__content']";
	public static String adminName = "//body//div[@data-app='true']//div[@role='document']//div//div//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String adminAcronym = "//body//div[@data-app='true']//div[@role='document']//div//div//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String statusCheck = "//input[@role='checkbox']";
	public static String userViewTitle = "//div[@role='document']//div//div//div//div[2]//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String userViewAcronym = "//div[@role='document']//div//div//div//div[2]//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String description = "//div[@role='document']//div[3]//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String addGroup = "//span[@class='v-btn__content'][normalize-space()='Add']";
	public static String lastRow = "(//table[1]//tbody[1]//tr)[last()]//td[1]";

	public static String update = "//span[normalize-space()='Update']";

	public static String cardUser = "//body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]";
	public static String clickUser2 = "//td[3]";


}
