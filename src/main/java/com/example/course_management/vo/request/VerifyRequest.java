package com.example.course_management.vo.request;

public class VerifyRequest {

    String email;

    String code;

    public VerifyRequest() {
        super();
    }

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
