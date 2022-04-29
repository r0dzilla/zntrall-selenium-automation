package NormalUserXpath;

public class UsersXpath {

	public static String username = "//input[@type='text']";
	public static String password = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";
	
	public static String userList = "//span[normalize-space()='Users']";
	
	public static String add = "//i[normalize-space()='add']";
	public static String addUser = "//strong[normalize-space()='Add new user']";
	
	public static String FirstName = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	public static String LastName = "//body/div[@data-app='true']/div[@role='document']/div/div/div/div/div/div/form[@novalidate='novalidate']/div/div/div/div/div/div[1]/div[1]/div[1]/input[1]";
	public static String emailId = "//input[@type='email']";

	public static String save = "//span[normalize-space()='save']";
	
	public static String firstRow = "//tbody/tr[1]/td[3]";
	
	public static String status = "//div[@role='status']";
	
	public static String invalidMsg = "//div[@class='v-messages__message']";
	public static String reset = "//span[normalize-space()='Reset']";
	
	public static String actualText1 = "//div[@class='col-sm-12 col-md-6 col']//div[@class='v-messages__message']";
	public static String actualText2 = "//div[@class='col-md-6 col']//div[@class='v-messages__message']";
	public static String actualText3 = "//div[contains(text(),'E-mail must be valid')]";
	
	public static String cancel = "//span[normalize-space()='Cancel']";
	public static String close = "//button[normalize-space()='close']";
	
	public static String search = "//input[@type='text']";
	public static String firstRowInfo = "//tbody[1]/tr[1]/td[1]";
	public static String user = "//a[@class='router-link-active']";
}
