
package beans;

import javafx.beans.property.*;

public class User {
	
	private final SimpleStringProperty username;
	private final SimpleStringProperty name;
	private final SimpleStringProperty surname;
	private final SimpleStringProperty password;
	private final SimpleStringProperty mail;
	
	public User( String Username, String Name, String Surname, String Password, String Mail ) {
		
		username = new SimpleStringProperty(Username);
		name = new SimpleStringProperty(Name);
		surname = new SimpleStringProperty(Surname);
		password = new SimpleStringProperty(Password);
		mail = new SimpleStringProperty(Mail);

	}
	
	public String getusername() {
		
		return username.get();
	}
	
	public String getname() {
		
		return name.get();
	}

	public String getsurname() {
	
		return surname.get();
	}

	public String getpassword() {
	
		return password.get();
	}
	
	public String getmail() {
		
		return mail.get();
	}
}
