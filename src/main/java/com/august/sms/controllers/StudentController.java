package com.august.sms.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.august.sms.models.Student;
import com.august.sms.service.StudentService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;


    // listing paginated students
    @GetMapping("/page/{pageNo}")
     public ModelAndView paginatedStudents(@PathVariable (value = "pageNo") int pageNo, ModelAndView mv)
     {
        mv.setViewName("students");
        
        int pageSize = 5;
        
        //create a page object for the current view
        Page<Student> page = studentService.findPaginated(pageNo, pageSize);
        
        //bind data the relevant data to this page object
        List<Student> listStudents = page.getContent();

        //add the necessary attributes for the view
        mv.addObject("listStudents", listStudents);
        mv.addObject("currentPage", pageNo);
        mv.addObject("totalPages", page.getTotalPages());
        mv.addObject("totalItems", page.getTotalElements());
        
        return mv;
    }

    //show the home page
    @GetMapping("/")
    public ModelAndView viewHomePage(ModelAndView mv)
    {
        mv.setViewName("students");

        paginatedStudents(1, mv);

        return mv;
    }


    // handler method to list all students and return model and view
    @GetMapping("/students")
    public ModelAndView listStudents(ModelAndView mv) {
        mv.setViewName("students");

        List<Student> students = studentService.getAllStudents();

        mv.addObject("students", students);

        return mv;
    }

    


    //adding a new student
    @PostMapping("/students")
    public String saveStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/";
    }
    

    @GetMapping("/students/new")
    public ModelAndView createStudentForm(ModelAndView mv)
    {
        
        //set view
        mv.setViewName("create_student");

        //create student object to hold student form data
        Student student = new Student();
         
        mv.addObject("student", student);
        
        return mv;
    }

    @GetMapping("/students/edit_form/{id}")
    public ModelAndView editStudentForm(@PathVariable Long id, ModelAndView mv)
    {
        //set view
        mv.setViewName("edit_student");

        mv.addObject("student", studentService.getStudentById(id));

        return mv;
    }

    @PostMapping("/students/udpate/{id}")
    public String updateStudent(@PathVariable Long id, Student student)
    {
        //get Student from database by id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        //save updated student object
        studentService.saveStudent(existingStudent);

        return "redirect:/";
    }


    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id)
    {
        studentService.deleteStudent(id);

        return "redirect:/";
    }

}
