package controller;

import dao.DBUtil;
import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    String name=null;
    String password=null;
    UserDao dba;
    User user;
    public void init(){
        dba=new UserDao();
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
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html");

            name=req.getParameter("name");
            String temppass=req.getParameter("password");

            System.out.println(name);
            System.out.println(password);
            try{
                user =dba.query(name);    //查询是否有name用户，有则获得密码
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }

            PrintWriter out = resp.getWriter();
            if(!temppass.equals(user.getPassword())) {  //密码不正确
                out.println("密码不正确");
            }
            else { //密码正确
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("userName", name);
//                out.println("密码正确！");

                req.getRequestDispatcher("/home").include(req, resp);
            }
        }
}
