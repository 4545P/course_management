package com.example.course_management.vo.request;

/**
 * JI.
 * 表示用於驗證操作的請求對象。
 * 這個類用於接收客戶端發送的驗證相關操作的請求數據。
 */
public class VerifyRequest {

  String email;

  String code;

  public VerifyRequest() {
    super();
  }

  /**
   * 初始化驗證請求對象.
   *
   * @param email 電子郵件地址
   * @param code 驗證碼
   */
  public VerifyRequest(String email, String code) {
    super();
    this.email = email;
    this.code = code;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
