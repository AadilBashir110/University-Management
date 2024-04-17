package com.adil.universitymanagement.service;

import com.adil.universitymanagement.model.Course;
import com.adil.universitymanagement.model.Student;
import com.adil.universitymanagement.model.Teacher;


public interface CourseService {

    Course addCourse(Course course);
    Course getCourseById(Long id) throws Exception;
    Course updateCourse(Long id, Course courseDetails) throws Exception;
    void deleteCourse(Long id);
    Course assignTeacherToCourse(Long id, Teacher teacher);
    Course enrollStudentToCourse(Long id, Student student);

}
