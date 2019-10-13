
package beans;

import java.sql.*;
import javafx.beans.property.*;

public class Orders {

	private final SimpleIntegerProperty customer;
	private final SimpleIntegerProperty product;
	private final SimpleObjectProperty<Timestamp> purchaseDate;
	private final SimpleStringProperty status;
	
	public Orders( int idcustomer, int idproduct, Timestamp date, String state ) {
		
		customer = new SimpleIntegerProperty(idcustomer);
		product = new SimpleIntegerProperty(idproduct);
		purchaseDate = new SimpleObjectProperty(date);
		status = new SimpleStringProperty(state);
	}
	
	public int getCustomer() {
		
		return customer.get();
	}
	
	public int getProduct() {
		
		return product.get();
	}
	
	public Timestamp getPurchaseDate() {
		
		return purchaseDate.get();
	}
	
	public String getStatus() {
		
		return status.get();
	}
	
}
