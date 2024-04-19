package com.adil.universitymanagement.service;

import com.adil.universitymanagement.entity.Student;

import java.util.List;
public interface StudentService {

    public Student addStudent(Student student);
    public Student getStudentById(Long id) throws Exception;
    public List<Student> getAllStudents();
    public Student updateStudent(Student student);
   // public void deleteStudent(Long id);

}
