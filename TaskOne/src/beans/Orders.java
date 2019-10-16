
package beans;

import java.sql.*;
import javafx.beans.property.*;

public class Orders {

	private final SimpleStringProperty customer;
	private final SimpleIntegerProperty product;
	private final SimpleObjectProperty<Timestamp> purchaseDate;
	private final SimpleIntegerProperty price;
	private final SimpleStringProperty status;
	
	public Orders( String idcustomer, int idproduct, Timestamp date, int cost , String state ) {
		
		customer = new SimpleStringProperty(idcustomer);
		price = new SimpleIntegerProperty( cost );
		product = new SimpleIntegerProperty(idproduct);
		purchaseDate = new SimpleObjectProperty(date);
		status = new SimpleStringProperty(state);
	}
	
	public String getCustomer() {
		
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
