package com.example.course_management.controller;

import com.example.course_management.repository.PersonnelDao;
import com.example.course_management.repository.StudentDao;
import com.example.course_management.service.ifs.VerificationService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JI.
 * 控制驗證相關操作的 REST API 控制器。
 * 允許跨域請求（CORS）。
 */
@CrossOrigin
@RestController
public class VerificationController {

  private VerificationService verificationService;

  public PersonnelDao personnelDao;
  
  public StudentDao studentDao;


  @Autowired
  public void setVerificationService(VerificationService verificationService) {
    this.verificationService = verificationService;
  }

  @Autowired
  public void setPersonnelDao(PersonnelDao personnelDao) {
    this.personnelDao = personnelDao;
  }

  @Autowired
  public void setStudentDao(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  /**
   * 發送人員驗證碼.
   *
   * @param email 電子郵件地址
   * @return 包含成功或失敗信息的響應對象
   */
  @PostMapping("/send-personnel-verification-code")
  @ResponseBody
  public Map<String, Object> sendPersonnelVerificationCode(@RequestParam("email") String email) {
    return verificationService.sendVerificationCode(email, true);
  }

  /**
   * 發送學生驗證碼.
   *
   * @param email 電子郵件地址
   * @return 包含成功或失敗信息的響應對象
   */
  @PostMapping("/send-student-verification-code")
  @ResponseBody
  public Map<String, Object> sendStudentVerificationCode(@RequestParam("email") String email) {
    return verificationService.sendVerificationCode(email, false);
  }

  /**
   * 驗證人員.
   *
   * @param requestData 包含ID和驗證碼的請求體
   * @return 包含驗證結果的響應對象
   */
  @PostMapping("/verify-personnel")
  @ResponseBody
  public Map<String, Object> verifyPersonnel(@RequestBody Map<String, Object> requestData) {
    return verificationService.verifyIdentity(requestData, true);
  }

  /**
   * 驗證學生.
   *
   * @param requestData 包含學生ID和驗證碼的請求體
   * @return 包含驗證結果的響應對象
   */
  @PostMapping("/verify-student")
  @ResponseBody
  public Map<String, Object> verifyStudent(@RequestBody Map<String, Object> requestData) {
    return verificationService.verifyIdentity(requestData, false);
  }
}
