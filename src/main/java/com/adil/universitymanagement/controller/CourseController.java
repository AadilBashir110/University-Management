package com.adil.universitymanagement.controller;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.CourseIdsRequest;
import com.adil.universitymanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_TEACHER') OR hasRole('ROLE_STUDENT') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CourseBean>> getAllCourses(){
        List<CourseBean> courseList = courseService.getAllCourses();
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_TEACHER') OR hasRole('ROLE_STUDENT') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<CourseBean> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            CourseBean courseBean = new CourseBean(course);
            return new ResponseEntity<>(courseBean, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CourseBean> createCourse(@RequestBody CourseBean courseBean){
       courseService.createCourse(courseBean);
       return new ResponseEntity<>(courseBean,HttpStatus.OK);
    }

    @PutMapping("/update-course")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CourseBean> updateCourse(@RequestBody CourseBean courseBean){
        courseService.updateCourse(courseBean);
        return new ResponseEntity<>(courseBean,HttpStatus.OK);
    }

    @PostMapping("/assign-teacher")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> assignTeacherToCourse(@RequestBody CourseIdsRequest courseIdsRequest, @RequestParam Long teacherId){
        String message = courseService.assignTeacherToCourse(courseIdsRequest.getCourseIds(),teacherId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PostMapping("/enroll-student")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STUDENT')")
    public ResponseEntity<String> enrollStudentToCourse(@RequestBody CourseIdsRequest courseIdsRequest, @RequestParam Long studentId){
        String message = courseService.enrollStudentToCourse(courseIdsRequest.getCourseIds(),studentId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @DeleteMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCourse(@RequestParam Long courseId){
        String message = courseService.deleteCourse(courseId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/count")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Long> getCourseCount() {
        long count = courseService.getCourseCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}