package com.example.course_management.service.impl;

import com.example.course_management.constants.WidgetApiRtnCode;
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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * JI.
 * 人員服務實現類，提供人員相關業務邏輯的具體實現。
 * @author blue
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
      return new PersonnelResponse(Collections.singletonList(personnel), WidgetApiRtnCode.FAILED_TO_ADD_NEW_STAFF.getMessage());
    } else {
      // 密碼加鹽加密
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String encodedPassword = passwordEncoder.encode(personnel.getPassword());
      // 設置加密後的密碼
      personnel.setPassword(encodedPassword);

      LocalDateTime registerTime = LocalDateTime.now();
      personnel.setRegisterDate(registerTime);
      personnel.setEnable(false);
      personnelDao.save(personnel);
      return new PersonnelResponse(Collections.singletonList(personnel), WidgetApiRtnCode.ADDED_FACULTY_SUCCESSFULLY.getMessage());
    }
  }

  @Override
  public PersonnelResponse addStudent(Student student) {
    if (student.getName().isBlank()
      || student.getPassword().isBlank()
      || student.getEmail().isBlank()) {
      return new PersonnelResponse(WidgetApiRtnCode.PARANETER_REQUIRE.getMessage(), Collections.singletonList(student));
    }

    // 密碼加鹽加密
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(student.getPassword());
    // 設置加密後的密碼
    student.setPassword(encodedPassword);

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
      courseSelection.setStudentId(student.getStudentId());
      courseSelection.setName(student.getName());
      courseSelectionDao.save(courseSelection);

      return new PersonnelResponse(WidgetApiRtnCode.ADDED_FACULTY_SUCCESSFULLY.getMessage(), Collections.singletonList(student));
    } catch (Exception e) {
      // 處理資料庫保存異常
      return new PersonnelResponse(WidgetApiRtnCode.FAILED_TO_ADD_NEW_STAFF.getMessage(), Collections.singletonList(student));
    }
  }

  @Override
  public PersonnelResponse updatePersonnel(PersonnelRequest personnelRequest) {
    Personnel user = personnelRequest.getPersonnel();
    Integer userId = user.getId();
    Optional<Personnel> existingPersonnelOptional = personnelDao.findById(userId);
    if (existingPersonnelOptional.isEmpty()) {
      return new PersonnelResponse(WidgetApiRtnCode.UPDATE_STAFF_FAILED.getMessage());
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
    return new PersonnelResponse(WidgetApiRtnCode.UPDATE_STAFF_SUCCESSFUL.getMessage());
  }


  @Override
  public PersonnelResponse updateStudent(StudentRequest studentRequest) {
    Student student = studentRequest.getStudent();
    Integer studentId = student.getStudentId();
    Optional<Student> existingStudentOptional = studentDao.findById(studentId);
    if (existingStudentOptional.isEmpty()) {
      return new PersonnelResponse(WidgetApiRtnCode.UPDATE_STAFF_FAILED.getMessage());
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
    return new PersonnelResponse(WidgetApiRtnCode.UPDATE_STAFF_SUCCESSFUL.getMessage());
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
    // 查詢所有相同名稱的 Personnel
    List<Personnel> personnelList = personnelDao.findByName(name);

    // 如果找到對應的 Personnel，則比對密碼
    for (Personnel userPersonnel : personnelList) {
      // 使用 BCrypt 來比對加鹽密碼
      if (BCrypt.checkpw(password, userPersonnel.getPassword())) {
        return new PersonnelResponse(
          userPersonnel.getRole(),
          userPersonnel.getId(),
          userPersonnel.getName(),
          userPersonnel.getPassword(),
          userPersonnel.getEnable(),
          userPersonnel.getEmail()
        );
      }
    }

    // 查詢所有相同名稱的 Student
    List<Student> studentList = studentDao.findByName(name);

    // 如果找到對應的 Student，則比對密碼
    for (Student userStudent : studentList) {
      // 使用 BCrypt 來比對加鹽密碼
      if (BCrypt.checkpw(password, userStudent.getPassword())) {
        return new PersonnelResponse(
          "student",
          userStudent.getStudentId(),
          userStudent.getName(),
          userStudent.getPassword(),
          userStudent.isEnable(),
          userStudent.getEmail()
        );
      }
    }

    // 如果都找不到匹配的使用者或密碼不正確
    return new PersonnelResponse(WidgetApiRtnCode.FAILED.getMessage());
  }


  //檢查補助資格
  private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
    return currentDate.getYear() - birthDate.getYear();
  }
}
