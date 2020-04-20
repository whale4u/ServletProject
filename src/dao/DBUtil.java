package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    public static Connection init() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功！");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/test?allowMutiQueries=true", "root", "nikodl11");
        } catch (ClassNotFoundException e) {
            System.out.println("注册驱动失败！");
            e.printStackTrace();
            return null;
        }
    }

}
