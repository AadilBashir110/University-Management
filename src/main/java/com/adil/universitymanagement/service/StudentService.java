package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.entity.Student;

import java.util.List;
public interface StudentService {

    void addStudent(StudentBean studentBean);
    StudentBean getStudentById(Long id) ;
    List<StudentBean> getAllStudents();
    Student updateStudent(Student student);

    // void deleteStudent(Long id);

}
