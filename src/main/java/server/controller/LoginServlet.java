package server.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.StringUtils;
import server.service.UserService;
import server.util.CommonConstants;

/**
 */
public class LoginServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    String userName = request.getParameter("userName");
    String password = request.getParameter("password");

    if(userName == null || password == null || !userName.equals(CommonConstants.USER_NAME) ||
        !password.equals(CommonConstants.PASSWORD)){
      System.out.println("Not a valid userName or password.");

      String env = System.getProperty("env") != null ? System.getProperty("env") :
          request.getServletContext().getContextPath() + "/login";

      response.sendRedirect(env);

    }else{
      //set the cookie.
      Cookie cookie = new Cookie(CommonConstants.TOKEN_NAME, CommonConstants.TOKEN_VALUE);
      cookie.setPath("/timetable/");
      response.addCookie(cookie);

      String env = System.getProperty("env") != null ? System.getProperty("env") +"/timetable/time" :
          request.getServletContext().getContextPath() + "/time";

      response.sendRedirect(env);
    }
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    request.getRequestDispatcher("/login.jsp").forward(request, response);
  }
}
