package server.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.dto.AddScheduleDto;
import server.service.UserService;

/**
 */
public class IndexController extends HttpServlet {

  private static final UserService userService = new UserService();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Map<String, List<AddScheduleDto>> map = userService.getAllSchedules();
    request.setAttribute("map", map);

    request.getRequestDispatcher("/index.jsp").forward(request, response);
  }
}
