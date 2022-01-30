package NormalUserXpath;

public class ContactsXpath {

	public static String username = "//input[@type='text']";
	public static String pass = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";

	public static String contacts = "//span[normalize-space()='Contacts']";

	public static String search = "//div[@class='v-text-field__slot']//input[@type='text']";
	public static String firstRow = "//tbody/tr[1]/td[1]";
	public static String add = "//i[normalize-space()='add']";

	public static String firstName = "//input[@required='required']";
	public static String lastName = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div//div//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]";
	public static String genderDropDown = "//form[@novalidate='novalidate']//div//div//div//div//div//div//div[@role='button']//div//div//div//i[@aria-hidden='true']";
	public static String gender = "//div[contains(text(),'Male')]";
	public static String phone = "//div[@role='document']//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String typeDropDown = "//body/div[@id='inspire']/div[@role='document']/div/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div/div[@role='button']/div/div/div/i[1]";
	public static String type = "//div[contains(text(),'Work')]";
	public static String email = "//input[@type='email']";
	public static String title = "//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String note = "//div[6]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String save = "//span[normalize-space()='Save']";

	public static String firstNameMsg = "//div[@class='v-messages__message']";
	public static String invalidMsg = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]";

	public static String dataForEdit = "//tbody/tr[1]/td[1]";
	public static String selectEdit = "//span[normalize-space()='Edit']";
	public static String editPhone = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String editTitle = "//div[@role='document']//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String phoneNumber = "//tbody/tr[1]/td[2]";

	public static String remove = "//span[normalize-space()='Remove']";

	public static String cancel = "//button[@class='float-right text-capitalize align-cancel v-btn v-btn--flat v-btn--text theme--light v-size--default']//span[@class='v-btn__content'][normalize-space()='Cancel']";
	public static String close = "//div[@class='v-dialog v-dialog--active v-dialog--scrollable']//button[@type='button'][normalize-space()='close']";



}
