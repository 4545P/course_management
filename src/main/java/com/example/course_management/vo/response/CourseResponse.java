package com.example.course_management.vo.response;

import com.example.course_management.entity.Course;
import com.example.course_management.entity.CourseSchedule;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 * JI.
 * 表示課程相關操作的響應對象。
 * 屬性值為空（null）的屬性將不包含在JSON序列化中。
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {

  private List<Course> courseList;

  private List<CourseSchedule> scheduleList;

  private String courseId;

  private String message;

  public CourseResponse(List<Course> courseList, String message) {
    this.courseList = courseList;
    this.message = message;
  }

  public CourseResponse(String message, List<CourseSchedule> scheduleList) {
    this.message = message;
    this.scheduleList = scheduleList;
  }

  public CourseResponse(String message) {
    this.message = message;
  }

  public CourseResponse(String courseId, String message) {
    this.courseId = courseId;
    this.message = message;
  }

  public List<Course> getCourseList() {
    return courseList;
  }

  public void setCourseList(List<Course> courseList) {
    this.courseList = courseList;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
