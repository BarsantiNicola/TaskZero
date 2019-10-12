
package beans;

import javafx.beans.property.*;

public class Component {
	
	private final SimpleIntegerProperty IDcomponent;
	private final SimpleStringProperty componentName;
	private final SimpleStringProperty componentDescription;
	private final SimpleBooleanProperty componentAvailability;
	
	public Component( int id, String name, String description, boolean availability ) {
		
		IDcomponent = new SimpleIntegerProperty(id);
		componentName = new SimpleStringProperty(name);
		componentDescription = new SimpleStringProperty(description);
		componentAvailability = new SimpleBooleanProperty(availability);
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
	
	public boolean getProductAvailability() {
		
		return componentAvailability.get();
	}
	
	
}

