package com.example.course_management.repository;

import com.example.course_management.entity.ScheduleManagement;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * JI.
 * 資料庫存取層接口，用於存取課程進度管理資料。
 */
@Repository
public interface ScheduleManagementDao extends JpaRepository<ScheduleManagement, String> {

  /**
   * 根據學生ID、課程代碼和課程大綱查找課程進度管理記錄.
   *
   * @param studentId     學生ID
   * @param courseCode    課程代碼
   * @param courseOutline 課程大綱
   * @return 符合條件的課程進度管理對象，如果不存在則返回空的 Optional
   */
  @Query("SELECT sm FROM ScheduleManagement sm WHERE sm.studentId = :studentId AND sm.courseCode = :courseCode AND sm.courseOutline = :courseOutline")
  Optional<ScheduleManagement> findByStudentIdAndCourseCodeAndCourseOutline(
           String studentId, String courseCode, String courseOutline);

  /**
   * 根據學生ID模糊查詢所有符合條件的課程進度管理記錄列表.
   *
   * @param studentId 學生ID的一部分（不區分大小寫）
   * @return 符合條件的課程進度管理記錄列表，如果不存在則返回空列表
   */
  List<ScheduleManagement> findAllByStudentIdContainingIgnoreCase(String studentId);

  /**
   * 根據學生ID、課程代碼和課程項目查找課程進度管理記錄.
   *
   * @param studentId    學生ID
   * @param courseCode   課程代碼
   * @param courseProject 課程項目
   * @return 符合條件的課程進度管理對象，如果不存在則返回 null
   */
  ScheduleManagement findByStudentIdAndCourseCodeAndCourseProject(String studentId, String courseCode, String courseProject);

}
