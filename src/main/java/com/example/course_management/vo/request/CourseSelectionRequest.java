package com.example.course_management.vo.request;

import com.example.course_management.entity.Student;

public class CourseSelectionRequest {

    public Integer studentId;

    public String name;

    public String courseCode;

    public CourseSelectionRequest() {
    }

    public CourseSelectionRequest(Integer studentId, String name, String courseCode) {
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
