package com.adil.universitymanagement.controller;

import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<StudentBean>> getAllStudents(){
        List<StudentBean> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STUDENT')")
    public ResponseEntity<StudentBean> getStudentById(@PathVariable Long id){
      try {
          StudentBean student = studentService.getStudentById(id);
          return new ResponseEntity<>(student,HttpStatus.OK);
      } catch (Exception e){
          e.printStackTrace();
      }
      return null;
    }
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<StudentBean> createStudent(@RequestBody StudentBean studentBean){
        studentService.createStudent(studentBean);
        return new ResponseEntity<>(studentBean,HttpStatus.OK);
    }

    @PutMapping("/update-student")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STUDENT')")
    public ResponseEntity<StudentBean> updateStudent(@RequestBody StudentBean studentBean){
        studentService.updateStudent(studentBean);
        return new ResponseEntity<>(studentBean,HttpStatus.OK);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteStudent(@RequestParam Long studentId ){
        String message = studentService.deleteStudent(studentId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Long> getStudentCount() {
        long count = studentService.getStudentCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
