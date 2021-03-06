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
import server.cache.WorkStatusCache;
import server.dto.AddScheduleDto;
import server.dto.DeleteDto;
import server.dto.MoveDto;
import server.dto.SearchDto;
import server.service.UserService;

/**
 */
public class MainController extends HttpServlet {

  private static final UserService userService = UserService.getInstance();

  @Override
  public void init() {
    initializeUserCache();
    initializeWorkStatusCache();
  }

  private void initializeUserCache() {
    UserCache.getInstance().initializeCache();
  }

  private void initializeWorkStatusCache() {
    WorkStatusCache.getInstance().initializeCache();
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    checkAuth(request, response);

    String requestType = request.getParameter("query");

    if (requestType.equals("add")) {

      AddScheduleDto addScheduleDto = getAddScheduleDto(request);
      userService.addNewSchedule(addScheduleDto);

      String env = System.getProperty("env") != null ? System.getProperty("env") + "/timetable/time" :
          request.getServletContext().getContextPath() + "/time";

      response.sendRedirect(env);

    } else if (requestType.equals("update")) {

      AddScheduleDto addScheduleDto = getAddScheduleDto(request);
      addScheduleDto.setId(Integer.parseInt(request.getParameter("scheduleId")));
      userService.updateSchedule(addScheduleDto);


      String env = System.getProperty("env") != null ? System.getProperty("env") + "/timetable/time":
          request.getServletContext().getContextPath() + "/time";

      response.sendRedirect(env);

    } else if (requestType.equals("search")) {
      SearchDto searchDto = new SearchDto(request.getParameter("userName"),
          request.getParameter("title"));
      AddScheduleDto addScheduleDto = userService.searchSchedule(searchDto);

      request.setAttribute("scheduleId", addScheduleDto.getId());
      request.setAttribute("userName", request.getParameter("userName"));

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

      List<String> statusList = getStatusList();
      request.setAttribute("workStatusList", statusList);
      Map<Integer, String> statusCache = WorkStatusCache.getInstance().getStatusCache();
      request.setAttribute("workStatusSelected", statusCache.get(addScheduleDto.getStatusId()));

      request.getRequestDispatcher("/update_schedule.jsp").forward(request, response);
    }else if (requestType.equals("delete")) {
      DeleteDto deleteDto = new DeleteDto(request.getParameter("userName"),
          request.getParameter("title"));
      userService.delete(deleteDto);

      String env = System.getProperty("env") != null ? System.getProperty("env") + "/timetable/time" :
          request.getServletContext().getContextPath() + "/time";

      response.sendRedirect(env);
    }else if (requestType.equals("move")) {
      MoveDto moveDto = new MoveDto(request.getParameter("sourceUserName"),
          request.getParameter("targetUserName"), request.getParameter("title"));
      userService.move(moveDto);

      String env = System.getProperty("env") != null ? System.getProperty("env") + "/timetable/time" :
          request.getServletContext().getContextPath() + "/time";

      response.sendRedirect(env);
    }
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    checkAuth(request, response);

    String requestType = request.getParameter("query");

    List<String> userNames = getUserNames();
    request.setAttribute("userList", userNames);

    List<String> statusList = getStatusList();
    request.setAttribute("workStatusList", statusList);

    if (requestType.equals("add")) {
      request.getRequestDispatcher("/add_schedule.jsp").forward(request, response);
    } else if (requestType.equals("search")) {
      request.getRequestDispatcher("/search_schedule.jsp").forward(request, response);
    } else if (requestType.equals("delete")) {
      request.getRequestDispatcher("/delete_schedule.jsp").forward(request, response);
    } else if (requestType.equals("move")) {
      request.getRequestDispatcher("/move_task.jsp").forward(request, response);
    }
  }

  private void checkAuth(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    if(request.getAttribute("auth") == null || !request.getAttribute("auth").equals("true")){
      request.getRequestDispatcher("/login.jsp").forward(request, response);
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

  private List<String> getStatusList() {
    List<String> statusList = new ArrayList<>();
    Map<Integer, String> statusCache = WorkStatusCache.getInstance().getStatusCache();

    for (Entry<Integer, String> entry : statusCache.entrySet()) {
      statusList.add(entry.getValue());
    }
    return statusList;
  }

  private List<String> getTimes() {
    List<String> times = new ArrayList<>(16);
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
    times.add("19:00");
    times.add("20:00");
    times.add("21:00");
    times.add("22:00");
    times.add("23:00");
    times.add("24:00");
    return times;
  }

  private AddScheduleDto getAddScheduleDto(HttpServletRequest request) {
    return new AddScheduleDto(request.getParameter("userName"),
        request.getParameter("startTime"), request.getParameter("endTime"),
        request.getParameter("title"), request.getParameter("startDate"),
        request.getParameter("endDate"), request.getParameter("description"),
        request.getParameter("workStatus"));
  }
}
