package superAdminInputData;

public class ClientsInfoData {

	//Login with valid info
	private String user = "superadmin@mercury.com";
	private String pass = "qwerty";

	//name and acronym for add new client group
	private String name = "Test SQA 2022";
	private String acronym = "TSQA22";

	//edit name and acronym for a client group
	private String editName = "Test SQA 2022";
	private String editAcronym = "TSQA22";


	//login for valid info
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


	//name and acronym for add new client group
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


	//edit name and acronym for a client group
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

}
