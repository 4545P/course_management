package com.example.course_management.service.ifs;

import java.util.Map;

/**
 * JI.
 * 驗證服務接口，定義了發送驗證碼和郵件等操作的方法。
 */
public interface VerificationService {

  // 發送人員驗證碼
  String sendPersonnelVerificationCode(String email);

  // 發送學生驗證碼
  String sendStudentVerificationCode(String email);

  // 生成驗證碼
  String generateVerificationCode();

  // 使用JavaMailSender接口發送郵件
  void sendEmail(String toEmail, String subject, String body);

  // 發送驗證碼
  Map<String, Object> sendVerificationCode(String email, boolean isPersonnel);

  // 驗證身份
  Map<String, Object> verifyIdentity(Map<String, Object> requestData, boolean isPersonnel);
}
