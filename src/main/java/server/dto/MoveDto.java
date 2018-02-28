package server.dto;

/**
 */
public class MoveDto {

  private String sourceUserName;
  private String targetUserName;
  private String title;
  private Long sourceUserId;
  private Long targetUserId;

  public MoveDto() {
  }

  public MoveDto(String sourceUserName, String targetUserName, String title) {
    this.sourceUserName = sourceUserName;
    this.targetUserName = targetUserName;
    this.title = title;
  }

  public String getSourceUserName() {
    return sourceUserName;
  }

  public void setSourceUserName(String sourceUserName) {
    this.sourceUserName = sourceUserName;
  }

  public String getTargetUserName() {
    return targetUserName;
  }

  public void setTargetUserName(String targetUserName) {
    this.targetUserName = targetUserName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getSourceUserId() {
    return sourceUserId;
  }

  public void setSourceUserId(Long sourceUserId) {
    this.sourceUserId = sourceUserId;
  }

  public Long getTargetUserId() {
    return targetUserId;
  }

  public void setTargetUserId(Long targetUserId) {
    this.targetUserId = targetUserId;
  }
}
