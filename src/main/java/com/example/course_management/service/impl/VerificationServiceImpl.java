package com.example.course_management.service.impl;

import com.example.course_management.constants.WidgetApiRtnCode;
import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import com.example.course_management.repository.PersonnelDao;
import com.example.course_management.repository.StudentDao;
import com.example.course_management.service.ifs.VerificationService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * JI.
 * 驗證實現類，提供驗證邏輯的具體實現。
 */
@Service
public class VerificationServiceImpl implements VerificationService {

  private JavaMailSender mailSender;

  private PersonnelDao personnelDao;

  private StudentDao studentDao;

  @Autowired
  public void setMailSender(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Autowired
  public void setPersonnelDao(PersonnelDao personnelDao) {
    this.personnelDao = personnelDao;
  }

  @Autowired
  public void setStudentDao(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public Map<String, Object> sendVerificationCode(String email, boolean isPersonnel) {
    String verificationCode = isPersonnel ? sendPersonnelVerificationCode(email) :
        sendStudentVerificationCode(email);
    // 可以在這裡處理其他邏輯，例如將驗證碼儲存到暫存中
    System.out.println(verificationCode);
    Map<String, Object> response = new HashMap<>();
    response.put(WidgetApiRtnCode.SUCCESSFUL.getMessage(), true);
    return response;
  }

  @Override
  public Map<String, Object> verifyIdentity(Map<String, Object> requestData, boolean isPersonnel) {
    String idKey = isPersonnel ? "id" : "studentId";
    Integer id = (Integer) requestData.get(idKey);
    String code = (String) requestData.get("code");
    Map<String, Object> response = new HashMap<>();
    Optional<?> optionalIdentity = isPersonnel ? personnelDao.findById(id) : studentDao.findById(id);
    if (optionalIdentity.isPresent()) {
      Object identity = optionalIdentity.get();
      String storedVerificationCode = isPersonnel ? ((Personnel) identity).getVerificationCode() :
          ((Student) identity).getVerificationCode();
      if (storedVerificationCode != null && storedVerificationCode.equals(code)) {
        if (isPersonnel) {
          ((Personnel) identity).setEnable(true);
          personnelDao.save((Personnel) identity);
        } else {
          ((Student) identity).setEnable(true);
          studentDao.save((Student) identity);
        }
        response.put(WidgetApiRtnCode.SUCCESSFUL.getMessage(), true);
      } else {
        response.put(WidgetApiRtnCode.FAILED.getMessage(), false);
      }
    } else {
      response.put(WidgetApiRtnCode.FAILED.getMessage(), false);
    }
    return response;
  }

  // 生成並發送驗證碼
  @Override
  public String sendPersonnelVerificationCode(String email) {
    // 隨機生成驗證碼
    String verificationCode = generateVerificationCode();
    // 發送帶有驗證碼的郵件
    String body = "驗證碼: " + verificationCode;
    LocalDateTime verificationDate = LocalDateTime.now();
    Personnel personnel = personnelDao.findByEmail(email);
    if (personnel != null) {
      personnel.setVerificationCode(verificationCode);
      personnel.setVerificationDate(verificationDate);
      personnelDao.save(personnel);
      // 在三分鐘後執行一個任務來將驗證碼相關字段設置為null
      scheduleVerificationCleanupTask(email, verificationDate.plusMinutes(3));
    }
    //調用發送郵件方法
    sendEmail(email, "Monthly Billing Statement", body);
    return verificationCode;
  }

  @Override
  public String sendStudentVerificationCode(String email) {
    // 隨機生成驗證碼
    String verificationCode = generateVerificationCode();
    // 發送帶有驗證碼的郵件
    String body = "驗證碼: " + verificationCode;
    LocalDateTime verificationDate = LocalDateTime.now();
    Student student = studentDao.findByEmail(email);
    if (student != null) {
      student.setVerificationCode(verificationCode);
      student.setVerificationDate(verificationDate);
      studentDao.save(student);
      // 在三分鐘後執行一個任務來將驗證碼相關字段設置為null
      scheduleVerificationCleanupTask(email, verificationDate.plusMinutes(3));
    }
    //調用發送郵件方法
    sendEmail(email, "Monthly Billing Statement", body);
    return verificationCode;
  }

  @Override
  public String generateVerificationCode() {
    // 生成隨機六位數驗證碼
    int code = 100000 + new Random().nextInt(900000);
    return String.valueOf(code);
  }

  // 定時任務，用於清除驗證碼相關字段
  private void scheduleVerificationCleanupTask(String email, LocalDateTime cleanupTime) {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    executorService.schedule(() -> {
      Personnel personnel = personnelDao.findByEmail(email);
      if (personnel != null && personnel.getVerificationDate().isBefore(cleanupTime)) {
        personnel.setVerificationCode(null);
        personnel.setVerificationDate(null);
        personnelDao.save(personnel);
      }
    }, Duration.between(LocalDateTime.now(), cleanupTime).toMillis(), TimeUnit.MILLISECONDS);
  }

  // 使用JavaMailSender接口發送郵件
  @Override
  public void sendEmail(String toEmail, String subject, String body) {
    try {
      MimeMessagePreparator preparator = mimeMessage -> {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom("sr123692010@gmail.com");
        messageHelper.setTo(toEmail);
        messageHelper.setSubject(subject);
        // 設置郵件内容為 HTML 格式
        messageHelper.setText(body, true);
      };
      mailSender.send(preparator);
      System.out.println(WidgetApiRtnCode.SUCCESSFUL.getCode() + toEmail);
    } catch (MailException e) {
      System.out.println(WidgetApiRtnCode.FAILED.getCode() + toEmail);
      e.printStackTrace();
    }
  }
}
