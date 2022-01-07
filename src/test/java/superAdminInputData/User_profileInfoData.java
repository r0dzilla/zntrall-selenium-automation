package superAdminInputData;

public class User_profileInfoData {

	//Login with valid info
	private String user = "superadmin@mercury.com";
	private String pass = "qwerty";

	//Add phone number
	private String addPhone = "123456789";

	//Update email
	private String firstMail = "a@email.co";
	private String secondMail = "a@email.co";

	//Add address
	private String streetAddress = "";
	private String unit = "1";
	private String zip = "1234";
	private String city = "Dhk";
	private String state = "Nikunja";
	private String poBox = "Khilkhet";

	//Change password

	private String currentPass = "Sabbir33";
	private String newPass = "Sabbir33";



	//Login for valid info
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}


	//Add phone number 
	public String getPhone() {
		return addPhone;
	}
	public void setPhone(String addPhone) {
		this.addPhone = addPhone;
	}

	//update email
	public String getfirstMail() {
		return firstMail;
	}
	public void setfirstMail(String firstMail) {
		this.firstMail = firstMail;
	}
	public String getsecondMail() {
		return secondMail;
	}
	public void setsecondMail(String secondMail) {
		this.secondMail = secondMail;
	}


	//add address
	public String getstreetAddress() {
		return streetAddress;
	}
	public void setstreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getunit() {
		return unit;
	}
	public void setunit(String unit) {
		this.unit = unit;
	}

	public String getzip() {
		return zip;
	}
	public void setzip(String zip) {
		this.zip = zip;
	}

	public String getcity() {
		return city;
	}
	public void setcity(String city) {
		this.city = city;
	}

	public String getstate() {
		return state;
	}
	public void setstate(String state) {
		this.state = state;
	}

	public String getpoBox() {
		return poBox;
	}
	public void setpoBox(String poBox) {
		this.poBox = poBox;
	}


	//Change password
	public String getcurrentPass() {
		return currentPass;
	}
	public void setcurrentPass(String currentPass) {
		this.currentPass = currentPass;
	}

	public String getnewPass() {
		return newPass;
	}
	public void setnewPass(String newPass) {
		this.newPass = newPass;
	}
}
