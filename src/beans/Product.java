
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
	
	public String getproductName() {
		
		return productName.get();
	}
	
	public int getprice() {
		
		return price.get();
	}
	
	public String getproductDescription() {
		
		return productDescription.get();
	}
	
	public boolean getproductAvailability() {
		
		return productAvailability.get();
	}
	
	
}
