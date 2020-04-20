package filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Demo1Filter初始化创建....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        String name = servletRequest.getParameter("name");

        if ("admin".equals(name)) {
            servletResponse.setContentType("text/html;charset=UTF-8");
            PrintWriter out = servletResponse.getWriter();
            out.print("<b>admin用户不允许从前台登录！</b>");

            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("Demo1Filter被销毁了....");
    }
}
