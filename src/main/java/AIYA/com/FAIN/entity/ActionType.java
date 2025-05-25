package AIYA.com.FAIN.entity;

public enum ActionType {
  _119("119 이송"),
  FAMILY("보호자 조치");

  private final String value;

  ActionType(String value) {
    this.value = value;
  }
  public String value() {
    return value;
  }
}
