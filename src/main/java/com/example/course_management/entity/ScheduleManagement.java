package com.example.course_management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

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

    public ScheduleManagement(Integer id, String studentId, String courseCode, String courseOutline, String courseProject, boolean understand, String question, boolean solve) {
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
