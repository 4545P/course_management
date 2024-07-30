package com.example.course_management.vo.request;

import com.example.course_management.entity.Course;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * JI.
 * 表示用於接收課程相關操作請求的請求對象。
 * 這個類用於接收客戶端發送的課程相關操作的請求數據。
 */
public class CourseRequest {

  public String courseCode;

  public String courseTitle;

  public String courseInstructor;

  public String courseDescription;

  public String courseWeek;

  public LocalDate courseDate;

  public LocalDate courseEndDate;

  public LocalTime classTime;

  public LocalTime classEndTime;

  public boolean classEnable;

  public String classCity;

  public LocalDateTime classAdd;

  public LocalDateTime classRevise;

  public String personnel;

  public List<Course> courseList;

  public CourseRequest() {

  }

  public String getCourseCode() {
    return courseCode;
  }

  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }

  public String getCourseTitle() {
    return courseTitle;
  }

  public void setCourseTitle(String courseTitle) {
    this.courseTitle = courseTitle;
  }

  public String getCourseInstructor() {
    return courseInstructor;
  }

  public void setCourseInstructor(String courseInstructor) {
    this.courseInstructor = courseInstructor;
  }

  public String getCourseDescription() {
    return courseDescription;
  }

  public void setCourseDescription(String courseDescription) {
    this.courseDescription = courseDescription;
  }

  public String getCourseWeek() {
    return courseWeek;
  }

  public void setCourseWeek(String courseWeek) {
    this.courseWeek = courseWeek;
  }

  public LocalDate getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(LocalDate courseDate) {
    this.courseDate = courseDate;
  }

  public LocalDate getCourseEndDate() {
    return courseEndDate;
  }

  public void setCourseEndDate(LocalDate courseEndDate) {
    this.courseEndDate = courseEndDate;
  }

  public LocalTime getClassTime() {
    return classTime;
  }

  public void setClassTime(LocalTime classTime) {
    this.classTime = classTime;
  }

  public LocalTime getClassEndTime() {
    return classEndTime;
  }

  public void setClassEndTime(LocalTime classEndTime) {
    this.classEndTime = classEndTime;
  }

  public boolean isClassEnable() {
    return classEnable;
  }

  public void setClassEnable(boolean classEnable) {
    this.classEnable = classEnable;
  }

  public String getClassCity() {
    return classCity;
  }

  public void setClassCity(String classCity) {
    this.classCity = classCity;
  }

  public LocalDateTime getClassAdd() {
    return classAdd;
  }

  public void setClassAdd(LocalDateTime classAdd) {
    this.classAdd = classAdd;
  }

  public LocalDateTime getClassRevise() {
    return classRevise;
  }

  public void setClassRevise(LocalDateTime classRevise) {
    this.classRevise = classRevise;
  }

  public String getPersonnel() {
    return personnel;
  }

  public void setPersonnel(String personnel) {
    this.personnel = personnel;
  }

  public List<Course> getCourseList() {
    return courseList;
  }

  public void setCourseList(List<Course> courseList) {
    this.courseList = courseList;
  }
}
