package com.example.course_management.controller;

import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import com.example.course_management.service.ifs.PersonnelService;
import com.example.course_management.vo.request.PersonnelRequest;
import com.example.course_management.vo.request.StudentRequest;
import com.example.course_management.vo.response.PersonnelResponse;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JI.
 * 控制人員相關操作的 REST API 控制器。
 * 允許跨域請求（CORS）。
 */
@CrossOrigin
@RestController
public class PersonnelController {

  private PersonnelService personnelService;

  @Autowired
  public void setPersonnelService(PersonnelService personnelService) {
    this.personnelService = personnelService;
  }

  /**
   * 根據姓名獲取人員信息.
   *
   * @param personnelRequest 包含姓名的請求對象
   * @return 包含人員信息的Map對象
   */
  @PostMapping("/api/getPersonnel")
  public Map<String, Object> getPersonnel(@RequestBody PersonnelRequest personnelRequest) {
    return personnelService.getPersonnel(personnelRequest.getName());
  }

  /**
   * 根據姓名獲取學生信息.
   *
   * @param studentRequest 包含姓名的學生請求對象
   * @return 包含學生信息的Map對象
   */
  @PostMapping("/api/getStudent")
  public Map<String, Object> getStudent(@RequestBody StudentRequest studentRequest) {
    return personnelService.getStudent(studentRequest.getName());
  }

  /**
   * 新增人員.
   *
   * @param personnel 包含人員信息的請求對象
   * @return 新增人員的響應對象
   */
  @PostMapping("/personnel/add")
  public PersonnelResponse addPersonnel(@RequestBody Personnel personnel) {
    return personnelService.addPersonnel(personnel);
  }

  /**
   * 新增學生.
   *
   * @param student 包含學生信息的請求對象
   * @return 新增學生的響應對象
   */
  @PostMapping("/student/add")
  public PersonnelResponse addStudent(@RequestBody Student student) {
    return personnelService.addStudent(student);
  }

  /**
   * 修改人員信息.
   *
   * @param personnelRequest 包含人員信息的請求對象
   * @return 修改人員信息的響應對象
   */
  @PostMapping("/personnel/update")
  public PersonnelResponse updatePersonnel(@RequestBody PersonnelRequest personnelRequest) {
    return personnelService.updatePersonnel(personnelRequest);
  }

  /**
   * 修改學生信息.
   *
   * @param studentRequest 包含學生信息的請求對象
   * @return 修改學生信息的響應對象
   */
  @PostMapping("/student/update")
  public PersonnelResponse updateStudent(@RequestBody StudentRequest studentRequest) {
    return personnelService.updateStudent(studentRequest);
  }

  /**
   * 登入驗證.
   *
   * @param personnelRequest 包含人員登入信息的請求對象
   * @return 驗證結果的響應對象
   */
  @PostMapping("/login")
  public PersonnelResponse login(@RequestBody PersonnelRequest personnelRequest) {
    return personnelService.isValidPersonnel(personnelRequest.getName(), personnelRequest.getPassword());
  }
}
