package com.example.course_management.repository;

import com.example.course_management.entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseScheduleDao extends JpaRepository<CourseSchedule, Integer> {

    public CourseSchedule findByCourseCode(String courseCode);

    public CourseSchedule findByCourseCodeAndCourseProject(String courseCode, String courseProject);
}
