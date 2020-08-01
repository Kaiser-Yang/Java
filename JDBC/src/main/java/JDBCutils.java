import java.sql.*;
import java.util.ArrayList;

public abstract class JDBCutils {

    public static Connection getConnection(String database, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
    }
    public static void close(ResultSet res, Statement statement, Connection connection) throws SQLException {
        if (res != null) res.close();
        if(statement != null) statement.close();
        if (connection != null) connection.close();
    }
}
