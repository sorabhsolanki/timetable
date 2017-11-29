package server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import server.cache.UserCache;
import server.dto.AddScheduleDto;
import server.dto.SearchDto;
import server.repository.ScheduleRepository;

/**
 */
public class UserService {

  private static final UserService USER_SERVICE = new UserService();

  private final UserCache userCache = UserCache.getInstance();
  private final ScheduleRepository scheduleRepository = ScheduleRepository.getInstance();

  private UserService(){}

  public static UserService getInstance(){
    return USER_SERVICE;
  }

  public void addNewSchedule(AddScheduleDto addScheduleDto){

    if(!userCache.getUserIdMap().containsKey(addScheduleDto.getUserName())){
      System.out.println("No userName found.");
      return;
    }

    if(addScheduleDto.getStartTime().equals(addScheduleDto.getEndTime())){
      System.out.println("StartTime and endTime can not be same.");
      return;
    }

    int userId = userCache.getUserIdMap().get(addScheduleDto.getUserName());
    addScheduleDto.setUserId(userId);

    scheduleRepository.addSchedule(addScheduleDto);
  }

  public Map<String, List<AddScheduleDto>> getAllSchedules(){

    List<AddScheduleDto> addScheduleDtos = scheduleRepository.getAllSchedules();
    Map<Integer, String> userMap = userCache.getUserCache();

    Map<String, List<AddScheduleDto>> map = new HashMap<>();

    addScheduleDtos.forEach(addScheduleDto -> {
      String userName = userMap.get(addScheduleDto.getUserId());
      if(map.containsKey(userName)){
        List<AddScheduleDto> dtos = map.get(userName);
        dtos.add(addScheduleDto);
      }else {
        List<AddScheduleDto> dtos = new ArrayList<>();
        dtos.add(addScheduleDto);
        map.put(userName, dtos);
      }
    });

    return map;
  }

  public String getDescriptionOfUserWork(int id){
    return scheduleRepository.getScheduleDescription(id);
  }

  public AddScheduleDto searchSchedule(SearchDto searchDto){

    if(!userCache.getUserIdMap().containsKey(searchDto.getUserName())){
      System.out.println("No userName found.");
      return new AddScheduleDto();
    }
    //set the userId
    searchDto.setUserId(Long.valueOf(userCache.getUserIdMap().get(searchDto.getUserName())));
    return scheduleRepository.searchSchedule(searchDto);
  }

}
