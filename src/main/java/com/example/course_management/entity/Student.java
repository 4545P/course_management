package com.example.course_management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * JI.
 * 學生的資料模型。
 */
@Component
@Entity
@Table(name = "Student")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student_id")
  private Integer studentId;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "register_date")
  private LocalDateTime registerDate;

  @Column(name = "enable")
  private boolean enable;

  @Column(name = "support")
  private boolean support;

  @Column(name = "verification_code")
  private String verificationCode;

  @Column(name = "verification_date")
  private LocalDateTime verificationDate;

  public Student() {

  }

  /**
   * 建立學生的建構子.
   *
   * @param studentId 學生ID
   * @param name 學生姓名
   * @param password 密碼
   * @param email 電子郵件地址
   * @param birthday 生日
   * @param registerDate 註冊日期
   * @param enable 是否啟用
   * @param support 是否支持
   * @param verificationCode 驗證碼
   * @param verificationDate 驗證日期
   */
  public Student(Integer studentId, String name, String password, String email, LocalDate birthday, LocalDateTime registerDate,
                 boolean enable, boolean support, String verificationCode, LocalDateTime verificationDate) {
    this.studentId = studentId;
    this.name = name;
    this.password = password;
    this.email = email;
    this.birthday = birthday;
    this.registerDate = registerDate;
    this.enable = enable;
    this.support = support;
    this.verificationCode = verificationCode;
    this.verificationDate = verificationDate;
  }

  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
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

  public boolean isSupport() {
    return support;
  }

  public void setSupport(boolean support) {
    this.support = support;
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
}
