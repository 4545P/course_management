package com.example.course_management.repository;

import com.example.course_management.entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JI.
 * 資料庫存取層接口，用於存取課程排程資料。
 */
@Repository
public interface CourseScheduleDao extends JpaRepository<CourseSchedule, Integer> {

  /**
   * 根據課程代碼查找課程排程資料.
   *
   * @param courseCode 課程代碼
   * @return 符合課程代碼的課程排程資料，若不存在則返回 null
   */
  CourseSchedule findByCourseCode(String courseCode);

  /**
   * 根據課程代碼和課程項目查找課程排程資料.
   *
   * @param courseCode    課程代碼
   * @param courseProject 課程項目
   * @return 符合課程代碼和課程項目的課程排程資料，若不存在則返回 null
   */
  CourseSchedule findByCourseCodeAndCourseProject(String courseCode, String courseProject);

}
