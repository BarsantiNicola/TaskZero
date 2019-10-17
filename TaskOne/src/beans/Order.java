
package beans;

import java.sql.*;
import javafx.beans.property.*;

public class Order{

	private final SimpleStringProperty productName;
	private final SimpleIntegerProperty productPrice;
	private final SimpleObjectProperty<Timestamp> purchaseDate;
	private final SimpleIntegerProperty purchasedPrice;
	private final SimpleStringProperty orderStatus;
	
	public Order( String name , int price , Timestamp date, int cost , String status ) {

		productName = new SimpleStringProperty( name );
		productPrice = new SimpleIntegerProperty( price );
		purchaseDate = new SimpleObjectProperty(date);
		purchasedPrice = new SimpleIntegerProperty( cost );
		orderStatus = new SimpleStringProperty( status );

	}
	
	public String getProductName() {

		return productName.get();
	}

	public int getProductPrice() {

		return productPrice.get();
	}
	
	public Timestamp getPurchaseDate() {
		
		return purchaseDate.get();
	}
	
	public int getPurchasedPrice() {
		
		return purchasedPrice.get();
	}
	
	public String getOrderStatus() {
		
		return orderStatus.get();
	}
	
}
