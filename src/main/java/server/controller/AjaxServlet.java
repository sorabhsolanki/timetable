package server.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.service.UserService;

/**
 */
public class AjaxServlet extends HttpServlet {

  private static final UserService userService = UserService.getInstance();

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    final String hideTag = request.getParameter("hideTag");

    if(hideTag.contains("addSchedule"))
      return;

    final String status = request.getParameter("status");
    String description;
    if(status == null){
       description = userService
          .getDescriptionOfUserWork(Integer.parseInt(request.getParameter("hideTag")));
    }else{
      description = userService
          .getStatusOfUserWork(Integer.parseInt(request.getParameter("hideTag")));
    }
    response.getWriter().write(description);
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

  }
}
