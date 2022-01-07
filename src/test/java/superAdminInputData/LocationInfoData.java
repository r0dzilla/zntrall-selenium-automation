package superAdminInputData;

public class LocationInfoData {

	//Login with valid info
	private String user = "superadmin@mercury.com";
	private String pass = "qwerty";


	//search item for location
	private String searchLocation = "Rang";


	//update name and acronym

	private String editName = "Room and Board";
	private String editAcronym = "RB";

	//name and acronym for add new location type
	private String name = "Test SQA 2022";
	private String acronym = "TSQA22";





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
	public String getsearchLocation() {
		return searchLocation;
	}
	public void setsearchLocation(String searchLocation) {
		this.searchLocation = searchLocation;
	}


	//update name and acronym
	public String geteditName() {
		return editName;
	}
	public void seteditName(String editName) {
		this.editName = editName;
	}
	public String geteditAcronym() {
		return editAcronym;
	}
	public void seteditAcronym(String editAcronym) {
		this.editAcronym = editAcronym;
	}

	//name and acronym for add new location type
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAcronym() {
		return acronym;
	}
	public void setacronym(String acronym) {
		this.acronym = acronym;
	}
}
