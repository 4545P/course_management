package com.example.course_management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 * JI.
 * 課程排程的資料模型。
 */
@Component
@Entity
@Table(name = "Course_Schedule")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseSchedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;


  @Column(name = "course_code")
  private String courseCode;

  @Column(name = "course_date")
  private LocalDate courseDate;

  @Column(name = "course_outline")
  private String courseOutline;

  @Column(name = "course_project")
  private String courseProject;

  @Column(name = "course_content")
  private String courseContent;


  public CourseSchedule() {

  }

  /**
   * 建立課程排程的建構子.
   *
   * @param id 課程排程的唯一識別碼
   * @param courseCode 課程代碼
   * @param courseDate 課程日期
   * @param courseOutline 課程大綱
   * @param courseProject 課程專案
   * @param courseContent 課程內容
   */
  public CourseSchedule(Integer id, String courseCode, LocalDate courseDate,
                        String courseOutline, String courseProject, String courseContent) {
    this.id = id;
    this.courseCode = courseCode;
    this.courseDate = courseDate;
    this.courseOutline = courseOutline;
    this.courseProject = courseProject;
    this.courseContent = courseContent;
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

}
