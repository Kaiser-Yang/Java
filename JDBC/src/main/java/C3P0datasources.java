import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class C3P0datasources {
    private static String url = "jdbc:mysql://localhost:3306/user";
    private static String user = "root";
    private static String password = "123456";
    private static ComboPooledDataSource ds = new ComboPooledDataSource();

    static {
        try {
            ds.setDriverClass("com.mysql.cj.jdbc.Driver");
            ds.setJdbcUrl(url);
            ds.setUser(user);
            ds.setPassword(password);

            ds.setInitialPoolSize(5);
            ds.setMaxPoolSize(20);
            ds.setIdleConnectionTestPeriod(2);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
