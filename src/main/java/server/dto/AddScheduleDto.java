package server.dto;

/**
 */
public class AddScheduleDto {

  private int id;

  private String userName;
  private String startTime;
  private String endTime;
  private String title;
  private String startDate;
  private String endDate;
  private String description;
  private String status;

  private int userId;
  private int statusId;

  public AddScheduleDto() {
  }

  public AddScheduleDto(String userName, String startTime, String endTime, String title,
      String startDate, String endDate, String description, String status) {
    this.userName = userName;
    this.startTime = startTime;
    this.endTime = endTime;
    this.title = title;
    this.startDate = startDate;
    this.endDate = endDate;
    this.description = description;
    this.status = status;
  }

  public AddScheduleDto(int id, int userId, String startTime, String endTime, String title,
      String startDate,
      String endDate, String description) {
    this.id = id;
    this.userId = userId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.title = title;
    this.startDate = startDate;
    this.endDate = endDate;
    this.description = description;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getStatusId() {
    return statusId;
  }

  public void setStatusId(int statusId) {
    this.statusId = statusId;
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
        ", description='" + description + '\'' +
        '}';
  }
}
