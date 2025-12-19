package restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:FREE"; 
    private static final String USER = "system"; 
    private static final String PASSWORD = "menna123"; 

    /**
     * Method to get a connection to Oracle DB
     * @return Connection object or null if connection fails
     */
    public static Connection connectDb() {
        Connection conn = null;
        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Create connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to Oracle DB successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Utility method to close connection safely
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                System.err.println("Failed to close connection!");
                e.printStackTrace();
            }
        }
    }
}
