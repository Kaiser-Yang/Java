import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBCPdatasources {
    private static String url = "jdbc:mysql://localhost:3306/user";
    private static String user = "root";
    private static String password = "123456";
    private static BasicDataSource ds = new BasicDataSource();

    static{
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);

        ds.setInitialSize(5);//初始创建几个connection
        ds.setMaxActive(20);//最大有几个connection
        ds.setMinIdle(2);//最小的空闲connection
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();//不需要归还直接close即可
    }
}
