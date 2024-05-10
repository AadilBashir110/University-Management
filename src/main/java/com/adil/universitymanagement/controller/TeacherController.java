package com.adil.universitymanagement.controller;

import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teacher")
@PreAuthorize("hasRole('ROLE_TEACHER')")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/all-teachers")
    public ResponseEntity<List<TeacherBean>> getAllTeachers(){
        List<TeacherBean> teacherList = teacherService.getAllTeachers();
        return new ResponseEntity<>(teacherList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherBean> getTeacherById(@PathVariable Long id) {
        try {
            TeacherBean teacher = teacherService.getTeacherById(id);
            return new ResponseEntity<>(teacher,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/add-teacher")
    public ResponseEntity<TeacherBean> createTeacher(@RequestBody TeacherBean teacherBean) {
        teacherService.createTeacher(teacherBean);
        return new ResponseEntity<>(teacherBean, HttpStatus.OK);
    }

    @PutMapping("/update-teacher")
    public ResponseEntity<TeacherBean> updateTeacher(@RequestBody TeacherBean teacherBean){
        teacherService.updateTeacher(teacherBean);
        return new ResponseEntity<>(teacherBean,HttpStatus.OK);
    }

}
