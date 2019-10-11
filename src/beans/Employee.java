
package beans;

import javafx.beans.property.*;

public class Employee {
	
	private final SimpleStringProperty IDemployee;
	private final SimpleIntegerProperty salary;
	private final SimpleStringProperty role;
	private final SimpleIntegerProperty team;
	
	public Employee( String id, int money, String job, int group ) {
		
		IDemployee = new SimpleStringProperty(id);
		salary = new SimpleIntegerProperty(money);
		role = new SimpleStringProperty(job);
		team = new SimpleIntegerProperty(group);

	}
	
	public String getIDemployee() {
		
		return IDemployee.get();
	}
	
	public int getsalary() {
		
		return salary.get();
	}

	public String getrole() {
	
		return role.get();
	}

	public int getteam() {
	
		return team.get();
	}
}
