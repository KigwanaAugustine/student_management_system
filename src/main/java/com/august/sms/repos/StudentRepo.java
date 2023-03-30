package com.august.sms.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.august.sms.models.Student;

public interface StudentRepo extends JpaRepository<Student, Long>
{
    
}
