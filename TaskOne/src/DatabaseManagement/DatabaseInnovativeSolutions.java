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
	private static PreparedStatement getManagedTeam;
	private static PreparedStatement getUsers;
	private static PreparedStatement searchUsers;
	private static PreparedStatement searchProducts;
	private static PreparedStatement searchOrders;
	private static PreparedStatement searchTeamEmployee;
	private static PreparedStatement insertUser;


	static {

		connectionString = "jdbc:mysql://localhost:3306/?user=root&password=root&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		myConnection = null;

		try {
			myConnection = DriverManager.getConnection(connectionString);

			System.out.println("Database Connection Established");
			
			getOrderStatusStatement = myConnection.prepareStatement(
					"SELECT product,purchaseDate,status"
					+ " FROM orders"
					+ " WHERE customer=?");

			getAvailableProductsStatement = myConnection.prepareStatement(
					"SELECT IDproduct , productName, productPrice , productDescription , productAvailability "
							+ " FROM product "
							+ " WHERE productAvailability > 0 ");

			updateSalaryStatement = myConnection.prepareStatement(
					"UPDATE employee"
							+ " SET salary=?"
							+ " WHERE id=?");

			updateTeamLeaderStatement = myConnection.prepareStatement(
					"UPDATE team"
							+ " SET IDleader=?"
							+ " WHERE IDteam=?");

			deleteUserStatement = myConnection.prepareStatement(
					"DELETE FROM user"
							+ " WHERE nickname=?");

			getTeamEmployeeStatement = myConnection.prepareStatement(
					"SELECT IDemployee,salary,role"
							+ " FROM employee"
							+ " WHERE team=?");

			insertProductStatement = myConnection.prepareStatement(
					"INSERT INTO product VALUES (?,?,?,?,?");

			updatePriceStatement = myConnection.prepareStatement(
					"UPDATE product"
							+ " SET price=?"
							+ " WHERE IDproduct=?"
			);

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
					"SELECT username , name, surname, password , mail"
							+ " FROM User"
			);

			searchUsers = myConnection.prepareStatement(

					"SELECT username, name, surname , password , mail"
							+ " FROM User"
							+ " WHERE username = ? OR name = ? OR surname = ? OR mail = ?"
			);

			searchProducts = myConnection.prepareStatement(
					"SELECT IDProduct , productName , price , productDescription , productAvailability"
							+ " FROM Product"
							+ " WHERE IDProduct = ? OR productName = ? OR price = ? "

			);

			searchOrders = myConnection.prepareStatement(
					"SELECT IDProduct , productName , price , productDescription , productAvailability"
							+ " FROM Order"
							+ " WHERE customer = ? AND (product = ?  OR status = ?)"

			);

			searchTeamEmployee = myConnection.prepareStatement(
					"SELECT IDemployee,salary,role"
							+ " FROM employee"
							+ " WHERE team=? AND ( salary = ? OR role = ?)");

			insertUser = myConnection.prepareStatement(
					"INSERT INTO User VALUES ( ? , ? , ? , ? , ? )"
			);
			
			System.out.println("Statements Created Correctly");

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}
	}
	
	public static List<Orders> getOrderStatus( int customer ){
		
		List<Orders> ordersList = new ArrayList<>();

		try {

			getOrderStatusStatement.execute();

			ResultSet orderStatusResult = getOrderStatusStatement.getResultSet();

			while (orderStatusResult.next()) {

				ordersList.add(new Orders(customer,
								orderStatusResult.getInt("product"),
								orderStatusResult.getTimestamp("purchaseDate"),
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

	public static List<Product> getAvailableProducts() {

		List<Product> productList = new ArrayList<>();

		try {

			getAvailableProductsStatement.execute();

			ResultSet availableProductsResult = getAvailableProductsStatement.getResultSet();

			while (availableProductsResult.next()) {

				productList.add(new Product(availableProductsResult.getInt("IDproduct"),
								availableProductsResult.getString("productName"),
								availableProductsResult.getInt("price"),
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

	public static int updateSalary(int salary, int employee) {

		int updatedRows = 0;

		try {

			updateSalaryStatement.setString(1, Integer.toString(salary));
			updateSalaryStatement.setString(2, Integer.toString(employee));

			updatedRows = updateSalaryStatement.executeUpdate();

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return updatedRows;
	}

	public static int updateTeamLeader(int leader, int team) {

		int updatedRows = 0;

		try {

			updateTeamLeaderStatement.setString(1, Integer.toString(leader));
			updateTeamLeaderStatement.setString(2, Integer.toString(team));

			updatedRows = updateTeamLeaderStatement.executeUpdate();

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return updatedRows;
	}

	public static int deleteUser(String username) {

		int deletedRows = 0;

		try {

			deleteUserStatement.setString(1, username);

			deletedRows = deleteUserStatement.executeUpdate();

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return deletedRows;
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

	public static int insertProduct(int id, String name, int cost, String description, int availability) {

		int insertedRows = 0;

		try {

			insertProductStatement.setString(1, Integer.toString(id));
			insertProductStatement.setString(2, name);
			insertProductStatement.setString(3, Integer.toString(cost));
			insertProductStatement.setString(4, description);
			insertProductStatement.setInt(5, availability);

			insertedRows = insertProductStatement.executeUpdate();

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return insertedRows;
	}

	public static int updatePrice(int cost, int product) {

		int updatedRows = 0;

		try {

			updatePriceStatement.setString(1, Integer.toString(cost));
			updatePriceStatement.setString(2, Integer.toString(product));

			updatedRows = updatePriceStatement.executeUpdate();

		} catch (SQLException caughtException) {
			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());
		}

		return updatedRows;
	}

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
				list.add(new User(users.getString("username"), users.getString("name"), users.getString("surname"), users.getString("password"), users.getString("mail")));

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

			searchUsers.execute();
			ResultSet users = searchUsers.getResultSet();

			while (users.next())
				list.add(new User(users.getString("username"), users.getString("name"), users.getString("surname"), users.getString("password"), users.getString("mail")));

		} catch (SQLException caughtException) {

			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());

		}

		return list;
	}

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

	public static List<Orders> searchOrders(String value, String customerID) {

		List<Orders> list = new ArrayList<>();

		try {

			searchOrders.setString(1, customerID);
			searchOrders.setString(2, value);
			searchOrders.setString(3, value);

			searchOrders.execute();
			ResultSet orders = searchOrders.getResultSet();
			while (orders.next())
				list.add(new Orders(orders.getInt("customer"), orders.getInt("product"), orders.getTimestamp("purchaseDate"), orders.getString("status")));

		} catch (SQLException caughtException) {

			System.out.println("SQLException: " + caughtException.getMessage());
			System.out.println("SQLState: " + caughtException.getSQLState());
			System.out.println("VendorError: " + caughtException.getErrorCode());

		}

		return list;

	}

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


	public static boolean insertUser(HashMap<String, String> values) {

		try {

			insertUser.setString(1, values.get("Username"));
			insertUser.setString(2, values.get("Name"));
			insertUser.setString(3, values.get("Surname"));
			insertUser.setString(4, values.get("Password"));
			insertUser.setString(5, values.get("Mail"));

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