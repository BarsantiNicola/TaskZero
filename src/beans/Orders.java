
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
	
	public int getcustomer() {
		
		return customer.get();
	}
	
	public int getproduct() {
		
		return product.get();
	}
	
	public Timestamp getpurchaseDate() {
		
		return purchaseDate.get();
	}
	
	public String getstatus() {
		
		return status.get();
	}
	
}
