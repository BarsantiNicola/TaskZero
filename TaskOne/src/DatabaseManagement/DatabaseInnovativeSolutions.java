package DatabaseManagement;

import beans.*;
//import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;


public class DatabaseInnovativeSolutions {


	private static String connectionString;
	private static Connection myConnection;
	
	//these statements are the ones related to the operations
	private static PreparedStatement addUserStatement;
	private static PreparedStatement deleteUserStatement;
	private static PreparedStatement updateSalaryStatement;
	private static PreparedStatement getTeamProductsStatement;
	private static PreparedStatement getTeamEmployeeStatement;
	private static PreparedStatement insertProductStatement;
	private static PreparedStatement getAvailableProductsStatement;
	private static PreparedStatement insertOrderStatement;
	private static PreparedStatement getOrderStatusStatement;
	
	
	private static PreparedStatement loginStatement;
	private static PreparedStatement isEmployeeStatement;
	private static PreparedStatement isCustomerStatement;
	private static PreparedStatement getManagedTeam;
	private static PreparedStatement getUsers;
	private static PreparedStatement searchUsers;
	private static PreparedStatement searchProducts;
	private static PreparedStatement searchOrders;
	private static PreparedStatement searchTeamEmployee;
	private static PreparedStatement insertUser;
	private static PreparedStatement insertEmployee;


	static {

		connectionString = "jdbc:mysql://localhost:3306/exercise1?user=root&password=root&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		myConnection = null;

		try {
			myConnection = DriverManager.getConnection(connectionString);

			System.out.println("Database Connection Established");
			
			//OPERATIONS STATEMENTS
			
			addUserStatement = myConnection.prepareStatement(
					"INSERT INTO user values ( ? , ? , ? , ? )");
					
			deleteUserStatement = myConnection.prepareStatement(
					"START TRANSACTION; DELETE FROM `user`"
							+ " WHERE username = ?;"
							+ " DELETE FROM `Employee`"
							+ " WHERE IDemployee = ?; COMMIT");
			
			updateSalaryStatement = myConnection.prepareStatement(
					"UPDATE employee"
							+ " SET salary=?"
							+ " WHERE IDemployee=?");
			
			getTeamProductsStatement = myConnection.prepareStatement(
					" SELECT P.productType,P.productName,P.productPrice,P.productDescription,P.productAvailability"
				 +  " FROM product P INNER JOIN assembles A ON P.productName = A.product"
				 +  " WHERE team=?");
			
			getTeamEmployeeStatement = myConnection.prepareStatement(
					"SELECT IDemployee,salary,role"
							+ " FROM employee"
							+ " WHERE team=?");
			
			insertProductStatement = myConnection.prepareStatement(
					"INSERT INTO product VALUES (?,?,?,?,0");
			
			getAvailableProductsStatement = myConnection.prepareStatement(
					"SELECT productType , productName, productPrice , productDescription , productAvailability "
							+ " FROM product "
							+ " WHERE productAvailability > 0 ");
			
			insertOrderStatement = myConnection.prepareStatement(
					"INSERT INTO orders VALUES (?,?,?,?,?");
			
			getOrderStatusStatement = myConnection.prepareStatement(
					"SELECT product,purchaseDate,status"
					+ " FROM orders"
					+ " WHERE customer=?");
			
			//OTHER STATEMENTS

			loginStatement = myConnection.prepareStatement(
					"SELECT COUNT(*) AS numberOfUsers"
							+ " FROM user"
							+ " WHERE username=? AND password=?"
			);

			isEmployeeStatement = myConnection.prepareStatement(
					"SELECT COUNT(*) AS isEmployee"
							+ " FROM team"
							+ " WHERE teamLeader=?"
			);

			isCustomerStatement = myConnection.prepareStatement(
					"SELECT COUNT(*) AS isCustomer"
							+ " FROM customer"
							+ " WHERE IDcustomer=?"
			);
			
			getManagedTeam = myConnection.prepareStatement(
					"SELECT IDTeam "
							+ " FROM Team"
							+ " WHERE teamLeader=?"
			);

			getUsers = myConnection.prepareStatement(
					"SELECT username, name, surname, password, mail , salary , role, team"
						+ " FROM user LEFT JOIN employee"
						+ " ON user.username = employee.IDemployee"
			);

			searchUsers = myConnection.prepareStatement(

					"SELECT username, name, surname, password, mail , salary , role, team"
							+ " FROM user LEFT JOIN employee"
							+ " ON user.username = employee.IDemployee"
							+ " WHERE username = ? OR name = ? OR surname = ? OR mail = ?"
							+ " OR role = ?"
			);

			searchProducts = myConnection.prepareStatement(
					"SELECT productType , productName , price , productDescription , productAvailability"
							+ " FROM Product"
							+ " WHERE IDProduct = ? OR productName = ? OR price = ? "

			);

			searchOrders = myConnection.prepareStatement(
					"SELECT productType , productName , price , productDescription , productAvailability"
							+ " FROM Order"
							+ " WHERE customer = ? AND (product = ?  OR status = ?)"

			);

			searchTeamEmployee = myConnection.prepareStatement(
					"SELECT IDemployee,salary,role , admin "
							+ " FROM employee"
							+ " WHERE team=? AND ( salary = ? OR role = ?)");

			insertUser = myConnection.prepareStatement(
					"START TRANSACTION; INSERT INTO `User`( username , name , surname , password , mail ) VALUE ( ? , ? , ? , ? , ? ); INSERT INTO `Employee`( IDemployee , salary , role, team ) VALUE ( ? , ? , ? , ? ); COMMIT;"
			);

			
			System.out.println("Statements Created Correctly");

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
	}
	
	//OPERATIONS
	

	
	public static int deleteUser(String username) {

		int deletedRows = 0;

		try {

			deleteUserStatement.setString(1, username );
			deleteUserStatement.setString( 2, username );

			deletedRows = deleteUserStatement.executeUpdate();

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return deletedRows;
	}
	
	public static void updateSalary(int salary, String employee) {

		try {

			updateSalaryStatement.setInt(1, salary );
			updateSalaryStatement.setString(2, employee);

			 updateSalaryStatement.executeUpdate();

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
	}
	
	public static List<Product> getTeamProducts( int team ){
		
		List<Product> teamProductList = new ArrayList<>();
		
		try {
			getTeamProductsStatement.setInt(1, team);
		
			getTeamProductsStatement.execute();

			ResultSet getTeamProductsResult = getTeamEmployeeStatement.getResultSet();

			while (getTeamProductsResult.next()) {

				teamProductList.add(new Product(getTeamProductsResult.getInt("productType"),
						getTeamProductsResult.getString("productName"),
						getTeamProductsResult.getInt("productPrice"),
						getTeamProductsResult.getString("productDescription"),
						getTeamProductsResult.getInt("productAvailability")
						)
				);
			}
		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
		
		return teamProductList;
	}
	
	public static List<Employee> getTeamEmployees(int team) {

		List<Employee> employeeList = new ArrayList<>();

		try {
			getTeamEmployeeStatement.setInt(1, team);
			System.out.println("employee");
			getTeamEmployeeStatement.execute();

			ResultSet teamEmployeeResult = getTeamEmployeeStatement.getResultSet();

			while (teamEmployeeResult.next()) {

				employeeList.add(new Employee(teamEmployeeResult.getString("IDemployee"),
								teamEmployeeResult.getInt("salary"),
								teamEmployeeResult.getString("role"),
								teamEmployeeResult.getInt("team")
						)
				);
			}
		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return employeeList;
	}
	
	public static int insertProduct(int id, String name, int cost, String description ) {

		int insertedRows = 0;

		try {

			insertProductStatement.setString(1, Integer.toString(id));
			insertProductStatement.setString(2, name);
			insertProductStatement.setString(3, Integer.toString(cost));
			insertProductStatement.setString(4, description);

			insertedRows = insertProductStatement.executeUpdate();

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return insertedRows;
	}
	
	public static List<Product> getAvailableProducts() {

		List<Product> productList = new ArrayList<>();

		try {

			getAvailableProductsStatement.execute();

			ResultSet availableProductsResult = getAvailableProductsStatement.getResultSet();

			while (availableProductsResult.next()) {

				productList.add(new Product(availableProductsResult.getInt("productType"),
								availableProductsResult.getString("productName"),
								availableProductsResult.getInt("productPrice"),
								availableProductsResult.getString("productDescription"),
								availableProductsResult.getInt("productAvailability")
						)
				);
			}
		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return productList;
	}
	
	public static int insertOrder( String customer, int product, int price) {
		
		int insertedRows = 0;

		try {

			insertOrderStatement.setString(1, customer);
			insertOrderStatement.setString(2, Integer.toString(product));
			insertOrderStatement.setString(3, Integer.toString(price));
			insertOrderStatement.setString(4, "IN LAVORAZIONE");

			insertedRows = insertOrderStatement.executeUpdate();

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return insertedRows;
	}
	
	public static List<Orders> getOrderStatus( String IDcustomer ){
		
		List<Orders> ordersList = new ArrayList<>();

		try {

			getOrderStatusStatement.execute();

			ResultSet orderStatusResult = getOrderStatusStatement.getResultSet();

			while ( orderStatusResult.next() ) {

				ordersList.add(new Orders(IDcustomer,
								orderStatusResult.getInt("product"),
								orderStatusResult.getTimestamp("purchaseDate"),
								orderStatusResult.getInt( "price" ),
								orderStatusResult.getString("status")
						)
				);
			}


		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return ordersList;
	}

	//UTILITY FUNCTIONS
	
	public static int getTeam(String name) {

		try {


			getManagedTeam.setString(1, name);
			getManagedTeam.execute();

			ResultSet myTeam = getManagedTeam.getResultSet();
			while (myTeam.next())
				return myTeam.getInt("IDTeam");

		} catch (SQLException caughtException) {

			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());


		}
		return -1;
	}

	public static List<User> getUsers() {

		List<User> list = new ArrayList<>();

		try {

			getUsers.execute();
			ResultSet users = getUsers.getResultSet();

			while (users.next())
				list.add(new User(users.getString("username"), users.getString("name"), users.getString("surname"), users.getString("password"), users.getString("mail") , users.getString( "role") , users.getInt( "salary") , users.getInt( "Team")));

		} catch (SQLException caughtException) {

			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());

		}

		return list;

	}

	public static List<User> searchUsers(String value) {

		List<User> list = new ArrayList<>();

		try {

			searchUsers.setString(1, value);
			searchUsers.setString(2, value);
			searchUsers.setString(3, value);
			searchUsers.setString(4, value);
			searchUsers.setString(5, value);

			searchUsers.execute();
			ResultSet users = searchUsers.getResultSet();

			while (users.next())
				list.add(new User(users.getString("username"), users.getString("name"), users.getString("surname"), users.getString("password"), users.getString("mail") , users.getString( "role") , users.getInt("salary") , users.getInt("Team")));

		} catch (SQLException caughtException) {

			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());

		}

		return list;
	}

	//questa c'� gi� sopra
	public static List<Product> searchProducts(String value) {

		List<Product> list = new ArrayList<>();

		try {

			searchProducts.setInt(1, Integer.parseInt(value));
			searchProducts.setString(2, value);
			searchProducts.setInt(3, Integer.parseInt(value));

			searchProducts.execute();
			ResultSet products = searchProducts.getResultSet();
			while (products.next())
				list.add(new Product(products.getInt("IDProduct"), products.getString("productName"), products.getInt("productPrice"), products.getString("productDescription"), products.getInt("productAvailability")));
		} catch (SQLException caughtException) {

			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());

		}

		return list;

	}

	//questa c'� gi� sopra
	public static List<Orders> searchOrders(String value, String customerID) {

		List<Orders> list = new ArrayList<>();

		try {

			searchOrders.setString(1, customerID);
			searchOrders.setString(2, value);
			searchOrders.setString(3, value);

			searchOrders.execute();
			ResultSet orders = searchOrders.getResultSet();
			while (orders.next())
				list.add(new Orders(orders.getString("customer"), orders.getInt("product"), orders.getTimestamp("purchaseDate"), orders.getInt( "price" ) , orders.getString("status")));

		} catch (SQLException caughtException) {

			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());

		}

		return list;

	}

	//questa c'� gi� sopra
	public static List<Employee> searchTeamEmployees(String value, int team) {

		List<Employee> list = new ArrayList<>();

		try {

			searchTeamEmployee.setInt(1, team);
			searchTeamEmployee.setInt(2, Integer.parseInt(value));
			searchTeamEmployee.setString(3, value);

			searchTeamEmployee.execute();
			ResultSet employees = searchTeamEmployee.getResultSet();
			while (employees.next())
				list.add(new Employee(employees.getString("IDemployee"), employees.getInt("salary"), employees.getString("role"), team));

		} catch (SQLException caughtException) {

			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());

		}

		return list;

	}

	public static UserType login(String user, String psw) {

		try {

			loginStatement.setString(1, user);
			loginStatement.setString(2, psw);

			loginStatement.execute();

			ResultSet loginResult = loginStatement.getResultSet();

			loginResult.next();
			int loginConclusion = loginResult.getInt("numberOfUsers");

			if (loginConclusion == 1) {

				isEmployeeStatement.setString(1, user);
				isEmployeeStatement.execute();

				ResultSet isEmployeeResult = isEmployeeStatement.getResultSet();
				isEmployeeResult.next();
				int isEmployeeConclusion = isEmployeeResult.getInt("isEmployee");

				if (isEmployeeConclusion == 1)
					return UserType.HEAD_DEPARTMENT;

				isCustomerStatement.setString(1, user);
				isCustomerStatement.execute();

				ResultSet isCustomerResult = isCustomerStatement.getResultSet();

				isCustomerResult.next();
				int isCustomerConclusion = isCustomerResult.getInt("isCustomer");

				if (isCustomerConclusion == 1)
					return UserType.CUSTOMER;

				return UserType.ADMINISTRATOR;

			}

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return UserType.NOUSER;
	}


	public static boolean insertUser( User newUser) {

		try {

			insertUser.setString(1, newUser.getUsername());
			insertUser.setString(2, newUser.getName());
			insertUser.setString(3, newUser.getSurname());
			insertUser.setString(4, newUser.getPassword());
			insertUser.setString(5, newUser.getMail());
			insertUser.setString(6, newUser.getUsername());
			insertUser.setInt(7, newUser.getSalary());
			insertUser.setString(8, newUser.getRole());
			insertUser.setInt(9, newUser.getTeam());

			insertUser.execute();
			return true;

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
			return false;
		}
	}
}
//This class manages the connection with the DB. In particular, the connection is established 
//into the static block, and statements are prepared there too. The class offers different 
//functions that execute this statements.