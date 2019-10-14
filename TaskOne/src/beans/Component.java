
package beans;

import javafx.beans.property.*;

public class Component {
	
	private final SimpleIntegerProperty IDcomponent;
	private final SimpleStringProperty componentName;
	private final SimpleStringProperty componentDescription;
	private final SimpleIntegerProperty componentAvailability;
	
	public Component( int id, String name, String description, int availability ) {
		
		IDcomponent = new SimpleIntegerProperty(id);
		componentName = new SimpleStringProperty(name);
		componentDescription = new SimpleStringProperty(description);
		componentAvailability = new SimpleIntegerProperty(availability);
	}
	
	public int getIDproduct() {
		
		return IDcomponent.get();
	}
	
	public String getProductName() {
		
		return componentName.get();
	}
	
	public String getProductDescription() {
		
		return componentDescription.get();
	}
	
	public int getProductAvailability() {
		
		return componentAvailability.get();
	}
	
	
}

