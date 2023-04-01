package com.august.sms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.august.sms.models.Student;

public interface StudentService 
{
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    void updateStudent(Student student);
    public void deleteStudent(Long id);
    public Student getStudentById(Long id);
    Page<Student> findPaginated(int pageNo, int pageSize);

}
