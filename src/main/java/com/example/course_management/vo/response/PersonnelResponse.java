package com.example.course_management.vo.response;

import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 * JI.
 * 表示人員的響應對象，用於返回人員相關信息。
 * 屬性值為空（null）的屬性將不包含在JSON序列化中。
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonnelResponse {

  private List<Personnel> personnelList;

  private List<Student> studentList;

  private String message;

  private String name;

  private String password;

  private String role;

  private Integer id;

  private Boolean enable;

  private String email;

  public PersonnelResponse(List<Personnel> personnelList, String message) {
    this.personnelList = personnelList;
    this.message = message;
  }

  public PersonnelResponse(String message, List<Student> studentList) {
    this.studentList = studentList;
    this.message = message;
  }

  public PersonnelResponse(String message) {
    this.message = message;
  }

  /**
   * 創建人員響應對象的構造函數.
   *
   * @param role     人員角色
   * @param id       人員ID
   * @param name     人員姓名
   * @param password 人員密碼
   * @param enable   是否啟用
   * @param email    人員電子郵件地址
   */
  public PersonnelResponse(String role, Integer id, String name, String password, Boolean enable, String email) {
    this.role = role;
    this.id = id;
    this.name = name;
    this.password = password;
    this.enable = enable;
    this.email = email;
  }

  public List<Personnel> getPersonnelList() {
    return personnelList;
  }

  public void setPersonnelList(List<Personnel> personnelList) {
    this.personnelList = personnelList;
  }

  public List<Student> getStudentList() {
    return studentList;
  }

  public void setStudentList(List<Student> studentList) {
    this.studentList = studentList;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
