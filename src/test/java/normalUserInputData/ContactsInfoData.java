package normalUserInputData;

public class ContactsInfoData {

	//Login with valid info
	private String user = "shaque.sabbir@gmail.com";
	private String pass = "Sabbir33";

	//search item for location
	private String search = "Sabbir";

	//add contact with valid info
	private String firstName = "Sabbir";
	private String lastName = "Ahmed";
	private String phone = "789456123";
	private String email = "abc@email.com";
	private String title = "This is test";
	private String note = "lorem ipsum";


	//add contact with invalid/empty info
	private String emptyfirstName = "";
	private String emptylastName = "";
	private String emptyphone = "";
	private String emptyemail = "";
	private String emptytitle = "";
	private String emptynote = "";
	private String invalidemail = "@abc.skfh";


	//Edit contact from the list
	private String editphone = "789456123";
	private String edittitle = "This is test";







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


	//Search item for location
	public String getsearch() {
		return search;
	}
	public void setsearch(String search) {
		this.search = search;
	}


	//add contact with valid info
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

	public String getphone() {
		return phone;
	}
	public void setphone(String phone) {
		this.phone = phone;
	}

	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}

	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}

	public String getnote() {
		return note;
	}
	public void setnote(String note) {
		this.note = note;
	}



	//add contact with invalid/empty info
	public String getemptyfirstName() {
		return emptyfirstName;
	}
	public void setemptyfirstName(String emptyfirstName) {
		this.emptyfirstName = emptyfirstName;
	}

	public String getemptylastName() {
		return emptylastName;
	}
	public void setemptylastName(String emptylastName) {
		this.emptylastName = emptylastName;
	}

	public String getemptyphone() {
		return emptyphone;
	}
	public void setemptyphone(String emptyphone) {
		this.emptyphone = emptyphone;
	}

	public String getemptyemail() {
		return emptyemail;
	}
	public void setemptyemail(String emptyemail) {
		this.emptyemail = emptyemail;
	}

	public String getemptytitle() {
		return emptytitle;
	}
	public void setemptytitle(String emptytitle) {
		this.emptytitle = emptytitle;
	}

	public String getemptynote() {
		return emptynote;
	}
	public void setemptynote(String emptynote) {
		this.emptynote = emptynote;
	}

	public String getinvalidemail() {
		return invalidemail;
	}
	public void setinvalidemail(String invalidemail) {
		this.invalidemail = invalidemail;
	}


	//Edit contact from the list
	public String geteditphone() {
		return editphone;
	}
	public void seteditphone(String editphone) {
		this.editphone = editphone;
	}

	public String getedittitle() {
		return edittitle;
	}
	public void setedittitle(String edittitle) {
		this.edittitle = edittitle;
	}
}
