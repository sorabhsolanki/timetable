package server.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import server.dto.WorkStatusDto;
import server.repository.WorkStatusRepository;

/**
 */
public class WorkStatusCache {

  private static final WorkStatusCache STATUS_CACHE = new WorkStatusCache();

  private final Map<Integer, String> statusCache = new HashMap<>();
  private final Map<String, Integer> statusIdMap = new HashMap<>();
  private final WorkStatusRepository workStatusRepository = WorkStatusRepository.getInstance();

  public static WorkStatusCache getInstance(){
    return STATUS_CACHE;
  }

  public void initializeCache(){
    List<WorkStatusDto> workStatusDtos = workStatusRepository.getWorkStatus();
    workStatusDtos.forEach(workStatusDto -> {
      statusCache.put(workStatusDto.getId(), workStatusDto.getStatus());
      statusIdMap.put(workStatusDto.getStatus(), workStatusDto.getId());
    });
  }

  public Map<Integer, String> getStatusCache() {
    return statusCache;
  }

  public Map<String, Integer> getStatusIdMap() {
    return statusIdMap;
  }
}
