package com.example.course_management.controller;

import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import com.example.course_management.repository.PersonnelDao;
import com.example.course_management.repository.StudentDao;
import com.example.course_management.service.ifs.VerificationService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
    return sendVerificationCode(email, true);
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
    return sendVerificationCode(email, false);
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
    return verifyIdentity(requestData, true);
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
    return verifyIdentity(requestData, false);
  }

  /**
   * 發送驗證碼的通用方法.
   *
   * @param email      電子郵件地址
   * @param isPersonnel 是否是人員
   * @return 包含成功或失敗信息的響應對象
   */
  private Map<String, Object> sendVerificationCode(String email, boolean isPersonnel) {
    String verificationCode = isPersonnel ? verificationService.sendPersonnelVerificationCode(email) :
          verificationService.sendStudentVerificationCode(email);
    System.out.println(verificationCode); // 可以在這裡處理其他邏輯，例如將驗證碼儲存到暫存中
    Map<String, Object> response = new HashMap<>();
    response.put("success", true);
    return response;
  }

  /**
   * 驗證身份的通用方法.
   *
   * @param requestData 包含ID和驗證碼的請求體
   * @param isPersonnel 是否是人員
   * @return 包含驗證結果的響應對象
   */
  private Map<String, Object> verifyIdentity(Map<String, Object> requestData, boolean isPersonnel) {
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
        response.put("success", true);
      } else {
        response.put("success", false);
      }
    } else {
      response.put("success", false);
    }
    return response;
  }

}
