package com.example.course_management.config;

import com.example.course_management.entity.Personnel;
import com.example.course_management.repository.PersonnelDao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * JI.
 * 管理員初始化類。
 */
@Component
public class AdminInitialization {

  private final PersonnelDao personnelDao;

  @Autowired
  public AdminInitialization(PersonnelDao personnelDao) {
    this.personnelDao = personnelDao;
  }

  /**
   * 在應用程式啟動完成後，如果系統中不存在名稱為 "admin" 的人員，則建立超級管理員.
   * 使用 {@link org.springframework.context.event.EventListener}
   * 監聽 {@link org.springframework.boot.context.event.ApplicationReadyEvent} 事件，
   * 確保應用程式已準備就緒後執行初始化。
   * 如果系統中不存在名稱為 "admin" 的人員，則創建一個具有預設值的超級管理員帳號，包括姓名、密碼、電子郵件、生日、角色、啟用狀態和註冊日期。
   * 使用 {@code personnelDao} 儲存新建的管理員帳號。
   */
  @EventListener(value = ApplicationReadyEvent.class)
  public void initializeAdmin() {
    if (!personnelDao.existsByName("admin")) {
      Personnel admin = new Personnel();
      admin.setName("admin");
      admin.setPassword("admin");
      admin.setEmail("admin");
      admin.setBirthday(LocalDate.now());
      admin.setRole("admin");
      admin.setEnable(true);
      admin.setRegisterDate(LocalDateTime.now());

      personnelDao.save(admin);
    }
  }
}


