package com.example.course_management.config;

import com.example.course_management.entity.Personnel;
import com.example.course_management.repository.PersonnelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class AdminInitialization {

    private final PersonnelDao personnelDao;

    @Autowired
    public AdminInitialization(PersonnelDao personnelDao) {
        this.personnelDao = personnelDao;
    }

    //預設建立超級管理員
    @EventListener(value = ApplicationReadyEvent.class)
    public void initializeAdmin() {
        // Check if the admin already exists
        if (!personnelDao.existsByName("admin")) {
            // Create the admin user and save it to the database
            Personnel admin = new Personnel();
            admin.setName("admin");
            admin.setPassword("admin");
            admin.setEmail("admin");
            admin.setBirthday(LocalDate.now());
            admin.setRole("admin");
            admin.setEnable(true);
            admin.setRegisterDate(LocalDateTime.now());

            personnelDao.save(admin);
        }
    }
}


