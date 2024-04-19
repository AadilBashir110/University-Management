package com.adil.universitymanagement.service;

import com.adil.universitymanagement.entity.Teacher;

import java.util.List;

public interface TeacherService {
    public Teacher addTeacher(Teacher teacher);
    public Teacher getTeacherById(Long id) throws Exception;
    public List<Teacher> getAllTeachers();
    public Teacher updateTeacher(Teacher teacher);
   // public void deleteTeacher(Long id);


}
