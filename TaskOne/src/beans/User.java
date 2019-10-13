
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
	
	public String getUsername() {
		
		return username.get();
	}
	
	public String getName() {
		
		return name.get();
	}

	public String getSurname() {
	
		return surname.get();
	}

	public String getPassword() {
	
		return password.get();
	}
	
	public String getMail() {
		
		return mail.get();
	}

	public void setUsername( String u ) {

		username.set(u);
	}

	public void setName( String n ) {

		name.set(n);
	}

	public void setSurname( String s ) {

		surname.set(s);
	}

	public void SetPassword( String p ) {

		password.set(p);
	}

	public void setMail( String m ) {

		mail.set(m);
	}
}
