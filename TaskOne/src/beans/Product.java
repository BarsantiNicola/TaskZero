
package beans;

import javafx.beans.property.*;

public class Product {
	
	private final SimpleIntegerProperty productType;
	private final SimpleStringProperty productName;
	private final SimpleIntegerProperty productPrice;
	private final SimpleStringProperty productDescription;
	private final SimpleIntegerProperty productAvailability;
	
	public Product( int id, String name, int cost, String description, int availability ) {
		
		productType = new SimpleIntegerProperty(id);
		productName = new SimpleStringProperty(name);
		productPrice = new SimpleIntegerProperty(cost);
		productDescription = new SimpleStringProperty(description);
		productAvailability = new SimpleIntegerProperty(availability);
	}
	
	public int getProductType() {
		
		return productType.get();
	}
	
	public String getProductName() {
		
		return productName.get();
	}
	
	public int getProductPrice() {
		
		return productPrice.get();
	}
	
	public String getProductDescription() {
		
		return productDescription.get();
	}
	
	public int getProductAvailability() {
		
		return productAvailability.get();
	}
	
	
}
