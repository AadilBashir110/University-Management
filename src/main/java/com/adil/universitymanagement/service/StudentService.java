package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.entity.User;

import java.util.List;
public interface StudentService {

    void createStudent(StudentBean studentBean);
    StudentBean getStudentById(Long id) ;
    List<StudentBean> getAllStudents();
    void updateStudent(StudentBean studentBean, String username);

    // void deleteStudent(Long id);

}
