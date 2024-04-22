package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.entity.Course;


import java.util.List;


public interface CourseService {

    public List<Course> getAllCourses();
    Course addCourse(Course course);
    Course getCourseById(Long id);
    Course updateCourse(Course course);

    Course assignTeacherToCourse(List<Long> courseIds, Long teacherId);

    Course enrollStudentToCourse(List<Long> courseIds, Long studentId);

    public List<Course> findCourseByTeacher(Long teacherId);
    public List<Course> findCoursesByStudent(Long studentId);



   // void deleteCourse(Long id);

}
