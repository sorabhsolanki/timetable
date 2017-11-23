package server.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import server.connection.ConnectionManager;
import server.dto.UserDto;

/**
 */
public class UserRepository {

  private static final UserRepository USER_REPOSITORY = new UserRepository(
      ConnectionManager.getInstance(20));
  private static final String select_all_users = "SELECT * FROM user";

  private final ConnectionManager connectionManager;

  private UserRepository(ConnectionManager connectionManager) {
    this.connectionManager = connectionManager;
  }

  public static UserRepository getInstance(){
    return USER_REPOSITORY;
  }

  public List<UserDto> getUserDetails() {

    List<UserDto> userDtos = new ArrayList<>();
    try {
      PreparedStatement statement = connectionManager.getConnection().prepareStatement(select_all_users);
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        userDtos.add(new UserDto(resultSet.getInt("id"),
            resultSet.getString("user_name")));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return userDtos;
  }
}
