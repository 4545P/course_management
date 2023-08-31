package com.example.course_management.service.ifs;

import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import com.example.course_management.vo.request.PersonnelRequest;
import com.example.course_management.vo.request.StudentRequest;
import com.example.course_management.vo.response.PersonnelResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PersonnelService {

    public Map<String, Object> getPersonnel(String name);

    public Map<String,Object> getStudent(String name);

    public PersonnelResponse addPersonnel(Personnel personnel);

    public PersonnelResponse addStudent(Student student);

    public PersonnelResponse updatePersonnel(PersonnelRequest personnelRequest);

    public PersonnelResponse updateStudent(StudentRequest studentRequest);

    public PersonnelResponse deletePersonnel(Integer personnelId);

    public PersonnelResponse deleteStudent(Integer studentId);

    public PersonnelResponse isValidPersonnel(String name, String password);

}
