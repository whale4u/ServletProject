package controller;

import dao.DBUtil;
import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    String name = null;
    String password = null;
    UserDao dba;
    User user;

    public void  init() {
        dba = new UserDao();
        try {
            DBUtil.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        name = req.getParameter("name");
        password = req.getParameter("password");

        PrintWriter out = resp.getWriter();

        try {
            user = dba.query(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user.getName() != null) {
            out.println("该用户已存在！");
        } else {
            try {
                dba.insert(name, password);
                out.println("注册成功！3秒钟跳到登录页!");
                resp.setHeader("refresh", "3;url=login.html");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("注册出错！请检查您的输入！");
            }
        }

    }
}
