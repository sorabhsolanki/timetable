package server.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import server.connection.Conn;
import server.connection.ConnectionManager;
import server.dto.AddScheduleDto;

/**
 */
public class ScheduleRepository {

  private static final ScheduleRepository SCHEDULE_REPOSITORY = new ScheduleRepository(
      ConnectionManager.getInstance(20));

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  private static final String insert_schedule = " INSERT INTO time_table "
      + "(user_id, start_time, end_time, title, start_date, end_date)"
      + " VALUES (?, ?, ?, ?, ?, ?)";

  private static final String select_all_schedules = "SELECT * from time_table";

  private final Conn connection;

  private ScheduleRepository(ConnectionManager connectionManager) {
    this.connection = connectionManager.getConnection();
  }

  public static ScheduleRepository getInstance(){
    return SCHEDULE_REPOSITORY;
  }

  public void addSchedule(AddScheduleDto addScheduleDto){

    try {
      PreparedStatement preparedStmt = connection.getConnection().prepareStatement(insert_schedule);
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

      preparedStmt.execute();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public List<AddScheduleDto> getAllSchedules(){

    List<AddScheduleDto> addScheduleDtos = new ArrayList<>();
    try {
      PreparedStatement preparedStmt = connection.getConnection().prepareStatement(select_all_schedules);
      ResultSet resultSet = preparedStmt.executeQuery();

      while (resultSet.next()) {
        addScheduleDtos.add(new AddScheduleDto(resultSet.getInt("user_id"),
            resultSet.getString("start_time"), resultSet.getString("end_time"),
            resultSet.getString("title"), resultSet.getDate("start_date").toString(),
            resultSet.getDate("end_date").toString()));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return addScheduleDtos;
  }
}
