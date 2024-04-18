package com.adil.universitymanagement.service;

import com.adil.universitymanagement.model.Course;
import com.adil.universitymanagement.model.Student;
import com.adil.universitymanagement.model.Teacher;

import java.util.List;


public interface CourseService {

    public List<Course> getAllCourses();
    Course addCourse(Course course);
    Course getCourseById(Long id) throws Exception;
    Course updateCourse(Long id, Course courseDetails) throws Exception;
    void deleteCourse(Long id);
    Course assignTeacherToCourse(Long id, Teacher teacher);
    Course enrollStudentToCourse(Long id, Student student);
    public List<Course> getCoursesByTeacher(Teacher teacher);
    public List<Course> getCoursesByStudent(Student student);

}
