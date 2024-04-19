package com.adil.universitymanagement.controller;

import com.adil.universitymanagement.model.Course;
import com.adil.universitymanagement.model.Student;
import com.adil.universitymanagement.model.Teacher;
import com.adil.universitymanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courseList = courseService.getAllCourses();
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id){
        try{
            Course course = courseService.getCourseById(id);
            return new ResponseEntity<>(course,HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        courseService.addCourse(course);
        return new ResponseEntity<>(course,HttpStatus.OK);
    }

    @PutMapping("/update-course")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course){
        courseService.updateCourse(course);
        return new ResponseEntity<>(course,HttpStatus.OK);
    }

    @PutMapping("/assign-teacher/{id}")
    public ResponseEntity<Course> assignTeacherToCourse(@PathVariable Long id, @RequestBody Teacher teacher){
        Course assignedCourse = courseService.assignTeacherToCourse(id,teacher);
        return new ResponseEntity<>(assignedCourse,HttpStatus.OK);
    }

    @PutMapping("/enroll-student/{id}")
    public ResponseEntity<Course> enrollStudentToCourse(@PathVariable Long id, @RequestBody Student student){
        Course enrolledCourse = courseService.enrollStudentToCourse(id,student);
        return new ResponseEntity<>(enrolledCourse,HttpStatus.OK);
    }
}
