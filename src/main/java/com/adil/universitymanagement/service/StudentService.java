package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.entity.Student;

import java.util.List;
public interface StudentService {

    public void addStudent(Student student);
    public Student getStudentById(Long id) ;
    public List<Student> getAllStudents();
    public Student updateStudent(Student student);

   // public void deleteStudent(Long id);

}
