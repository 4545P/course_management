package com.example.course_management.constants;

public enum WidgetApiRtnCode {
  SUCCESSFUL(200, "成功"),
  FAILED(400, "失敗"),
  PARANETER_REQUIRE(401, "缺少必要參數"),
  ADD_COURSE_SUCCESSFUL(1001, "新增課程成功"),
  ADD_COURSE_FAIL(1002, "新增課程失敗"),
  UPDATE_COURSE_SUCCESSFUL(1003, "更新課程成功"),
  UPDATE_COURSE_FAIL(1003, "更新課程失敗"),
  NO_STUDENTS_FOUND(1004, "沒有找到學生"),
  THE_STUDENT_HAS_SELECTED_A_COURSE(1005, "學生已選擇課程"),
  THE_COURSE_CANNOT_BE_FOUND(1006, "找不到該課程"),
  THIS_COURSE_CANNOT_BE_SELECTED(1007, "該課程不能被選擇"),
  COURSE_SELECTION_SUCCESSFUL(1008, "選課成功"),
  FAILED_TO_ADD_COURSE_SCHEDULE(1009, "新增課程時間表失敗"),
  ADDED_COURSE_SCHEDULE_SUCCESSFULLY(1010, "新增課程時間表成功"),
  FAILED_TO_UPDATE_STUDENT_PROGRESS(1011, "更新學生進度失敗"),
  UPDATE_STUDENT_PROGRESS_SUCCESSFULLY(1012, "更新學員進度成功"),
  SCHEDULED_TASKS_START(1013, "定時任務開始"),
  FAILED_TO_ADD_NEW_STAFF(1014, "新增教職員失敗"),
  ADDED_FACULTY_SUCCESSFULLY(1015, "新增教職員成功"),
  UPDATE_STAFF_FAILED(1016, "更新教職員失敗"),
  UPDATE_STAFF_SUCCESSFUL(1017, "更新教職員成功");

  private final int code;
  private final String message;

  WidgetApiRtnCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
