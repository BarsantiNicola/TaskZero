package DatabaseManagement;

import beans.*;
import java.sql.*;
import java.util.*;


public class DatabaseInnovativeSolutions {


	private static String connectionString;
	private static Connection myConnection;
	
	//these statements are the ones related to the operations
	private static PreparedStatement addUserStatement;
	private static PreparedStatement deleteUser;
	private static PreparedStatement deleteEmployee;
	private static PreparedStatement updateSalaryStatement;
	private static PreparedStatement getTeamProductsStatement;
	private static PreparedStatement getTeamEmployeeStatement;
	private static PreparedStatement insertProductStatement;
	private static PreparedStatement getAvailableProductsStatement;
	private static PreparedStatement insertOrderStatement;
	
	
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
	private static PreparedStatement startTransaction;
	private static PreparedStatement commit;
	private static PreparedStatement rollback;
	private static PreparedStatement isTeamLeader;
	private static PreparedStatement getOrders;
	private static PreparedStatement updateProductAvailability;


	static {

		connectionString = "jdbc:mysql://localhost:3306/exercise1?user=root&password=root&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		myConnection = null;

		try {
			myConnection = DriverManager.getConnection(connectionString);

			System.out.println("Database Connection Established");
			
			//OPERATIONS STATEMENTS
			
			addUserStatement = myConnection.prepareStatement(
					"INSERT INTO user values ( ? , ? , ? , ? )");
					
			deleteUser = myConnection.prepareStatement(
					"DELETE FROM `user` WHERE username = ?;"
			);

			deleteEmployee = myConnection.prepareStatement(
					"DELETE FROM `Employee` WHERE IDemployee = ?;");
			
			updateSalaryStatement = myConnection.prepareStatement(
					"UPDATE employee"
							+ " SET salary=?"
							+ " WHERE IDemployee=?");
			
			getTeamProductsStatement = myConnection.prepareStatement(
					"SELECT P.productType,P.productName,P.productPrice,P.productDescription,P.productAvailability "
						+ " FROM product P INNER JOIN assembles A ON P.productType = A.product WHERE team = ?;");
			
			getTeamEmployeeStatement = myConnection.prepareStatement(
					"select IDemployee , name , surname , mail , role" +
							" from user" +
							" join employee" +
							" on user.username = IDemployee\n" +
							" where team = ?;");
			
			insertProductStatement = myConnection.prepareStatement(
					"INSERT INTO product VALUES (?,?,?,?,?);"
			);
			
			getAvailableProductsStatement = myConnection.prepareStatement(
					"SELECT productType , productName, productPrice , productDescription , productAvailability "
							+ " FROM product "
							+ " WHERE productAvailability > 0 ");
			
			insertOrderStatement = myConnection.prepareStatement(
					"INSERT INTO orders VALUES (?,?,?,?,?)");
			
			getOrders = myConnection.prepareStatement(
					"SELECT productName , productPrice ,purchaseDate, price , status"
					+ " FROM orders JOIN product"
					+ " ON  orders.product = product.productType"
					+ " WHERE customer=?");
			
			//OTHER STATEMENTS

			loginStatement = myConnection.prepareStatement(
					"SELECT COUNT(*) AS numberOfUsers"
							+ " FROM user"
							+ " WHERE username=? AND password=?"
			);

			isEmployeeStatement = myConnection.prepareStatement(
					"SELECT COUNT(*) AS isEmployee"
							+ " FROM Employee"
							+ " WHERE IDemployee=?"
			);

			isTeamLeader = myConnection.prepareStatement(

					"SELECT COUNT(*) AS isTeamLeader"
							+ " FROM Team"
							+ " WHERE TeamLeader=?"
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
					"SELECT productType , productName , productPrice , productDescription , productAvailability"
							+ " FROM Product"
							+ " WHERE productName = ?;"

			);

			searchOrders = myConnection.prepareStatement(
					"SELECT productType , productName , price , productDescription , productAvailability"
							+ " FROM Order"
							+ " WHERE customer = ? AND (product = ?  OR status = ?)"

			);

			searchTeamEmployee = myConnection.prepareStatement(
					"select IDemployee , name , surname , mail , role" +
							" from user" +
							" join employee" +
							" on user.username = employee.IDemployee" +
							" where team = ?" +
							" AND ( IDemployee = ? OR name = ? OR surname = ? OR mail = ? OR role = ?); ");

			insertUser = myConnection.prepareStatement(
					"INSERT INTO `User`( username , name , surname , password , mail ) VALUE ( ? , ? , ? , ? , ? );"
			);

			insertEmployee = myConnection.prepareStatement(
					"INSERT INTO `Employee`( IDemployee , salary , role, team ) VALUE ( ? , ? , ? , ? );"
			);

			startTransaction = myConnection.prepareStatement(
					"START TRANSACTION;"
			);

			commit = myConnection.prepareStatement(
					"START TRANSACTION;"
			);

			rollback = myConnection.prepareStatement(
					"ROLLBACK TRANSACTION;"
			);

			updateProductAvailability = myConnection.prepareStatement(
						"UPDATE product " +
								" SET productAvailability = ?" +
								" WHERE productType = ?;"
			);

			
			System.out.println("Statements Created Correctly");

		} catch (SQLException caughtException) {

			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());

		}
	}
	
	//OPERATIONS
	

	
	public static boolean deleteUser( String username ) {


		try {

			startTransaction.execute();
			deleteUser.setString(1, username );
			deleteUser.execute();
			if( DatabaseInnovativeSolutions.isEmployee( username ))
				deleteEmployee.setString( 1, username );
			commit.execute();
			return true;

		} catch (SQLException caughtException) {
			try{
				rollback.execute();
				System.out.println("Rollback executed");
			}catch( SQLException e ){
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			}
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return false;
	}

	public static boolean isEmployee( String username ){
		ResultSet result;
		try {
			isEmployeeStatement.setString(1, username);
			isEmployeeStatement.execute();
			result = isEmployeeStatement.getResultSet();
			result.next();
			return result.getInt("isEmployee") > 0;
		}catch( SQLException e ){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			return false;
		}

	}

	public static boolean updateSalary(int salary, String employee) {

		try {

			updateSalaryStatement.setInt(1, salary );
			updateSalaryStatement.setString(2, employee);

			 updateSalaryStatement.executeUpdate();
			 return true;

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
			return false;
		}
	}
	
	public static List<Product> getTeamProducts( int team ){
		
		List<Product> teamProductList = new ArrayList<>();
		
		try {
			getTeamProductsStatement.setInt(1 , team );
		
			getTeamProductsStatement.execute();

			ResultSet getTeamProductsResult = getTeamProductsStatement.getResultSet();

			while(getTeamProductsResult.next()) {

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
			getTeamEmployeeStatement.execute();

			ResultSet teamEmployeeResult = getTeamEmployeeStatement.getResultSet();

			while (teamEmployeeResult.next()) {

				employeeList.add(new Employee(teamEmployeeResult.getString("IDemployee"),
								teamEmployeeResult.getString("name"),
								teamEmployeeResult.getString("surname"),
								teamEmployeeResult.getString("mail"),
								teamEmployeeResult.getString("role")));
			}
		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return employeeList;
	}
	
	public static int insertProduct(int id, String name, int cost, String description , int availability ) {

		int insertedRows = 0;

		try {

			insertProductStatement.setInt(1, id);
			insertProductStatement.setString(2, name);
			insertProductStatement.setInt(3, cost);
			insertProductStatement.setString(4, description );
			insertProductStatement.setInt( 5 , availability );

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
			insertOrderStatement.setObject( 3 , new Timestamp(System.currentTimeMillis()));
			insertOrderStatement.setString(4, Integer.toString(price));
			insertOrderStatement.setString(5, "received");

			insertedRows = insertOrderStatement.executeUpdate();

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return insertedRows;
	}
	
	public static List<Order> getOrder( String IDcustomer ){
		
		List<Order> ordersList = new ArrayList<>();

		try {

			getOrders.setString( 1 , IDcustomer );
			getOrders.execute();

			ResultSet orderStatusResult = getOrders.getResultSet();

			while ( orderStatusResult.next() ) {

				ordersList.add(new Order(orderStatusResult.getString( "productName"),
								orderStatusResult.getInt("productPrice"),
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

			searchProducts.setString(1, value);


			searchProducts.execute();
			ResultSet products = searchProducts.getResultSet();

			while (products.next())
				list.add(new Product(products.getInt("productType"), products.getString("productName"), products.getInt("productPrice"), products.getString("productDescription"), products.getInt("productAvailability")));

		} catch (SQLException caughtException) {

			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());

		}

		return list;

	}

	//questa c'� gi� sopra
	public static List<Order> searchOrders(String value, String customerID) {

		List<Order> list = new ArrayList<>();

		try {

			searchOrders.setString(1, customerID);
			searchOrders.setString(2, value);
			searchOrders.setString(3, value);

			searchOrders.execute();
			ResultSet orders = searchOrders.getResultSet();
			while (orders.next())
				list.add(new Order(orders.getString("customer"), orders.getInt("product"), orders.getTimestamp("purchaseDate"), orders.getInt( "price" ) , orders.getString("status")));

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
			searchTeamEmployee.setString(2, value );
			searchTeamEmployee.setString(3, value);
			searchTeamEmployee.setString(4, value);
			searchTeamEmployee.setString(5, value);
			searchTeamEmployee.setString(6, value);

			searchTeamEmployee.execute();
			ResultSet employees = searchTeamEmployee.getResultSet();
			while (employees.next())
				list.add(new Employee( employees.getString("IDemployee"),
						employees.getString("name"),
						employees.getString("surname"),
						employees.getString("mail"),
						employees.getString("role")));

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

				isEmployeeStatement.setString(1, user );
				isEmployeeStatement.execute();

				ResultSet isEmployeeResult = isEmployeeStatement.getResultSet();
				isEmployeeResult.next();

				if ( isTeamLeader( user) )
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


	public static boolean isTeamLeader( String user ){
		ResultSet result;
		try {
			isTeamLeader.setString(1, user);
			isTeamLeader.execute();
			result = isTeamLeader.getResultSet();
			result.next();
			return result.getInt("isTeamLeader") > 0;
		}catch( SQLException e ){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			return false;
		}

	}

	public static boolean updateProductAvailability( int product , int value ){

		try {
			updateProductAvailability.setInt(1, value);
			updateProductAvailability.setInt(2, product );
			updateProductAvailability.execute();
			return true;
		}catch( SQLException e ){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			return false;
		}

	}

	public static boolean insertUser( User newUser) {

		try {

			startTransaction.execute();

			insertUser.setString(1, newUser.getUsername());
			insertUser.setString(2, newUser.getName());
			insertUser.setString(3, newUser.getSurname());
			insertUser.setString(4, newUser.getPassword());
			insertUser.setString(5, newUser.getMail());
			insertUser.execute();

			if( newUser.getRole()!= null && newUser.getRole().length() > 0 ) {

				if( newUser.getSalary() <= 0 || newUser.getTeam() <= 0 ){
					rollback.execute();
					return false;
				}

				insertEmployee.setString(1, newUser.getUsername());
				insertEmployee.setInt(2, newUser.getSalary());
				insertEmployee.setString(3, newUser.getRole());
				insertEmployee.setInt(4, newUser.getTeam());
				insertEmployee.execute();
			}

			commit.execute();
			return true;

		} catch (SQLException caughtException) {

			try {
				rollback.execute();
				System.out.println("Rollback executed");

			}catch( SQLException e ){

				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());

			}

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