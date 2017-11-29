package server.dto;

/**
 */
public class SearchDto {

  private String userName;
  private String title;
  private Long userId;

  public SearchDto() {
  }

  public SearchDto(String userName, String title) {
    this.userName = userName;
    this.title = title;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
