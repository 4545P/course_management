package com.example.course_management.vo.request;

import com.example.course_management.entity.ScheduleManagement;
import java.util.List;

/**
 * JI.
 * 表示用於接收課程管理請求的請求對象。
 * 這個類用於接收客戶端發送的課程管理相關操作的請求數據。
 */
public class ScheduleManagementRequest {

  public Integer id;

  public String studentId;

  public String courseCode;

  public String courseOutline;

  public String courseProject;

  public boolean understand;

  public String question;

  public boolean solve;

  public List<ScheduleManagement> managementList;

  public ScheduleManagementRequest() {
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

  public List<ScheduleManagement> getManagementList() {
    return managementList;
  }

  public void setManagementList(List<ScheduleManagement> managementList) {
    this.managementList = managementList;
  }
}
