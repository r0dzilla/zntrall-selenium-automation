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
	public static String lastName = "//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]";
	public static String genderDropDown = "//form[@novalidate='novalidate']//div//div//div//div//div//div//div[@role='button']//div//div//div//i[@aria-hidden='true']";
	public static String gender = "//div[contains(text(),'Male')]";
	public static String phone = "//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]";
	public static String typeDropDown = "//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/i[1]";
	public static String type = "//div[contains(text(),'Work')]";
	public static String email = "//input[@type='email']";
	public static String title = "//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]";
	public static String note = "//body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[5]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]";
	public static String save = "//span[normalize-space()='SAVE']";
	public static String save2 = "//span[normalize-space()='Save']";
	public static String firstNameMsg = "//div[@role='alert']//div//div";
	public static String invalidMsg = "//div[contains(text(),'E-mail must be valid')]";

	public static String dataForEdit = "//tbody/tr[1]/td[1]";
	public static String selectEdit = "//span[normalize-space()='Edit']";
	public static String editPhone = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div//div//div[3]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String editTitle = "//div[@role='document']//div[5]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String phoneNumber = "//tbody/tr[1]/td[2]";

	public static String remove = "//span[normalize-space()='Remove']";

	public static String cancel = "//button[2]//span[1]";
	public static String close = "//div[@role='document']//div//div//div//span//button[@type='button']";



}
