package com.adil.universitymanagement.controller;

import com.adil.universitymanagement.entity.Student;
import com.adil.universitymanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getALlStudents(){
        List<Student> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
      try {
          Student student = studentService.getStudentById(id);
          return new ResponseEntity<>(student,HttpStatus.OK);
      } catch (Exception e){
          e.printStackTrace();
      }
      return null;
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PutMapping("/update-student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
}
