package com.example.course_management.vo.request;

/**
 * JI.
 * 表示用於接收課程選擇相關操作請求的請求對象。
 * 這個類用於接收客戶端發送的課程選擇相關操作的請求數據。
 */
public class CourseSelectionRequest {

  public Integer studentId;

  public String name;

  public String courseCode;

  public CourseSelectionRequest() {

  }

  /**
   * 初始化課程選擇請求對象.
   *
   * @param studentId 學生ID
   * @param name 學生姓名
   * @param courseCode 課程代碼
   */
  public CourseSelectionRequest(Integer studentId, String name, String courseCode) {
    this.studentId = studentId;
    this.name = name;
    this.courseCode = courseCode;
  }

  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

}
