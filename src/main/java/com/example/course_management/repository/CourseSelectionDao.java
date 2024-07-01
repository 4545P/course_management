package com.example.course_management.repository;

import com.example.course_management.entity.CourseSelection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * JI.
 * 資料庫存取層接口，用於存取課程選課資料。
 */
@Repository
public interface CourseSelectionDao extends JpaRepository<CourseSelection, Integer> {

  /**
   * 根據課程代碼查找課程選課資料列表.
   *
   * @param courseCode 課程代碼
   * @return 符合課程代碼的課程選課資料列表
   */
  List<CourseSelection> findByCourseCode(String courseCode);

  /**
   * 查找已選課程的學生資料列表.
   *
   * @return 已選課程的學生資料列表
   */
  @Query("SELECT cs FROM CourseSelection cs WHERE cs.courseCode IS NOT NULL")
  List<CourseSelection> findStudentsWithSelectedCourses();

}
