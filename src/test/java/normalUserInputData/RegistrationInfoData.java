package normalUserInputData;

public class RegistrationInfoData {

	// Registration with all valid data
	private String firstName = "Sabbir-Test";
	private String lastName = "Info";
	private String email = "sabbirtest@email.com";
	private String verifiedemail = "sabbirtest@email.com";


	// Registration - First Name field validation
	private String invalidfirstName = "fgggfggfgfgfgfgfgfgfgfgfg";

	// Registration-- Last Name field validation
	private String invalidlastName = "fgggfggfgfgfgfgfgfgfgfgfg";

	// Registration -- user email field validation
	private String invalidemail = "sabbirtest22@email.com";



	
	

	// Registration with all valid data
	public String getfirstName() {
		return firstName;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getlastName() {
		return lastName;
	}
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getverifiedemail() {
		return verifiedemail;
	}
	public void setverifiedemail(String verifiedemail) {
		this.verifiedemail = verifiedemail;
	}


	// Registration - First Name field validation
	public String getinvalidfirstName() {
		return invalidfirstName;
	}
	public void setinvalidfirstName(String invalidfirstName) {
		this.invalidfirstName = invalidfirstName;
	}



	// Registration-- Last Name field validation
	public String getinvalidlastName() {
		return invalidlastName;
	}
	public void setinvalidlastName(String invalidlastName) {
		this.invalidlastName = invalidlastName;
	}


	// Registration -- user email field validation
	public String getinvalidemail() {
		return invalidemail;
	}
	public void setinvalidemail(String invalidemail) {
		this.invalidemail = invalidemail;
	}
}
