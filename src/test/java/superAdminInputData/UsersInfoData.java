package superAdminInputData;

public class UsersInfoData {

	//Login with valid info
	private String user = "superadmin@mercury.com";
	private String pass = "qwerty";

	//search item for location
	private String searchUser = "John";	

	//Add user group
	private String admin = "Dynamic Admin -2";
	private String acronym = "DA2";
	private String viewTitle = "DAdmin2";
	private String viewAcronym = "DAN2";
	private String description = "This is test ";






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



	//Search item for location
	public String getsearchUser() {
		return searchUser;
	}
	public void setsearchUser(String searchUser) {
		this.searchUser = searchUser;
	}


	//add user group
	public String getadmin() {
		return admin;
	}
	public void setadmin(String admin) {
		this.admin = admin;
	}

	public String getacronym() {
		return acronym;
	}
	public void setacronym(String acronym) {
		this.acronym = acronym;
	}

	public String getviewTitle() {
		return viewTitle;
	}
	public void setviewTitle(String viewTitle) {
		this.viewTitle = viewTitle;
	}

	public String getviewAcronym() {
		return viewAcronym;
	}
	public void setviewAcronym(String viewAcronym) {
		this.viewAcronym = viewAcronym;
	}

	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
}
