package server.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import server.connection.ConnectionManager;
import server.dto.AddScheduleDto;
import server.dto.DeleteDto;
import server.dto.MoveDto;
import server.dto.SearchDto;

/**
 */
public class ScheduleRepository {

  private static final ScheduleRepository SCHEDULE_REPOSITORY = new ScheduleRepository(
      ConnectionManager.getInstance(20));

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  private final DateTimeFormatter updated_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private static final String insert_schedule = " INSERT INTO time_table "
      + "(user_id, start_time, end_time, title, start_date, end_date, description, status)"
      + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String select_all_schedules = "SELECT * FROM time_table WHERE enabled = ?";

  private static final String select_user_description = "SELECT description FROM time_table WHERE "
      + "id = ?";

  private static final String select_work_status = "SELECT status FROM time_table WHERE "
      + "id = ?";

  private static final String search_user_schedule = "SELECT * FROM time_table WHERE "
      + "user_id = ? AND title = ?";

  private static final String delete_user_schedule = "UPDATE time_table set enabled = false WHERE "
      + "user_id = ? and title = ?";

  private static final String update_schedule = " UPDATE time_table "
      + " SET start_time = ?, end_time = ?, title = ?, start_date = ?, end_date = ?, description = ?, "
      + "status = ? WHERE id = ?";

  private static final String move_user_schedule = "UPDATE time_table set user_id = ? WHERE "
      + "user_id = ? and title = ?";

  private final ConnectionManager connectionManager;

  private ScheduleRepository(ConnectionManager connectionManager) {
    this.connectionManager = connectionManager;
  }

  public static ScheduleRepository getInstance() {
    return SCHEDULE_REPOSITORY;
  }

  public void addSchedule(AddScheduleDto addScheduleDto) {

    Connection connection = connectionManager.getConnection();
    try {
      PreparedStatement preparedStmt = connection.prepareStatement(insert_schedule);
      preparedStmt.setInt(1, addScheduleDto.getUserId());
      preparedStmt.setString(2, addScheduleDto.getStartTime());
      preparedStmt.setString(3, addScheduleDto.getEndTime());
      preparedStmt.setString(4, addScheduleDto.getTitle());

      String startDate = addScheduleDto.getStartDate();
      LocalDate dateTime = LocalDate.parse(startDate, formatter);
      preparedStmt.setDate(5, Date.valueOf(dateTime));

      String endDate = addScheduleDto.getEndDate();
      LocalDate endDateTime = LocalDate.parse(endDate, formatter);
      preparedStmt.setDate(6, Date.valueOf(endDateTime));

      preparedStmt.setString(7, addScheduleDto.getDescription());

      preparedStmt.setInt(8, addScheduleDto.getStatusId());

      preparedStmt.execute();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public List<AddScheduleDto> getAllSchedules() {

    List<AddScheduleDto> addScheduleDtos = new ArrayList<>();
    Connection connection = connectionManager.getConnection();
    try {
      PreparedStatement preparedStmt = connection.prepareStatement(select_all_schedules);
      preparedStmt.setBoolean(1, true);
      ResultSet resultSet = preparedStmt.executeQuery();

      while (resultSet.next()) {
        addScheduleDtos.add(new AddScheduleDto(resultSet.getInt("id"),
            resultSet.getInt("user_id"),
            resultSet.getString("start_time"), resultSet.getString("end_time"),
            resultSet.getString("title"), resultSet.getDate("start_date").toString(),
            resultSet.getDate("end_date").toString(), resultSet.getString("description")));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }

    return addScheduleDtos;
  }

  public String getScheduleDescription(int id) {
    String description = "";
    Connection connection = connectionManager.getConnection();
    try {
      PreparedStatement preparedStmt = connection.prepareStatement(select_user_description);
      preparedStmt.setInt(1, id);
      ResultSet resultSet = preparedStmt.executeQuery();
      while (resultSet.next()) {
        description = resultSet.getString("description");
      }
      return description == null ? "" : description;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }

    return description;
  }

  public int getWorkStatus(int id) {
    int workStatusId = -1;
    Connection connection = connectionManager.getConnection();
    try {
      PreparedStatement preparedStmt = connection.prepareStatement(select_work_status);
      preparedStmt.setInt(1, id);
      ResultSet resultSet = preparedStmt.executeQuery();
      while (resultSet.next()) {
        workStatusId = resultSet.getInt("status");
      }
      return workStatusId;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }

    return workStatusId;
  }

  public AddScheduleDto searchSchedule(SearchDto searchDto) {

    AddScheduleDto addScheduleDto = new AddScheduleDto();
    PreparedStatement preparedStmt = null;
    Connection connection = connectionManager.getConnection();
    try {
      preparedStmt = connection.prepareStatement(search_user_schedule);
      preparedStmt.setLong(1, searchDto.getUserId());
      preparedStmt.setString(2, searchDto.getTitle());
      ResultSet resultSet = preparedStmt.executeQuery();
      while (resultSet.next()) {
        addScheduleDto.setId(resultSet.getInt("id"));
        addScheduleDto.setUserId(resultSet.getInt("user_id"));
        addScheduleDto.setStartTime(resultSet.getString("start_time"));
        addScheduleDto.setEndTime(resultSet.getString("end_time"));
        addScheduleDto.setTitle(resultSet.getString("title"));
        addScheduleDto.setStartDate(resultSet.getDate("start_date").toString());
        addScheduleDto.setEndDate(resultSet.getDate("end_date").toString());
        addScheduleDto.setDescription(resultSet.getString("description"));
        addScheduleDto.setStatusId(resultSet.getInt("status"));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }

    return addScheduleDto;
  }

  public void updateSchedule(AddScheduleDto addScheduleDto) {
    Connection connection = connectionManager.getConnection();
    try {
      PreparedStatement preparedStmt = connection.prepareStatement(update_schedule);
      preparedStmt.setString(1, addScheduleDto.getStartTime());
      preparedStmt.setString(2, addScheduleDto.getEndTime());
      preparedStmt.setString(3, addScheduleDto.getTitle());

      String startDate = addScheduleDto.getStartDate();
      LocalDate dateTime = LocalDate.parse(startDate, updated_formatter);
      preparedStmt.setDate(4, Date.valueOf(dateTime));

      String endDate = addScheduleDto.getEndDate();
      LocalDate endDateTime = LocalDate.parse(endDate, updated_formatter);
      preparedStmt.setDate(5, Date.valueOf(endDateTime));

      preparedStmt.setString(6, addScheduleDto.getDescription());

      preparedStmt.setInt(7, addScheduleDto.getStatusId());

      preparedStmt.setInt(8, addScheduleDto.getId());

      preparedStmt.execute();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void deleteSchedule(DeleteDto deleteDto) {

    PreparedStatement preparedStmt = null;
    Connection connection = connectionManager.getConnection();
    try {
      preparedStmt = connection.prepareStatement(delete_user_schedule);
      preparedStmt.setLong(1, deleteDto.getUserId());
      preparedStmt.setString(2, deleteDto.getTitle());
      preparedStmt.execute();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void moveSchedule(MoveDto moveDto) {
    PreparedStatement preparedStmt = null;
    Connection connection = connectionManager.getConnection();
    try {
      preparedStmt = connection.prepareStatement(move_user_schedule);
      preparedStmt.setLong(1, moveDto.getTargetUserId());
      preparedStmt.setLong(2, moveDto.getSourceUserId());
      preparedStmt.setString(3, moveDto.getTitle());
      preparedStmt.execute();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
