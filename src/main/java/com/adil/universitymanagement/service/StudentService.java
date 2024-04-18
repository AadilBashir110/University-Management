package com.adil.universitymanagement.service;

import com.adil.universitymanagement.model.Course;
import com.adil.universitymanagement.model.Student;

import java.util.List;
public interface StudentService {

    public Student addStudent(Student student);
    public Student getStudentById(Long id) throws Exception;
    public List<Student> getAllStudents();
    public Student updateStudent(Student studentDetails);
   // public void deleteStudent(Long id);

}
