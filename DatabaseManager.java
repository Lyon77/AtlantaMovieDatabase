import java.sql.*;
import java.util.*;

public class DatabaseManager {
	private static final DatabaseManager instance = new DatabaseManager();
	public static DatabaseManager getInstance() { return instance; }
	
	private Connection con;
	
	private DatabaseManager() {
		try {
             Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection(
			 "jdbc:mysql://localhost:3306/team59", "root", "password");
        } catch (Exception e) {
            System.err.println("D'oh! Got an exception!");
            System.err.println(e.getMessage());

            return;
        }
		System.out.println("Connected to database.");
	}
	
	public ResultSet screen1Login(String username, String password) throws Exception {
		try {
			Statement stmt = con.createStatement();
			stmt.execute("CALL user_login('" + username + "', '" + password + "');");
			ResultSet rs = stmt.executeQuery ("SELECT * FROM team59.userlogin;");
			stmt.closeOnCompletion();
			return rs;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void screen3UserOnlyRegister(String firstName, String lastName, String username, String password) throws Exception {
		try {
			Statement stmt = con.createStatement();
			stmt.execute("CALL user_register('" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "');");
			stmt.close();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void screen4CustomerOnlyRegister(String firstName, String lastName, String username, String password) throws Exception {
		try {
			Statement stmt = con.createStatement();
			stmt.execute("CALL customer_only_register('" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "');");
			stmt.close();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void customerAddCreditCard(String username, String creditCardNum) throws Exception {
		try {
			Statement stmt = con.createStatement();
			stmt.execute("CALL customer_add_creditcard('" + username + "', '" + creditCardNum + "');");
			stmt.close();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void screen5ManagerOnlyRegister(String firstName, String lastName, String username, String password, String company, String street, String city, String state, String zipcode) throws Exception {
		try {
			Statement stmt = con.createStatement();
			stmt.execute("CALL manager_only_register('" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "', '" + company + "', '" + street + "', '" + city + "', '" + state + "', '" + zipcode + "');");
			stmt.close();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<String> getCompanyList() throws Exception {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery ("SELECT * FROM team59.company;");
			List<String> companyList = new ArrayList<String>();
			while (rs.next()) {
				companyList.add(rs.getString(1));
			}
			rs.close();
			stmt.close();
			return companyList;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void screen6ManagerCustomerRegister(String firstName, String lastName, String username, String password, String company, String street, String city, String state, String zipcode) throws Exception {
		try {
			Statement stmt = con.createStatement();
			stmt.execute("CALL manager_customer_register('" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "', '" + company + "', '" + street + "', '" + city + "', '" + state + "', '" + zipcode + "');");
			stmt.close();
		} catch (Exception e) {
			throw e;
		}
	}
}