package com.example.course_management.repository;

import com.example.course_management.entity.Personnel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JI.
 * 資料庫存取層接口，用於存取人員資料。
 */
@Repository
public interface PersonnelDao extends JpaRepository<Personnel, Integer> {

  /**
   * 根據名稱判斷人員是否存在.
   *
   * @param name 人員名稱
   * @return 如果存在返回 true，否則返回 false
   */
  boolean existsByName(String name);

  /**
   * 根據電子郵件查找人員.
   *
   * @param email 電子郵件地址
   * @return 符合條件的人員對象，如果不存在則返回 null
   */
  Personnel findByEmail(String email);

  /**
   * 根據名稱查找人員.
   *
   * @param name 人員名稱
   * @return 符合條件的人員對象，如果不存在則返回 null
   */
  Personnel findByName(String name);

  /**
   * 根據名稱模糊查詢所有符合條件的人員列表.
   *
   * @param name 人員名稱的一部分（不區分大小寫）
   * @return 符合條件的人員列表，如果不存在則返回空列表
   */
  List<Personnel> findAllByNameContainingIgnoreCase(String name);

}
