//  Establish a connection to a mSQL database using JDBC
import java.sql.*;

class Application {
    public static void main (String[] args) {
        try {
            // REMEMBER to fill out the Info file and bring it outisde the template folder

            // Step 1: "Load" the JDBC driver
             Class.forName(Info.JDBCDriver);

            // Step 2: Establish the connection to the database
            String url = Info.url;
            Connection conn = DriverManager.getConnection(url, Info.username, Info.password);

        } catch (Exception e) {
            System.err.println("D'oh! Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}