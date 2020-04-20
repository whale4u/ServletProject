package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public void insert(String name, String pass) throws SQLException {

        Connection conn = DBUtil.init();

        String sql = "insert into users(uname, upass) values(?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        String uname = name;
        String upass = pass;

        pstmt.setString(1, uname);
        pstmt.setString(2, upass);

        int result = pstmt.executeUpdate();

        pstmt.close();

        System.out.print("操作成功");
        System.out.print(result);
    }

    public User query(String name) throws SQLException {
        User user = new User();

        Connection conn = DBUtil.init();
        String sql = "select uname, upass from users where uname=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        String uname = name;
        pstmt.setString(1, uname);

        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            String dbname = resultSet.getString("uname");
            String dbpass = resultSet.getString("upass");

            if (!dbname.isEmpty()) {
                user.setName(dbname);
                user.setPassword(dbpass);
            }
        }

        pstmt.close();

        System.out.print("操作成功");

        return user;
    }
}
