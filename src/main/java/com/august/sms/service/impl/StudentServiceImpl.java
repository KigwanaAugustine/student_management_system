package com.august.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.august.sms.models.Student;
import com.august.sms.repos.StudentRepo;
import com.august.sms.service.StudentService;


import lombok.AllArgsConstructor;

@Service @AllArgsConstructor
public class StudentServiceImpl implements StudentService
{
    private StudentRepo studentRepo;

    @Override
    public List<Student> getAllStudents() {
        
        return studentRepo.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        
        return studentRepo.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepo.findById(id).get();
    }

    
}

