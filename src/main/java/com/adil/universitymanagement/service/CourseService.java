package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.entity.Course;


import java.util.List;


public interface CourseService {

    List<CourseBean> getAllCourses();
    void createCourse(CourseBean courseBean);
    Course getCourseById(Long id);  // Do not convert to bean, already converted in Course Controller
    Course updateCourse(Course course);

    CourseBean assignTeacherToCourse(List<Long> courseIds, Long teacherId);

    CourseBean enrollStudentToCourse(List<Long> courseIds, Long studentId);

    List<CourseBean> getCoursesByTeacher(Long teacherId);
    List<CourseBean> getCoursesByStudent(Long studentId);



   // void deleteCourse(Long id);

}
