package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.TeacherBean;

import java.util.List;

public interface TeacherService {
     void createTeacher(TeacherBean teacherBean);
     TeacherBean getTeacherById(Long id);
     List<TeacherBean> getAllTeachers();
     void updateTeacher(TeacherBean teacherBean);

     void deleteTeacher(Long id);


}
