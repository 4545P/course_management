package com.example.course_management.repository;

import com.example.course_management.entity.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JI.
 * 資料庫存取層接口，用於存取課程資料。
 */
@Repository
public interface CourseDao extends JpaRepository<Course, String> {

  /**
   * 查找所有標題包含指定不區分大小寫子串的課程.
   *
   * @param title 要在課程標題中查找的子串
   * @return 包含指定子串的課程列表
   */
  List<Course> findAllByCourseTitleContainingIgnoreCase(String title);

}
