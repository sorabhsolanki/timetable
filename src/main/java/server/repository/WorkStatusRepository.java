package server.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import server.connection.ConnectionManager;
import server.dto.WorkStatusDto;

/**
 */
public class WorkStatusRepository {

  private static final WorkStatusRepository WORK_STATUS_REPOSITORY = new WorkStatusRepository(
      ConnectionManager.getInstance(20));
  private static final String select_all_status = "SELECT * FROM work_status";

  private final ConnectionManager connectionManager;

  private WorkStatusRepository(ConnectionManager connectionManager) {
    this.connectionManager = connectionManager;
  }

  public static WorkStatusRepository getInstance(){
    return WORK_STATUS_REPOSITORY;
  }

  public List<WorkStatusDto> getWorkStatus() {

    Connection connection = connectionManager.getConnection();
    List<WorkStatusDto> workStatusDtos = new ArrayList<>();
    try {
      PreparedStatement statement = connection.prepareStatement(select_all_status);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        workStatusDtos.add(new WorkStatusDto(resultSet.getInt("id"),
            resultSet.getString("status")));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }

    return workStatusDtos;
  }
}
