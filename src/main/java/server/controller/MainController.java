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
import server.dto.SearchDto;
import server.service.UserService;

/**
 */
public class MainController extends HttpServlet {

  private static final UserService userService = UserService.getInstance();

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

    String requestType = request.getParameter("query");

    if (requestType.equals("add")) {

      AddScheduleDto addScheduleDto = new AddScheduleDto(request.getParameter("userName"),
          request.getParameter("startTime"), request.getParameter("endTime"),
          request.getParameter("title"), request.getParameter("startDate"),
          request.getParameter("endDate"), request.getParameter("description"));
      userService.addNewSchedule(addScheduleDto);

      response.sendRedirect(request.getServletContext().getContextPath() + "/time");

    } else if (requestType.equals("update")) {

      response.sendRedirect(request.getServletContext().getContextPath() + "/time");

    } else if (requestType.equals("search")) {
      SearchDto searchDto = new SearchDto(request.getParameter("userName"),
          request.getParameter("title"));
      AddScheduleDto addScheduleDto = userService.searchSchedule(searchDto);

      request.setAttribute("userName", addScheduleDto.getUserName());

      List<String> startTimes = getTimes();
      request.setAttribute("startTimes", startTimes);
      request.setAttribute("startTimeSelected", addScheduleDto.getStartTime());

      List<String> endTimes = getTimes();
      request.setAttribute("endTimes", endTimes);
      request.setAttribute("endTimeSelected", addScheduleDto.getEndTime());

      request.setAttribute("title", addScheduleDto.getTitle());

      request.setAttribute("startDate", addScheduleDto.getStartDate());
      request.setAttribute("endDate", addScheduleDto.getEndDate());

      request.setAttribute("description", addScheduleDto.getDescription());

      request.getRequestDispatcher("/update_schedule.jsp").forward(request, response);
    }
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String requestType = request.getParameter("query");

    List<String> userNames = getUserNames();
    request.setAttribute("userList", userNames);

    if(requestType.equals("add")){
      request.getRequestDispatcher("/add_schedule.jsp").forward(request, response);
    }else if(requestType.equals("search")){
      request.getRequestDispatcher("/search_schedule.jsp").forward(request, response);
    }
  }

  private List<String> getUserNames() {
    List<String> userNames = new ArrayList<>();
    Map<Integer, String> userCache = UserCache.getInstance().getUserCache();

    Map<Integer, String> treeMap = new TreeMap<>();
    treeMap.putAll(userCache);

    for (Entry<Integer, String> entry : treeMap.entrySet()) {
      userNames.add(entry.getValue());
    }
    return userNames;
  }

  public List<String> getTimes() {
    List<String> times = new ArrayList<>(10);
    times.add("9:00");
    times.add("10:00");
    times.add("11:00");
    times.add("12:00");
    times.add("13:00");
    times.add("14:00");
    times.add("15:00");
    times.add("16:00");
    times.add("17:00");
    times.add("18:00");
    return times;
  }
}
