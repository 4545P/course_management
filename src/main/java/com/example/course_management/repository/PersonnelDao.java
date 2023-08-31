package com.example.course_management.repository;

import com.example.course_management.entity.Personnel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PersonnelDao extends JpaRepository<Personnel, Integer> {

    public boolean existsByName(String name);

    public Personnel findByEmail(String email);

    public Personnel findByName(String name);

    public Personnel findByRole(String role);

    public List<Personnel> findAllByNameContainingIgnoreCase(String name);

    public long countByNameContainingIgnoreCase(String name);
}
