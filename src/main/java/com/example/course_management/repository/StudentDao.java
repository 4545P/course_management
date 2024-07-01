package com.example.course_management.repository;

import com.example.course_management.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JI.
 * 資料庫存取層接口，用於存取學生資料。
 */
@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {

  /**
   * 根據電子郵件地址查找學生.
   *
   * @param email 學生的電子郵件地址
   * @return 符合條件的學生對象，如果不存在則返回 null
   */
  Student findByEmail(String email);

  /**
   * 根據姓名查找學生.
   *
   * @param name 學生的姓名
   * @return 符合條件的學生對象，如果不存在則返回 null
   */
  Student findByName(String name);

  /**
   * 根據姓名模糊查詢所有符合條件的學生記錄列表.
   *
   * @param name 學生姓名的一部分（不區分大小寫）
   * @return 符合條件的學生記錄列表，如果不存在則返回空列表
   */
  List<Student> findAllByNameContainingIgnoreCase(String name);

}
