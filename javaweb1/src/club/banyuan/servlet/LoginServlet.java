package club.banyuan.servlet;


import club.banyuan.pojo.User;
import club.banyuan.service.impl.UserServiceImpl;
import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String landName = request.getParameter("landName");
        String landPassword = request.getParameter("landPassword");

        User user = new User();

        user.setLoginName(landName);
        user.setPassword(landPassword);

        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (userService.login(user) != null){
                response.sendRedirect("Sell.html");
            }else {
                response.sendRedirect("login.html");
            }
        } catch (Exception e) {
            response.sendRedirect("login.html");
        }
    }
}