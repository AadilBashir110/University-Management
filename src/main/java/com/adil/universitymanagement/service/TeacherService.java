package com.adil.universitymanagement.service;

import com.adil.universitymanagement.model.Course;
import com.adil.universitymanagement.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    public Teacher addTeacher(Teacher teacher);
    public Teacher getTeacherById(Long id) throws Exception;
    public List<Teacher> getAllTeachers();
    public Teacher updateTeacher(Long id, Teacher teacherDetails) throws Exception;
    public void deleteTeacher(Long id);
    public List<Course> getCoursesByTeacher(Teacher teacher);

}
