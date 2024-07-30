package com.example.course_management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.stereotype.Component;

/**
 * JI.
 * 課程的資料模型。
 */
@Component
@Entity
@Table(name = "Course")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {

  @Id
  @Column(name = "course_code")
  private String courseCode;

  @Column(name = "course_title")
  private String courseTitle;

  @Column(name = "course_instructor")
  private String courseInstructor;

  @Column(name = "course_description")
  private String courseDescription;

  @Column(name = "course_week")
  private String courseWeek;

  @Column(name = "course_date")
  private LocalDate courseDate;

  @Column(name = "course_end_date")
  private LocalDate courseEndDate;

  @Column(name = "class_time")
  private LocalTime classTime;

  @Column(name = "class_end_time")
  private LocalTime classEndTime;

  @Column(name = "class_enable")
  private boolean classEnable;

  @Column(name = "class_city")
  private String classCity;

  @Column(name = "class_add")
  private LocalDateTime classAdd;

  @Column(name = "class_revise")
  private LocalDateTime classRevise;

  @Column(name = "personnel")
  private String personnel;

  public Course() {

  }

  /**
   * 使用提供的參數初始化課程物件.
   *
   * @param courseCode        課程代碼
   * @param courseTitle       課程標題
   * @param courseInstructor  課程教師
   * @param courseDescription 課程描述
   * @param courseWeek        課程週數
   * @param courseDate        課程開始日期
   * @param courseEndDate     課程結束日期
   * @param classTime         上課時間
   * @param classEndTime      下課時間
   * @param classEnable       課程是否啟用
   * @param classCity         上課城市
   * @param classAdd          課程新增時間
   * @param classRevise       課程修改時間
   * @param personnel         課程人員
   */
  public Course(String courseCode, String courseTitle, String courseInstructor, String courseDescription,
                String courseWeek, LocalDate courseDate, LocalDate courseEndDate, LocalTime classTime, LocalTime classEndTime,
                boolean classEnable, String classCity, LocalDateTime classAdd, LocalDateTime classRevise, String personnel) {
    this.courseCode = courseCode;
    this.courseTitle = courseTitle;
    this.courseInstructor = courseInstructor;
    this.courseDescription = courseDescription;
    this.courseWeek = courseWeek;
    this.courseDate = courseDate;
    this.courseEndDate = courseEndDate;
    this.classTime = classTime;
    this.classEndTime = classEndTime;
    this.classEnable = classEnable;
    this.classCity = classCity;
    this.classAdd = classAdd;
    this.classRevise = classRevise;
    this.personnel = personnel;
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

  /**
   * 返回課程週數的字串陣列。
   * 如果課程週數不為 null，將其以逗號分隔的字串拆分為陣列返回。
   * 如果課程週數為 null，返回空字串陣列.
   *
   * @return 字串陣列，代表課程週數；如果課程週數為 null，返回空字串陣列
   */
  public String[] getCourseWeekArray() {
    if (courseWeek != null) {
      return courseWeek.split(",");
    }
    return new String[0]; // 或者返回 null 或其他適當的值
  }

  /**
   * 根據提供的字串陣列設置課程週數。
   * 如果提供的陣列不為空且包含元素，將這些元素連接成以逗號分隔的字串並設置為課程週數。
   * 如果陣列為空或為 null，則將課程週數設置為 null.
   *
   * @param courseWeekArray 字串陣列，代表課程週數
   */
  public void setCourseWeek(String[] courseWeekArray) {
    if (courseWeekArray != null && courseWeekArray.length > 0) {
      this.courseWeek = String.join(",", courseWeekArray);
    } else {
      this.courseWeek = null;
    }
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
}
