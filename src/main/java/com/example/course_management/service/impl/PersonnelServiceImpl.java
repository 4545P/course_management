package com.example.course_management.service.impl;

import com.example.course_management.entity.CourseSelection;
import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import com.example.course_management.repository.CourseSelectionDao;
import com.example.course_management.repository.PersonnelDao;
import com.example.course_management.repository.StudentDao;
import com.example.course_management.service.ifs.PersonnelService;
import com.example.course_management.vo.request.PersonnelRequest;
import com.example.course_management.vo.request.StudentRequest;
import com.example.course_management.vo.response.PersonnelResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * JI.
 * 人員服務實現類，提供人員相關業務邏輯的具體實現。
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {

  private PersonnelDao personnelDao;

  private StudentDao studentDao;

  private CourseSelectionDao courseSelectionDao;

  @Autowired
  public void setPersonnelDao(PersonnelDao personnelDao) {
    this.personnelDao = personnelDao;
  }

  @Autowired
  public void setStudentDao(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Autowired
  public void setCourseSelectionDao(CourseSelectionDao courseSelectionDao) {
    this.courseSelectionDao = courseSelectionDao;
  }

  @Override
  public Map<String, Object> getPersonnel(@RequestParam(required = false) String name) {
    List<Personnel> personnelList;
    long totalElements;
    if (name != null && !name.isEmpty()) {
      personnelList = personnelDao.findAllByNameContainingIgnoreCase(name);
    } else {
      // 如果沒有參數則返回全部
      personnelList = personnelDao.findAll();
    }
    // 計算總數
    totalElements = personnelList.size();
    List<Map<String, Object>> resultList = getMapPersonnel(personnelList);
    Map<String, Object> result = new HashMap<>();
    result.put("list", resultList);
    result.put("total", totalElements);
    return result;
  }

  @Override
  public Map<String, Object> getStudent(@RequestParam(required = false) String name) {
    List<Student> studentList;
    long totalElements;
    if (name != null && !name.isEmpty()) {
      studentList = studentDao.findAllByNameContainingIgnoreCase(name);
    } else {
      // 如果沒有參數則返回全部
      studentList = studentDao.findAll();
    }
    // 計算總數
    totalElements = studentList.size();
    List<Map<String, Object>> resultList = getMapStudent(studentList);
    Map<String, Object> result = new HashMap<>();
    result.put("list", resultList);
    result.put("total", totalElements);
    return result;
  }

  private static List<Map<String, Object>> getMapPersonnel(List<Personnel> personnelList) {
    List<Map<String, Object>> responses = new ArrayList<>();
    for (Personnel personnel : personnelList) {
      Map<String, Object> response = new HashMap<>();
      response.put("id", personnel.getId());
      response.put("name", personnel.getName());
      response.put("email", personnel.getEmail());
      response.put("role", personnel.getRole());
      response.put("enable", personnel.getEnable());
      response.put("birthday", personnel.getBirthday());
      responses.add(response);
    }
    return responses;
  }

  private static List<Map<String, Object>> getMapStudent(List<Student> studentList) {
    List<Map<String, Object>> responses = new ArrayList<>();
    for (Student student : studentList) {
      Map<String, Object> response = new HashMap<>();
      response.put("id", student.getStudentId());
      response.put("name", student.getName());
      response.put("email", student.getEmail());
      response.put("support", student.isSupport());
      response.put("enable", student.isEnable());
      response.put("birthday", student.getBirthday());
      responses.add(response);
    }
    return responses;
  }



  @Override
  public PersonnelResponse addPersonnel(Personnel personnel) {
    if (personnel.getName().isBlank()
        || personnel.getPassword().isBlank()
        || personnel.getEmail().isBlank()
        || personnel.getRole().isBlank()) {
      return new PersonnelResponse(Collections.singletonList(personnel), "新增人員失敗");
    } else {
      LocalDateTime registerTime = LocalDateTime.now();
      personnel.setRegisterDate(registerTime);
      personnel.setEnable(false);
      personnelDao.save(personnel);
      return new PersonnelResponse(Collections.singletonList(personnel), "新增人員成功");
    }
  }


  @Override
  public PersonnelResponse addStudent(Student student) {
    if (student.getName().isBlank()
        || student.getPassword().isBlank()
        || student.getEmail().isBlank()) {
      return new PersonnelResponse("新增學員失敗", Collections.singletonList(student));
    }
    LocalDateTime registerTime = LocalDateTime.now();
    student.setRegisterDate(registerTime);
    student.setEnable(false);
    LocalDate birthday = student.getBirthday();
    if (birthday != null) {
      int age = calculateAge(birthday, LocalDate.now());
      student.setSupport(age < 29);
    }
    try {
      student = studentDao.save(student);
      // 新增相應的選課表數據
      CourseSelection courseSelection = new CourseSelection();
      // 設置學號
      courseSelection.setStudentId(student.getStudentId());
      // 設置姓名
      courseSelection.setName(student.getName());
      courseSelectionDao.save(courseSelection);
      return new PersonnelResponse("新增學員成功", Collections.singletonList(student));
    } catch (Exception e) {
      // 處理資料庫保存異常
      return new PersonnelResponse("新增學員失敗", Collections.singletonList(student));
    }
  }

  @Override
  public PersonnelResponse updatePersonnel(PersonnelRequest personnelRequest) {
    Personnel user = personnelRequest.getPersonnel();
    Integer userId = user.getId();
    Optional<Personnel> existingPersonnelOptional = personnelDao.findById(userId);
    if (existingPersonnelOptional.isEmpty()) {
      return new PersonnelResponse("更新人員失敗，找不到人員");
    }
    Personnel existingUser = existingPersonnelOptional.get();
    if (StringUtils.hasText(user.getName())) {
      existingUser.setName(user.getName());
    }
    if (StringUtils.hasText(user.getPassword())) {
      existingUser.setPassword(user.getPassword());
    }
    if (StringUtils.hasText(user.getEmail())) {
      existingUser.setEmail(user.getEmail());
    }
    if (StringUtils.hasText(user.getRole())) {
      existingUser.setRole(user.getRole());
    }
    if (user.getBirthday() != null) {
      existingUser.setBirthday(user.getBirthday());
    }
    personnelDao.save(existingUser);
    return new PersonnelResponse("Update successful");
  }


  @Override
  public PersonnelResponse updateStudent(StudentRequest studentRequest) {
    Student student = studentRequest.getStudent();
    Integer studentId = student.getStudentId();
    Optional<Student> existingStudentOptional = studentDao.findById(studentId);
    if (existingStudentOptional.isEmpty()) {
      return new PersonnelResponse("更新學員失敗，找不到學員");
    }
    Student existingStudent = existingStudentOptional.get();
    if (StringUtils.hasText(student.getName())) {
      existingStudent.setName(student.getName());
    }
    if (StringUtils.hasText(student.getEmail())) {
      existingStudent.setEmail(student.getEmail());
    }
    if (student.getBirthday() != null) {
      existingStudent.setBirthday(student.getBirthday());
    }
    studentDao.save(existingStudent);
    return new PersonnelResponse("更新學員成功");
  }

  @Override
  public PersonnelResponse deletePersonnel(Integer personnelId) {
    return null;
  }

  @Override
  public PersonnelResponse deleteStudent(Integer studentId) {
    return null;
  }

  @Override
  public PersonnelResponse isValidPersonnel(String name, String password) {
    Personnel userPersonnel = personnelDao.findByName(name);
    // 如果人員有紀錄則比對密碼
    if (userPersonnel != null) {
      if (password.equals(userPersonnel.getPassword())) {
        return new PersonnelResponse(userPersonnel.getRole(),
          userPersonnel.getId(),
          userPersonnel.getName(),
          userPersonnel.getPassword(),
          userPersonnel.getEnable(),
          userPersonnel.getEmail());
      }
    }
    // 如果不是人員則找學員
    Student userStudent = studentDao.findByName(name);
    // 如果學員有紀錄則比對密碼
    if (userStudent != null) {
      if (password.equals(userStudent.getPassword())) {
        return new PersonnelResponse("student",
          userStudent.getStudentId(),
          userStudent.getName(),
          userStudent.getPassword(),
          userStudent.isEnable(),
          userStudent.getEmail());
      }
    }
    return new PersonnelResponse("登入失敗");
  }


  //檢查補助資格
  private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
    return currentDate.getYear() - birthDate.getYear();
  }
}
