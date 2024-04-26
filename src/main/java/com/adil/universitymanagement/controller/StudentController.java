package com.adil.universitymanagement.controller;

import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.entity.Student;
import com.adil.universitymanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentBean>> getALlStudents(){
        List<StudentBean> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentBean> getStudentById(@PathVariable Long id){
      try {
          StudentBean student = studentService.getStudentById(id);
          return new ResponseEntity<>(student,HttpStatus.OK);
      } catch (Exception e){
          e.printStackTrace();
      }
      return null;
    }
    @PostMapping
    public ResponseEntity<StudentBean> createStudent(@RequestBody StudentBean studentBean){
        studentService.createStudent(studentBean);
        return new ResponseEntity<>(studentBean,HttpStatus.OK);
    }

    @PutMapping("/update-student")
    public ResponseEntity<StudentBean> updateStudent(@RequestBody StudentBean studentBean){
        studentService.updateStudent(studentBean);
        return new ResponseEntity<>(studentBean,HttpStatus.OK);
    }
}
