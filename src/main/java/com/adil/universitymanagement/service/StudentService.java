package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.entity.User;

import java.util.List;
public interface StudentService {

    void createStudent(StudentBean studentBean);
    StudentBean getStudentById(Long id) ;
    List<StudentBean> getAllStudents();
    void updateStudent(StudentBean studentBean);

    // void deleteStudent(Long id);

}
