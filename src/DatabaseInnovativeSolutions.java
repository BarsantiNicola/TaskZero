
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Task0Class {

	private static String connectionString;
	private static Connection myConnection;
	private static PreparedStatement getOrderStatusStatement;
	private static PreparedStatement getAvailableProductsStatement;
	private static PreparedStatement updateSalaryStatement;
	private static PreparedStatement updateTeamLeaderStatement;
	private static PreparedStatement deleteUserStatement;
	private static PreparedStatement getTeamEmployeeStatement;
	private static PreparedStatement insertProductStatement;
	private static PreparedStatement updatePriceStatement;
	private static PreparedStatement loginStatement;
	private static PreparedStatement isEmployeeStatement;
	private static PreparedStatement isCustomerStatement;
	
	static {
		
		connectionString = "jdbc:mysql://localhost:3306/exercise1?user=root&password=root&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		myConnection = null;
		
		try {
			myConnection = DriverManager.getConnection(connectionString);
			
			System.out.println("Database Connection Established");
			
			getOrderStatusStatement = myConnection.prepareStatement(
					"SELECT product,purchaseDate,status "
				  + "FROM orders"
				  + "WHERE id=?");
			
		    getAvailableProductsStatement = myConnection.prepareStatement(
		    	    "SELECT productName,price, "
		    	  + "FROM product "
		    	  + "WHERE productAvailability=1");
		    
		    updateSalaryStatement = myConnection.prepareStatement(
		    		"UPDATE employee"
		    	  + "SET salary=?"
		    	  + "WHERE id=?");
		    
		    updateTeamLeaderStatement = myConnection.prepareStatement(
		    		"UPDATE team"
		    	  + "SET IDleader=?"
		    	  + "WHERE IDteam=?");
		    
		    deleteUserStatement = myConnection.prepareStatement(
		    		"DELETE FROM user"
		    	  + "WHERE nickname=?");
		    
		    getTeamEmployeeStatement = myConnection.prepareStatement(
		    		"SELECT IDemployee,salary,role"
		    	  + "FROM employee"
		    	  + "WHERE team=?");
		    
		    insertProductStatement = myConnection.prepareStatement(
		    		"INSERT INTO product VALUES (?,?,?,?,1");
		    
		    updatePriceStatement = myConnection.prepareStatement(
		    		"UPDATE product"
		    	  + "SET price=?"
		    	  + "WHERE IDproduct=?"
		    		);
		    
		    loginStatement = myConnection.prepareStatement(
		    		"SELECT COUNT(*) AS numberOfUsers"
		    	  + "FROM user"
		    	  + "WHERE username=? AND password=?"
		    		);
		    
		    isEmployeeStatement = myConnection.prepareStatement(
		    		"SELECT COUNT(*) AS isEmployee"
		    		+ "FROM team"
		    		+ "WHERE teamLeader=?"
		    		);
		    
		    isCustomerStatement = myConnection.prepareStatement(
		    		"SELECT COUNT(*) AS isCustomer"
		    	  + "FROM customer"
		    	  + "WHERE IDcustomer=?"
		    		);
		    		
		    System.out.println("Statements Created Correctly");
			
		} catch (SQLException caughtException){
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
	}
	
	public static List<Orders> getOrderStatus( int idCustomer ) {
		
		List<Orders> ordersList = new ArrayList<>();
		
		try {
			
			getOrderStatusStatement.setString(1, Integer.toString(idCustomer) );
			getOrderStatusStatement.execute();
			
			ResultSet orderStatusResult = getOrderStatusStatement.getResultSet();
			
			while( orderStatusResult.next() ) {
				
				ordersList.add( new Orders( orderStatusResult.getInt("customer"),
						                    orderStatusResult.getInt("product"),
						                    orderStatusResult.getTimestamp("purchaseDate"),
						                    orderStatusResult.getString("status")
						                   )
						      );
			}
			
		} catch (SQLException caughtException){
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
		
		return ordersList;
	}
	
	static List<Product> getAvailableProducts() {
		
		List<Product> productList = new ArrayList<>();
		
		try {
			
			getAvailableProductsStatement.execute();
			
			ResultSet availableProductsResult = getAvailableProductsStatement.getResultSet();
			
			while( availableProductsResult.next() ) {
				
				productList.add( new Product( availableProductsResult.getInt("IDproduct"),
											  availableProductsResult.getString("productName"),
											  availableProductsResult.getInt("price"),
											  availableProductsResult.getString("productDescription"),
											  availableProductsResult.getBoolean("productAvailability")
	                                        )
	                           );
			}
			
			
		} catch (SQLException caughtException){
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
		
		return productList;
	}
	
	static int updateSalary( int salary, int employee ) {
		
		int updatedRows=0;
		
		try {
			
			updateSalaryStatement.setString( 1, Integer.toString(salary) );
			updateSalaryStatement.setString( 2, Integer.toString(employee) );
			
			updatedRows = updateSalaryStatement.executeUpdate();
			
		} catch (SQLException caughtException){
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
		
		return updatedRows;
	}
	
	static int updateTeamLeader( int leader, int team ) {
		
		int updatedRows=0;
		
		try {
			
			updateTeamLeaderStatement.setString( 1, Integer.toString(leader) );
			updateTeamLeaderStatement.setString( 2, Integer.toString(team) );
			
			updatedRows = updateTeamLeaderStatement.executeUpdate();
			
		} catch (SQLException caughtException){
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
		
		return updatedRows;
	}
	
	static int deleteUser( String username ) {
		
		int deletedRows=0;
		
		try {
			
			deleteUserStatement.setString( 1, username );
			
			deletedRows = deleteUserStatement.executeUpdate();
			
		} catch (SQLException caughtException){
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
		
		return deletedRows;
	}
	
	static List<Employee> getTeamEmployees( int team ){
		
		List<Employee> employeeList = new ArrayList<>();
		
		try {
			
			getTeamEmployeeStatement.execute();
			
			ResultSet teamEmployeeResult = getTeamEmployeeStatement.getResultSet();
			
			while( teamEmployeeResult.next() ) {
				
				employeeList.add( new Employee( teamEmployeeResult.getString("IDemployee"),
												teamEmployeeResult.getInt("salary"),
												teamEmployeeResult.getString("role"),
												teamEmployeeResult.getInt("team")
	                                        )
	                           );
			}
		} catch (SQLException caughtException){
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
		
		return employeeList;
	}
	
	public static int insertProduct( int id, String name, int cost, String description ) {
		
		int insertedRows=0;
		
		try {
			
			insertProductStatement.setString( 1, Integer.toString(id) );
			insertProductStatement.setString( 2, name );
			insertProductStatement.setString( 3, Integer.toString(cost) );
			insertProductStatement.setString( 4, description );
			
			insertedRows = insertProductStatement.executeUpdate();
			
		} catch (SQLException caughtException){
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
		
		return insertedRows;
	}
	
	public static int updatePrice( int cost, int product ) {
		
		int updatedRows=0;
		
		try {
			
			updatePriceStatement.setString( 1, Integer.toString( cost ) );
			updatePriceStatement.setString( 2, Integer.toString( product ) );
			
			updatedRows = updatePriceStatement.executeUpdate();
			
		} catch (SQLException caughtException){
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
		
		return updatedRows;
	}
	
	public static UserType login( String user , String psw ) {
		
		try {
			
			loginStatement.setString( 1, user );
			loginStatement.setString( 2, psw );
			
			loginStatement.execute();
			
			ResultSet loginResult = loginStatement.getResultSet();
			
			int loginConclusion = loginResult.getInt("numberOfUsers");
			
			if( loginConclusion == 1 ) {
				
				isEmployeeStatement.setString(1,user);
				isEmployeeStatement.execute();
				
				ResultSet isEmployeeResult = isEmployeeStatement.getResultSet();
				
				int isEmployeeConclusion = isEmployeeResult.getInt("isEmployee");
				
				if( isEmployeeConclusion == 1 )
					return UserType.HEAD_DEPARTEMENT;
							
				isCustomerStatement.setString(1,user);
				isCustomerStatement.execute();
				
				ResultSet isCustomerResult = isCustomerStatement.getResultSet();
				
				int isCustomerConclusion = isCustomerResult.getInt("isCustomer");
				
				if( isCustomerConclusion == 1 )
					return UserType.CUSTOMER;
				
				return UserType.ADMINISTRATOR;
				
			}
			
		} catch (SQLException caughtException){
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
		
		return UserType.NOUSER;
	}
}

//This class manages the connection with the DB. In particular, the connection is established 
//into the static block, and statements are prepared there too. The class offers different 
//functions that execute this statements.