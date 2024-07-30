package com.example.course_management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

/**
 * JI.
 * 選課學生的資料模型。
 */
@Component
@Entity
@Table(name = "Course_Selection")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseSelection {

  @Id
  @JoinColumn(name = "student_id")
  private Integer studentId;

  @JoinColumn(name = "name")
  private String name;

  @Column(name = "course_code")
  private String courseCode;

  public CourseSelection() {

  }

  /**
   * 建立課程選擇的建構子.
   *
   * @param studentId 學生ID
   * @param name 學生姓名
   * @param courseCode 課程代碼
   */
  public CourseSelection(Integer studentId, String name, String courseCode) {
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
