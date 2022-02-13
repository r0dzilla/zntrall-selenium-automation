package NormalUserXpath;

public class CalendarXpath {

	public static String username = "//input[@type='text']";
	public static String pass = "//input[@type='password']";
	public static String login = "//form[@novalidate='novalidate']//button[1]";

	public static String calendar = "//span[normalize-space()='Calendar']";

	public static String selectDate = "//body/div[@id='inspire']/div/div/main[@data-booted='true']/div/div/button[1]";
	public static String patientSelectDropDown = "//div[@role='combobox']//div//div//div//i[@aria-hidden='true']";
	public static String patientSelect = "//div[@role='option']";
	//public static String dateFieldSelect = "//body//div[@data-app='true']//div[@role='document']//div//div//div//div//div//div//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]";
	//public static String dateSelect = "//div[@class='v-btn__content'][normalize-space()='30']";
	public static String allDay = "//span[contains(text(),'All day')]";
	public static String description = "//div[@class='v-input theme--light v-text-field v-text-field--is-booted']//input[@type='text']";
	public static String save = "//span[normalize-space()='Save']";
	public static String errorMsg = "//div[@role='status']";

	public static String selectSchedule = "//div[@class='v-calendar-weekly__day v-present']//div[2]//div[1]";
	public static String selectThreeDotMenu = "//div[@class='v-menu__content theme--light menuable__content__active']//i[@class='v-icon notranslate mdi mdi-dots-vertical theme--light']";
	public static String selectCancel = "//*[contains(text(), 'Cancel')]";

	//public static String selectSchedule2 = "/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[5]/div[3]/div[2]/div[1]"; 
	public static String selectDelete = "//*[contains(text(), 'Delete')]";
	public static String selectRecurringOption = "//label[normalize-space()='All events']";
	public static String delete = "//button[@class='ml-2 v-btn v-btn--depressed v-btn--flat v-btn--outlined theme--light v-size--default danger--text']//span[@class='v-btn__content'][normalize-space()='Delete']";

	//public static String selectSchedule3 = "//div[@class='pl-1'][normalize-space()='Testing data']"; 
	public static String selectEdit = "//*[contains(text(), 'Edit')]";
	public static String selectPatient = "//div[contains(text(),'Sabbir-Test Info')]";

	public static String selectType = "//button[@role='button']//i[@aria-hidden='true']";
	public static String selectDay = "//div[contains(text(),'Day')]";

	public static String selectWeek = "//div[contains(text(),'Week')]";
	public static String selectMonth = "//div[contains(text(),'Month')]";
	public static String selectAgenda = "//div[contains(text(),'Agenda')]";
}
