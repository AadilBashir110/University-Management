package com.adil.universitymanagement.controller;

import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Teacher;
import com.adil.universitymanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
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

    @PostMapping
    public ResponseEntity<TeacherBean> createTeacher(@RequestBody TeacherBean teacherBean) {
        teacherService.createTeacher(teacherBean);
        return new ResponseEntity<>(teacherBean, HttpStatus.OK);
    }

    @PutMapping("/update-teacher")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher){
        teacherService.updateTeacher(teacher);
        return new ResponseEntity<>(teacher,HttpStatus.OK);
    }

    // Other End Points will go here
}
