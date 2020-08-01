import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) {
        try{
//            selectAll();
//            checkLogin("Kaiser", "123456' or '1' = '1");
//            checkLogin2("Kaiser", "123456' or '1' = '1");
//            insertRandomUser();
//            selectUserByPage(100, 10);
//            delete("user", "t_user", 1002);
//            updatePassword(1, "624626089");
//            transferAccount("Kaiser", "Yang", 500);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean transferAccount(String name1, String name2, int money) throws ClassNotFoundException, SQLException{
        if (name1.equals(name2)){
            System.out.println("You are trying transfer your money to yourself");
            return false;
        }
        Connection connection = JDBCutils.getConnection("user", "root", "123456");
        String sql = "select * from t_user where user = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name2);
        ResultSet res = statement.executeQuery();
        if (!res.first()){
            JDBCutils.close(res, statement, connection);
            System.out.println("Wrong name, please check");
            return false;
        }

        sql = "select balance from t_user where user = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, name1);
        res = statement.executeQuery();

        if (!res.first()){
            JDBCutils.close(res, statement, connection);
            System.out.println("Wrong name, please check");
            return false;
        }
        if (res.getInt(1) < money){
            JDBCutils.close(res, statement, connection);
            System.out.println("Sorry, your credit is running low");
            return false;
        }

        connection.setAutoCommit(false);
        sql = "update t_user set balance = balance - ? where user = ?";
        statement =  connection.prepareStatement(sql);
        statement.setInt(1, money);
        statement.setString(2, name1);
        statement.executeUpdate();
        sql = "update t_user set balance = balance + ? where user = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, money);
        statement.setString(2, name2);
        statement.executeUpdate();
        connection.commit();


        JDBCutils.close(res, statement, connection);
        return true;
    }
    public static boolean updatePassword(int id, String newPassword) throws ClassNotFoundException, SQLException{
        //查询第p页， 每页有len个数据
        Connection connection = JDBCutils.getConnection("user", "root", "123456");
        String sql = "update t_user set password = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newPassword);
        statement.setInt(2, id);
        int rows = statement.executeUpdate();
        if (rows == 1){
            System.out.println("succeed to update password");
            JDBCutils.close(null, statement, connection);
            return true;
        }
        System.out.println("fail to update password");
        JDBCutils.close(null, statement, connection);
        return false;
    }
    public static boolean delete(String database, String tableName, int id) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCutils.getConnection(database, "root", "123456");
        String sql = "delete from " + tableName + " where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int rows = statement.executeUpdate();
        if (rows > 0){
            System.out.println("succeed to delete " + rows + " rows");
            JDBCutils.close(null, statement, connection);
            return true;
        }
        System.out.println("fail to delete");
        JDBCutils.close(null, statement, connection);
        return false;
    }
    public static void selectUserByPage(int p, int len) throws ClassNotFoundException, SQLException{
        int start = (p - 1) * len;
        Connection connection = JDBCutils.getConnection("user", "root", "123456");
        String sql = "select * from t_user limit ?, ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, start);
        statement.setInt(2, len);
        ResultSet res = statement.executeQuery();
        while(res.next()){
            System.out.printf("[id: %d user_name: %s password: %s]\n", res.getInt(1), res.getString(2), res.getString(3));
        }
        JDBCutils.close(res, statement, connection);
    }
    public static void insertRandomUser()throws ClassNotFoundException, SQLException{
        Connection connection = JDBCutils.getConnection("user", "root", "123456");
        PreparedStatement statement;
        for (int i = 1;i <= 1000;i ++){
            String user, password;
            user = Integer.toString((int)(Math.random() * (8e8) + 100000000));
            password = Integer.toString((int)(Math.random() * (8e5) + 100000));
            String sql = "insert into t_user(user, password, id) value(?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, password);
            statement.setInt(3, 3 + i - 1);
            statement.executeUpdate();
            statement.close();
        }
        JDBCutils.close(null, null, connection);
    }
    public static void selectAll() throws ClassNotFoundException, SQLException {
        Connection connection = JDBCutils.getConnection("myemployees", "root", "123456");
        Statement statement = connection.createStatement();
        String sql = "select * from employees order by first_name";
        ResultSet res = statement.executeQuery(sql);
        while(res.next()) {
            System.out.printf("%s %d\n", res.getString("first_name"), res.getInt("salary"));
        }
        JDBCutils.close(res, statement, connection);
    }
    public static boolean checkLogin(String user, String password) throws ClassNotFoundException, SQLException{
        Connection connection = JDBCutils.getConnection("user", "root", "123456");
        Statement statement = connection.createStatement();
        String sql = "select * from t_user where t_user.user = '" + user + "' and t_user.password = '" + password +"'";
        System.out.println(sql);
        ResultSet res = statement.executeQuery(sql);
        if (!res.next()){
            System.out.println("Wrong username or wrong password.");
            JDBCutils.close(res, statement, connection);
            return false;
        }
        System.out.println("Your ID is: " + res.getInt("id"));
        JDBCutils.close(res, statement, connection);
        return true;
    }
    public static boolean checkLogin2(String user, String password) throws ClassNotFoundException, SQLException{
        Connection connection = JDBCutils.getConnection("user", "root", "123456");
        String sql = "select * from t_user where user = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user);
        statement.setString(2, password);
        ResultSet res = statement.executeQuery();
        if (!res.next()){
            System.out.println("Wrong user name or wrong password");
            JDBCutils.close(res, statement, connection);
            return false;
        }
        System.out.println("Your ID is: " + res.getInt("id"));
        JDBCutils.close(res, statement, connection);
        return true;
    }
}