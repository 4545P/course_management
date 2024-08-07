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
 * 人員的資料模型。
 */
@Component
@Entity
@Table(name = "Personnel")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Personnel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "role")
  private String role;

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "register_date")
  private LocalDateTime registerDate;

  @Column(name = "enable")
  private boolean enable;

  @Column(name = "verification_code")
  private String verificationCode;

  @Column(name = "verification_date")
  private LocalDateTime verificationDate;

  public Personnel() {

  }

  /**
   * 建立人員的建構子.
   *
   * @param id 人員ID
   * @param name 人員姓名
   * @param password 密碼
   * @param email 電子郵件地址
   * @param role 角色
   * @param birthday 生日
   * @param registerDate 註冊日期
   * @param enable 是否啟用
   * @param verificationCode 驗證碼
   * @param verificationDate 驗證日期
   */
  public Personnel(Integer id, String name, String password, String email, String role, LocalDate birthday, LocalDateTime registerDate,
                   boolean enable, String verificationCode, LocalDateTime verificationDate) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.email = email;
    this.role = role;
    this.birthday = birthday;
    this.registerDate = registerDate;
    this.enable = enable;
    this.verificationCode = verificationCode;
    this.verificationDate = verificationDate;
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

  public boolean getEnable() {
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
}
