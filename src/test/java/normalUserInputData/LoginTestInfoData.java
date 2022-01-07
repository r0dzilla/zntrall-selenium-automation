package normalUserInputData;

public class LoginTestInfoData {

	//Login with valid info
	private String user = "shaque.sabbir@gmail.com";
	private String pass = "Sabbir33";

	//Login without username	
	private String invaliduser = "";


	//Login without password	
	private String invalidpass = "";


	
	
	
	
	
	
	//login with valid info
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


	//login without username
	public String getinvaliduser() {
		return invaliduser;
	}
	public void setinvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}


	//Login without password
	public String getinvalidpass() {
		return invalidpass;
	}
	public void setinvalidpass(String invalidpass) {
		this.invalidpass = invalidpass;
	}
}
