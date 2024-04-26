package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Teacher;

import java.util.List;

public interface TeacherService {
     void createTeacher(TeacherBean teacherBean);
     TeacherBean getTeacherById(Long id);
     List<TeacherBean> getAllTeachers();
     Teacher updateTeacher(Teacher teacher);

   //  void deleteTeacher(Long id);


}
