package normalUserInputData;

public class UsersInfoData {

	//Login with valid info
	private String user = "shaque.sabbir@gmail.com";
	private String pass = "Sabbir33";


	//Adding user with valid info
	private String FirstName = "Sabbir";
	private String LastName = "ahmed";
	private String emailId = "shaque.sabbir@gmail.com";

	//Add new user form -- validation
	private String invalidFirstName = "";
	private String invalidLastName = "";
	private String invalidemailId = "";

	//search by email
	private String search = "shaque.sabbir@gmail.com";


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

	//Adding user with valid info
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	public String getemailId() {
		return emailId;
	}
	public void setemailId(String emailId) {
		this.emailId = emailId;
	}


	//Add new user form -- validation

	public String getinvalidFirstName() {
		return invalidFirstName;
	}
	public void setinvalidFirstName(String invalidFirstName) {
		this.invalidFirstName = invalidFirstName;
	}
	public String getinvalidLastName() {
		return invalidLastName;
	}
	public void setinvalidLastName(String invalidLastName) {
		this.invalidLastName = invalidLastName;
	}
	public String getinvalidemailId() {
		return invalidemailId;
	}
	public void setinvalidemailId(String invalidemailId) {
		this.invalidemailId = invalidemailId;
	}


	//search by email
	public String getsearch() {
		return search;
	}
	public void setsearch(String search) {
		this.search = search;
	}
}
