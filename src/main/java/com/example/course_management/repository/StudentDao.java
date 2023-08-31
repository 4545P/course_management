package com.example.course_management.repository;

import com.example.course_management.entity.Personnel;
import com.example.course_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {

   public Student findByEmail(String email);

    public Student findByName(String name);

    public List<Student> findAllByNameContainingIgnoreCase(String name);
}
