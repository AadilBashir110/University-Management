package com.adil.universitymanagement.controller;

import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.entity.User;
import com.adil.universitymanagement.service.StudentService;
import com.adil.universitymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    private final UserService userService;

    @GetMapping("/all-students")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<List<StudentBean>> getALlStudents(){
        List<StudentBean> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_TEACHER') OR hasRole('ROLE_STUDENT')")
    public ResponseEntity<StudentBean> getStudentById(@PathVariable Long id){
      try {
          StudentBean student = studentService.getStudentById(id);
          return new ResponseEntity<>(student,HttpStatus.OK);
      } catch (Exception e){
          e.printStackTrace();
      }
      return null;
    }
    @PostMapping("/add-student")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<StudentBean> createStudent(@RequestBody StudentBean studentBean){
        studentService.createStudent(studentBean);
        return new ResponseEntity<>(studentBean,HttpStatus.OK);
    }

    @PutMapping("/update-student")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<StudentBean> updateStudent(@RequestHeader("Authorization")String jwt,
            @RequestBody StudentBean studentBean){
        User reqUser = userService.findUserByJwt(jwt).orElseThrow();
        System.out.println(reqUser.getUsername());
        studentService.updateStudent(studentBean,reqUser.getUsername());
        return new ResponseEntity<>(studentBean,HttpStatus.OK);
    }
}
