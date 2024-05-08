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
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/all-courses")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    public ResponseEntity<List<CourseBean>> getAllCourses(){
        List<CourseBean> courseList = courseService.getAllCourses();
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
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
    @PostMapping("/add-course")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<CourseBean> createCourse(@RequestBody CourseBean courseBean){
       courseService.createCourse(courseBean);
       return new ResponseEntity<>(courseBean,HttpStatus.OK);
    }

    @PutMapping("/update-course")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<CourseBean> updateCourse(@RequestBody CourseBean courseBean){
        courseService.updateCourse(courseBean);
        return new ResponseEntity<>(courseBean,HttpStatus.OK);
    }

    @PostMapping("/assign-teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<CourseBean> assignTeacherToCourse(@RequestBody CourseIdsRequest courseIdsRequest, @RequestParam Long teacherId){
        CourseBean assignedCourse = courseService.assignTeacherToCourse(courseIdsRequest.getCourseIds(),teacherId);
        return new ResponseEntity<>(assignedCourse,HttpStatus.OK);
    }

    @PostMapping("/enroll-student")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<CourseBean> enrollStudentToCourse(@RequestBody CourseIdsRequest courseIdsRequest, @RequestParam Long studentId){
        CourseBean enrolledStudent = courseService.enrollStudentToCourse(courseIdsRequest.getCourseIds(),studentId);
        return new ResponseEntity<>(enrolledStudent,HttpStatus.OK);
    }

    @GetMapping("/by-teacher/{teacherId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<List<CourseBean>> getCoursesByTeacher(@PathVariable Long teacherId ){
        List<CourseBean> courses = courseService.getCoursesByTeacher(teacherId);
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }
    @GetMapping("/by-student/{studentId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<List<CourseBean>> getCoursesByStudent(@PathVariable Long studentId){
        List<CourseBean> courses = courseService.getCoursesByStudent(studentId);
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }
}
