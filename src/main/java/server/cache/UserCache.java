package server.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import server.dto.UserDto;
import server.repository.UserRepository;

/**
 */
public class UserCache {

  private static final UserCache USER_CACHE = new UserCache();
  private final Map<Integer, String> userCache = new HashMap<>();
  private final Map<String, Integer> userIdMap = new HashMap<>();

  private final UserRepository userRepository = UserRepository.getInstance();

  public static UserCache getInstance(){
    return USER_CACHE;
  }

  public void initializeCache(){
    List<UserDto> userDtos = userRepository.getUserDetails();
    userDtos.forEach(userDto -> {
      userCache.put(userDto.getId(), userDto.getUserName());
      userIdMap.put(userDto.getUserName(), userDto.getId());
    });
  }

  public Map<Integer, String> getUserCache() {
    return userCache;
  }

  public Map<String, Integer> getUserIdMap() {
    return userIdMap;
  }
}
