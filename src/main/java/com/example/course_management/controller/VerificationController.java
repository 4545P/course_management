package com.example.course_management.controller;

import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import com.example.course_management.repository.PersonnelDao;
import com.example.course_management.repository.StudentDao;
import com.example.course_management.service.ifs.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



@CrossOrigin
@RestController
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @Autowired
    public PersonnelDao personnelDao;

    @Autowired
    public StudentDao studentDao;


    // 發送人員驗證碼
    @PostMapping("/send-personnel-verification-code")
    @ResponseBody
    public Map<String, Object> sendPersonnelVerificationCode(@RequestParam("email") String email) {
        // 調用 VerificationService 的方法發送驗證碼
        String verificationCode = verificationService.sendPersonnelVerificationCode(email);

        // 你可以在這裡處理其他邏輯，例如將驗證碼儲存到暫存中
        System.out.println(verificationCode);

        // 返回結果給前端，表示驗證碼已發送
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

    //發送學生驗證碼
    @PostMapping("/send-student-verification-code")
    @ResponseBody
    public Map<String, Object> sendStudentVerificationCode(@RequestParam("email") String email) {
        // 調用 VerificationService 的方法發送驗證碼
        String verificationCode = verificationService.sendStudentVerificationCode(email);

        // 你可以在這裡處理其他邏輯，例如將驗證碼儲存到暫存中
        System.out.println(verificationCode);

        // 返回結果給前端，表示驗證碼已發送
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

    //驗證人員
    @PostMapping("/verify-personnel")
    @ResponseBody
    public Map<String, Object> verifyPersonnel(@RequestBody Map<String, Object> requestData) {
        // 從請求主體中讀取ID和驗證碼
        Integer id = (Integer) requestData.get("id");
        String code = (String) requestData.get("code");

        // 驗證驗證碼的邏輯
        Map<String, Object> response = new HashMap<>();

        Optional<Personnel> optionalPersonnel = personnelDao.findById(id);

        if (optionalPersonnel.isPresent()) {
            Personnel personnel = optionalPersonnel.get();
            String storedVerificationCode = personnel.getVerificationCode();

            if (storedVerificationCode != null && storedVerificationCode.equals(code)) {
                // 驗證碼匹配成功
                personnel.setEnable(true);
                personnelDao.save(personnel); // 更新資料庫中的資料
                response.put("success", true);
            } else {
                // 驗證碼匹配失敗
                response.put("success", false);
            }
        } else {
            // 找不到對應的人員
            response.put("success", false);
        }

        return response;
    }

    //驗證學生
    @PostMapping("/verify-student")
    @ResponseBody
    public Map<String, Object> verifyStudent(@RequestBody Map<String, Object> requestData) {
        // 從請求主體中讀取ID和驗證碼
        Integer studentId = (Integer) requestData.get("studentId");
        String code = (String) requestData.get("code");

        // 驗證驗證碼的邏輯
        Map<String, Object> response = new HashMap<>();
        Optional<Student> optionalStudent = studentDao.findById(studentId);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            String storedVerificationCode = student.getVerificationCode();

            if (storedVerificationCode != null && storedVerificationCode.equals(code)) {
                // 驗證碼匹配成功
                student.setEnable(true);
                studentDao.save(student); // 更新資料庫中的資料
                response.put("success", true);
            } else {
                // 驗證碼匹配失敗
                response.put("success", false);
            }
        } else {
            // 找不到對應的學生
            response.put("success", false);
        }

        return response;
    }
}
