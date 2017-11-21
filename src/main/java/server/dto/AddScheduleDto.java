package server.dto;

/**
 */
public class AddScheduleDto {

  private String userName;
  private String startTime;
  private String endTime;
  private String title;
  private String startDate;
  private String endDate;

  private int userId;

  public AddScheduleDto(String userName, String startTime, String endTime, String title,
      String startDate, String endDate) {
    this.userName = userName;
    this.startTime = startTime;
    this.endTime = endTime;
    this.title = title;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public AddScheduleDto(int userId, String startTime, String endTime, String title, String startDate,
      String endDate) {
    this.userId = userId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.title = title;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getUserName() {
    return userName;
  }

  public String getStartTime() {
    return startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public String getTitle() {
    return title;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return "AddScheduleDto{" +
        "userName='" + userName + '\'' +
        ", startTime='" + startTime + '\'' +
        ", endTime='" + endTime + '\'' +
        ", title='" + title + '\'' +
        ", startDate='" + startDate + '\'' +
        ", endDate='" + endDate + '\'' +
        '}';
  }
}
