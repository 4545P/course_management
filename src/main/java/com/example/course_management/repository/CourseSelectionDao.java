package com.example.course_management.repository;

import com.example.course_management.entity.CourseSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSelectionDao extends JpaRepository<CourseSelection, Integer> {

    public List<CourseSelection> findByCourseCode(String courseCode);

    @Query("SELECT cs FROM CourseSelection cs WHERE cs.courseCode IS NOT NULL")
    public List<CourseSelection> findStudentsWithSelectedCourses();
}
