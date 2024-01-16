import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;
    private  DbConnection() throws ClassNotFoundException, SQLException {
        // connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Create a Connection
        connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/db_transaction",
                        "root",
                        "Akstack#2003#123@az");
    }
    public static DbConnection getInstance() throws SQLException, ClassNotFoundException {
        if (dbConnection==null){
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
