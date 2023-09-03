package com.example.course_management.service.ifs;

import com.example.course_management.entity.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

public interface VerificationService {

    // 發送人員驗證碼
    public String sendPersonnelVerificationCode(String email);

    // 發送學生驗證碼
    public String sendStudentVerificationCode(String email);

    // 生成驗證碼
    public String generateVerificationCode();

    // 使用JavaMailSender接口發送郵件
    public void sendEmail(String toEmail, String subject, String body);

}
