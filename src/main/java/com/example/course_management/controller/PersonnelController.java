package com.example.course_management.controller;

import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import com.example.course_management.service.ifs.PersonnelService;
import com.example.course_management.vo.request.PersonnelRequest;
import com.example.course_management.vo.request.StudentRequest;
import com.example.course_management.vo.response.PersonnelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    // 獲取人員
    @PostMapping("/api/getPersonnel")
    public Map<String, Object> getPersonnel(@RequestBody PersonnelRequest personnelRequest) {
        return personnelService.getPersonnel(personnelRequest.getName());
    }

    //獲取學生
    @PostMapping("/api/getStudent")
    public Map<String, Object> getStudent(@RequestBody StudentRequest studentRequest) {
        return personnelService.getStudent(studentRequest.getName());
    }

    //新增人員
    @PostMapping("/personnel/add")
    public PersonnelResponse addPersonnel(@RequestBody Personnel personnel){
        return personnelService.addPersonnel(personnel);
    }

    //新增學生
    @PostMapping("/student/add")
    public PersonnelResponse addStudent(@RequestBody Student student){
        return personnelService.addStudent(student);
    }

    //修改人員
    @PostMapping("/personnel/update")
    public PersonnelResponse updatePersonnel(@RequestBody PersonnelRequest personnelRequest){
        return personnelService.updatePersonnel(personnelRequest);
    }

    //修改學員
    @PostMapping("/student/update")
    public PersonnelResponse updateStudent(@RequestBody StudentRequest studentRequest){
        return personnelService.updateStudent(studentRequest);
    }

    //登入
    @PostMapping("/login")
    public PersonnelResponse login(@RequestBody PersonnelRequest personnelRequest) {
        return personnelService.isValidPersonnel(personnelRequest.getName(), personnelRequest.getPassword());
    }

}
