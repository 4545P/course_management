package com.example.course_management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

/**
 * JI.
 * 課程管理的資料模型。
 */
@Component
@Entity
@Table(name = "Schedule_Management")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleManagement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "student_id")
  private String studentId;


  @Column(name = "course_code")
  private String courseCode;


  @Column(name = "course_outline")
  private String courseOutline;


  @Column(name = "course_project")
  private String courseProject;

  @Column(name = "understand")
  private boolean understand;

  @Column(name = "question")
  private String question;

  @Column(name = "solve")
  private boolean solve;

  public ScheduleManagement() {
  }

  /**
   * 建立課程管理的建構子.
   *
   * @param id 課程管理的唯一識別碼
   * @param studentId 學生ID
   * @param courseCode 課程代碼
   * @param courseOutline 課程大綱
   * @param courseProject 課程專案
   * @param understand 是否理解
   * @param question 問題描述
   * @param solve 是否解決
   */
  public ScheduleManagement(Integer id, String studentId, String courseCode, String courseOutline,
                            String courseProject, boolean understand, String question, boolean solve) {
    this.id = id;
    this.studentId = studentId;
    this.courseCode = courseCode;
    this.courseOutline = courseOutline;
    this.courseProject = courseProject;
    this.understand = understand;
    this.question = question;
    this.solve = solve;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
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

  public boolean isUnderstand() {
    return understand;
  }

  public void setUnderstand(boolean understand) {
    this.understand = understand;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public boolean isSolve() {
    return solve;
  }

  public void setSolve(boolean solve) {
    this.solve = solve;
  }
}
