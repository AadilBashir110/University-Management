package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.entity.Course;


import java.util.List;


public interface CourseService {

    List<CourseBean> getAllCourses();
    void addCourse(CourseBean courseBean);
    Course getCourseById(Long id);  // Do not convert to bean, already converted in Course Controller
    Course updateCourse(Course course);

    CourseBean assignTeacherToCourse(List<Long> courseIds, Long teacherId);

    Course enrollStudentToCourse(List<Long> courseIds, Long studentId);

    List<Course> findCourseByTeacher(Long teacherId);
    List<Course> findCoursesByStudent(Long studentId);



   // void deleteCourse(Long id);

}
