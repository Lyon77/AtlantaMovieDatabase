import java.sql.*;

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
			return rs;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void screen3UserOnlyRegister(String firstName, String lastName, String username, String password) throws Exception {
		try {
			Statement stmt = con.createStatement();
			stmt.execute("CALL user_register('" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "');");
		} catch (Exception e) {
			throw e;
		}
	}
}