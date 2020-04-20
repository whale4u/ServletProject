package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        HttpSession httpSession = req.getSession(false);

        if (httpSession != null) {
            String userName = (String)httpSession.getAttribute("userName");
            if (userName != null) {
                pw.print("您好，" + userName + " 欢迎您来到个人信息中心！");
            }
        } else {
            String url = resp.encodeRedirectURL(req.getContextPath() + "/index.html");
            resp.sendRedirect(url);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
