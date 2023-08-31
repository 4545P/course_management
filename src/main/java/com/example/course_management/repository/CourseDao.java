package com.example.course_management.repository;

import com.example.course_management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseDao extends JpaRepository<Course, String> {


   public Optional<Course> findByCourseCode(String courseCode);

   public List<Course> findAllByCourseTitleContainingIgnoreCase(String title);
}
