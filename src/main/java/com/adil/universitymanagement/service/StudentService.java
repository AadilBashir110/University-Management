package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.StudentBean;

import java.util.List;
public interface StudentService {

    void createStudent(StudentBean studentBean);
    StudentBean getStudentById(Long id) ;
    List<StudentBean> getAllStudents();
    void updateStudent(StudentBean studentBean);
    String deleteStudent(Long studentId);

}
