package com.example.course_management.service.ifs;

import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import com.example.course_management.vo.request.PersonnelRequest;
import com.example.course_management.vo.request.StudentRequest;
import com.example.course_management.vo.response.PersonnelResponse;
import java.util.Map;

/**
 * JI.
 * 人員服務接口，定義了人員相關操作的方法。
 */
public interface PersonnelService {

  Map<String, Object> getPersonnel(String name);

  Map<String, Object> getStudent(String name);

  PersonnelResponse addPersonnel(Personnel personnel);

  PersonnelResponse addStudent(Student student);

  PersonnelResponse updatePersonnel(PersonnelRequest personnelRequest);

  PersonnelResponse updateStudent(StudentRequest studentRequest);

  PersonnelResponse deletePersonnel(Integer personnelId);

  PersonnelResponse deleteStudent(Integer studentId);

  PersonnelResponse isValidPersonnel(String name, String password);

}
