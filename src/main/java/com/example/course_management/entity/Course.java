package com.example.course_management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


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

    public Course(String courseCode, String courseTitle, String courseInstructor, String courseDescription, String courseWeek, LocalDate courseDate, LocalDate courseEndDate, LocalTime classTime, LocalTime classEndTime, boolean classEnable, String classCity, LocalDateTime classAdd, LocalDateTime classRevise, String personnel) {
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

    // 將 courseWeek 字串轉換為陣列
    public String[] getCourseWeekArray() {
        if (courseWeek != null) {
            return courseWeek.split(",");
        }
        return new String[0]; // 或者返回 null 或其他適當的值
    }
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
