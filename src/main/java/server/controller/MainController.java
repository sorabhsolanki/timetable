package server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.cache.UserCache;
import server.dto.AddScheduleDto;
import server.service.UserService;

/**
 */
public class MainController extends HttpServlet {

  private static final UserService userService = new UserService();

  @Override
  public void init() {
    initializeUserCache();
  }

  private void initializeUserCache() {
    UserCache.getInstance().initializeCache();
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    AddScheduleDto addScheduleDto = new AddScheduleDto(request.getParameter("userName"),
        request.getParameter("startTime"), request.getParameter("endTime"),
        request.getParameter("title"), request.getParameter("startDate"),
        request.getParameter("endDate"));

    userService.addNewSchedule(addScheduleDto);

    response.sendRedirect(request.getServletContext().getContextPath()+ "/time");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    List<String> userNames = new ArrayList<>();
    Map<Integer, String> userCache = UserCache.getInstance().getUserCache();

    Map<Integer, String> treeMap = new TreeMap<>();
    treeMap.putAll(userCache);

    for (Entry<Integer, String> entry : treeMap.entrySet()) {
      userNames.add(entry.getValue());
    }

    request.setAttribute("userList", userNames);
    request.getRequestDispatcher("/add_schedule.jsp").forward(request, response);
  }
}
