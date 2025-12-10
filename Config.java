import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
    public Connection connect() {

        Connection conn = null;
        try {
            // inisialisasi jdbc
            Class.forName("org.sqlite.JDBC");

            // config file jdbc
            String url = "jdbc:sqlite:C:\\Users\\ADVAN\\Documents\\data\\tugas\\jaav-jdbc\\db.db";

            // get connection to db
            conn = DriverManager.getConnection(url);

            // warning if connect to db success
            System.out.println("Connection to SQLite has been established.");
            
            return conn;
        } catch (SQLException e) {
            // error dari sql
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found: " + e.getMessage());
        }
        return conn;
    }

   

}

public class Config extends DatabaseConnection {
    public String main() {
        DatabaseConnection dbConnect = new DatabaseConnection();
        dbConnect.connect();
        return "Config Is Here!!!";
    }
}


 // public void execute(Connection conn) {
    // try {
    // if (conn != null) {
    // Statement statement = conn.createStatement();
    // String query = "select * from test";
    // ResultSet resultSet = statement.executeQuery(query);
    // while (resultSet.next()) {
    // int id = resultSet.getInt("id");
    // System.out.println("ID: " + id);
    // }
    // } else {
    // System.out.println("Not Connected...");
    // }
    // } catch (Exception e) {
    // System.out.println("Exception is " + e.getMessage());
    // }
    // }