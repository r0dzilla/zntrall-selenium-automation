package superAdminInputData;

public class LoginInfoData {

	//Login with valid info
	private String user = "superadmin@mercury.com";
	private String pass = "qwerty";

	//login without user name
	private String invalidUser = "";
	private String validPass = "qwerty";

	//login without password
	private String validUser = "superadmin@mercury.com";
	private String invalidPass = "";

	//login with invalid info
	private String invalidUserId = "superadmin@mercury.com";
	private String invalidPassId = "afvfd";

	
	
	
	//get-set for valid info
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

	//get-set for invalid user info
	public String getinvalidUser() {
		return invalidUser;
	}
	public void setinvalidUser(String invalidUser) {
		this.invalidUser = invalidUser;
	}
	public String getvalidPass() {
		return validPass;
	}
	public void setvalidPass(String validPass) {
		this.validPass = validPass;
	}


	//get-set for invalid password info
	public String getvalidUser() {
		return validUser;
	}
	public void setvalidUser(String validUser) {
		this.validUser = validUser;
	}
	public String getinvalidPass() {
		return invalidPass;
	}
	public void setinvalidPass(String invalidPass) {
		this.invalidPass = invalidPass;
	}


	//get-set for invalid info
	public String getinvalidUserId() {
		return invalidUserId;
	}
	public void setinvalidUserId(String invalidUserId) {
		this.invalidUserId = invalidUserId;
	}
	public String getinvalidPassId() {
		return invalidPassId;
	}
	public void setinvalidPassId(String invalidPassId) {
		this.invalidPassId = invalidPassId;
	}

}

