package com.example.demoforjpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoforjpa.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
    
}
