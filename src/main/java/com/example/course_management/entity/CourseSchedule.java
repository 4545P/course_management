package com.example.course_management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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

    public CourseSchedule(Integer id, String courseCode, LocalDate courseDate, String courseOutline, String courseProject, String courseContent) {
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
