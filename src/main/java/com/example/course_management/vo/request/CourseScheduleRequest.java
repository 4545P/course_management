package com.example.course_management.vo.request;

import com.example.course_management.entity.CourseSchedule;
import java.time.LocalDate;
import java.util.List;

/**
 * JI.
 * 表示用於接收課程排程相關操作請求的請求對象。
 * 這個類用於接收客戶端發送的課程排程相關操作的請求數據。
 */
public class CourseScheduleRequest {

  public Integer id;

  public String courseCode;

  public LocalDate courseDate;

  public String courseOutline;

  public String courseProject;

  public String courseContent;

  public List<CourseSchedule> scheduleList;

  public CourseScheduleRequest() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public LocalDate getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(LocalDate courseDate) {
    this.courseDate = courseDate;
  }

  public String getCourseOutline() {
    return courseOutline;
  }

  public void setCourseOutline(String courseOutline) {
    this.courseOutline = courseOutline;
  }

  public String getCourseProject() {
    return courseProject;
  }

  public void setCourseProject(String courseProject) {
    this.courseProject = courseProject;
  }

  public String getCourseContent() {
    return courseContent;
  }

  public void setCourseContent(String courseContent) {
    this.courseContent = courseContent;
  }

  public List<CourseSchedule> getScheduleList() {
    return scheduleList;
  }

  public void setScheduleList(List<CourseSchedule> scheduleList) {
    this.scheduleList = scheduleList;
  }
}
