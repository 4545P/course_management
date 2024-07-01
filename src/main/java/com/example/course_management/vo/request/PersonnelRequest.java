package com.example.course_management.vo.request;

import com.example.course_management.entity.Personnel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * JI.
 * 表示用於接收人員相關操作請求的請求對象。
 * 這個類用於接收客戶端發送的人員相關操作的請求數據。
 */
public class PersonnelRequest {

  public Personnel personnel;

  public Integer id;

  public String name;

  public String password;

  public String email;

  public String role;

  public LocalDate birthday;

  public LocalDateTime registerDate;

  public boolean enable;

  public String verificationCode;

  public LocalDateTime verificationDate;

  public List<Personnel> userList;


  public PersonnelRequest() {

  }

  public Personnel getPersonnel() {
    return personnel;
  }

  public void setPersonnel(Personnel personnel) {
    this.personnel = personnel;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public LocalDateTime getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(LocalDateTime registerDate) {
    this.registerDate = registerDate;
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  public String getVerificationCode() {
    return verificationCode;
  }

  public void setVerificationCode(String verificationCode) {
    this.verificationCode = verificationCode;
  }

  public LocalDateTime getVerificationDate() {
    return verificationDate;
  }

  public void setVerificationDate(LocalDateTime verificationDate) {
    this.verificationDate = verificationDate;
  }

  public List<Personnel> getUserList() {
    return userList;
  }

  public void setUserList(List<Personnel> userList) {
    this.userList = userList;
  }

}
