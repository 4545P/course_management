package com.example.course_management.service.impl;

import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import com.example.course_management.repository.PersonnelDao;
import com.example.course_management.repository.StudentDao;
import com.example.course_management.service.ifs.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PersonnelDao personnelDao;

    @Autowired
    private StudentDao studentDao;

    // 生成并发送验证码
    @Override
    public String sendPersonnelVerificationCode(String email) {
        // 生成随机验证码
        String verificationCode = generateVerificationCode();

        // 发送带有验证码的电子邮件
        String subject = "Email Verification";
        String body = "驗證碼: " + verificationCode;

        LocalDateTime verificationDate = LocalDateTime.now();

        Personnel personnel = personnelDao.findByEmail(email);
        if (personnel != null) {
            personnel.setVerificationCode(verificationCode);
            personnel.setVerificationDate(verificationDate);
            personnelDao.save(personnel);

            // 在三分鐘後執行一個任務來將驗證碼相關字段設置為null
            scheduleVerificationCleanupTask(email, verificationDate.plusMinutes(3));
        }

        //調用發送郵件方法
        sendEmail(email, "Monthly Billing Statement", body);

        // 这里可以添加保存验证码等相关逻辑
        return verificationCode;
    }

    @Override
    public String sendStudentVerificationCode(String email) {
        // 生成随机验证码
        String verificationCode = generateVerificationCode();

        // 发送带有验证码的电子邮件
        String subject = "Email Verification";
        String body = "驗證碼: " + verificationCode;

        LocalDateTime verificationDate = LocalDateTime.now();

        Student student = studentDao.findByEmail(email);
        if (student != null) {
            student.setVerificationCode(verificationCode);
            student.setVerificationDate(verificationDate);
            studentDao.save(student);

            // 在三分鐘後執行一個任務來將驗證碼相關字段設置為null
            scheduleVerificationCleanupTask(email, verificationDate.plusMinutes(3));
        }

        //調用發送郵件方法
        sendEmail(email, "Monthly Billing Statement", body);

        // 这里可以添加保存验证码等相关逻辑
        return verificationCode;
    }

    // 生成随机验证码的逻辑
    @Override
    public String generateVerificationCode() {
        // 生成一个六位数的随机数
        int code = 100000 + new Random().nextInt(900000);
        return String.valueOf(code);
    }

    // 定時任務，用於清除驗證碼相關字段
    private void scheduleVerificationCleanupTask(String email, LocalDateTime cleanupTime) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() -> {
            Personnel personnel = personnelDao.findByEmail(email);
            if (personnel != null && personnel.getVerificationDate().isBefore(cleanupTime)) {
                personnel.setVerificationCode(null);
                personnel.setVerificationDate(null);
                personnelDao.save(personnel);
            }
        }, Duration.between(LocalDateTime.now(), cleanupTime).toMillis(), TimeUnit.MILLISECONDS);
    }

    // 使用JavaMailSender接口發送郵件
    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        try {
            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setFrom("sr123692010@gmail.com");
                messageHelper.setTo(toEmail);
                messageHelper.setSubject(subject);

                // 設置郵件内容為 HTML 格式
                messageHelper.setText(body, true);
            };

            mailSender.send(preparator);

            System.out.println("Email sent successfully to: " + toEmail);
        } catch (MailException e) {
            System.out.println("Failed to send email to: " + toEmail);
            e.printStackTrace();
        }
    }
}
