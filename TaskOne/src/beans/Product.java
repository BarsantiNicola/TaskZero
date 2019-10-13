
package beans;

import javafx.beans.property.*;

public class Product {
	
	private final SimpleIntegerProperty IDproduct;
	private final SimpleStringProperty productName;
	private final SimpleIntegerProperty price;
	private final SimpleStringProperty productDescription;
	private final SimpleBooleanProperty productAvailability;
	
	public Product( int id, String name, int cost, String description, boolean availability ) {
		
		IDproduct = new SimpleIntegerProperty(id);
		productName = new SimpleStringProperty(name);
		price = new SimpleIntegerProperty(cost);
		productDescription = new SimpleStringProperty(description);
		productAvailability = new SimpleBooleanProperty(availability);
	}
	
	public int getIDproduct() {
		
		return IDproduct.get();
	}
	
	public String getProductName() {
		
		return productName.get();
	}
	
	public int getPrice() {
		
		return price.get();
	}
	
	public String getProductDescription() {
		
		return productDescription.get();
	}
	
	public boolean getProductAvailability() {
		
		return productAvailability.get();
	}
	
	
}
