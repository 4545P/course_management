package com.example.course_management.repository;

import com.example.course_management.entity.ScheduleManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleManagementDao extends JpaRepository<ScheduleManagement, String> {

   @Query("SELECT sm FROM ScheduleManagement sm WHERE sm.studentId = :studentId AND sm.courseCode = :courseCode AND sm.courseOutline = :courseOutline")
   public Optional<ScheduleManagement> findByStudentIdAndCourseCodeAndCourseOutline(
           String studentId, String courseCode, String courseOutline);

    public List<ScheduleManagement> findAllByStudentIdContainingIgnoreCase(String studentId);

   public  ScheduleManagement findByStudentIdAndCourseCodeAndCourseProject(String string, String courseCode, String courseProject);

    public List<ScheduleManagement> findByCourseCodeAndCourseProject(String courseCode, String courseProject);
}
