package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.entity.Course;


import java.util.List;


public interface CourseService {

    List<CourseBean> getAllCourses();
    void createCourse(CourseBean courseBean);
    Course getCourseById(Long id);  // Do not convert to bean, already converted in Course Controller
    void updateCourse(CourseBean courseBean);

    String assignTeacherToCourse(List<Long> courseIds, Long teacherId);

    String enrollStudentToCourse(List<Long> courseIds, Long studentId);

   // void deleteCourse(Long id);

}
