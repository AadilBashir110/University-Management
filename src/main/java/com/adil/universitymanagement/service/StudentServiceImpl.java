package com.adil.universitymanagement.service;


import com.adil.universitymanagement.model.Student;
import com.adil.universitymanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        Student newStudent = new Student();
        newStudent.setName(student.getName());
        newStudent.setEmail(student.getEmail());
        newStudent.setId(student.getId());
        newStudent.setCourses(student.getCourses());

        return studentRepository.save(newStudent);
    }

    @Override
    public Student getStudentById(Long id) throws Exception {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }
        throw new Exception("Student now exist with student id"+id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        Student oldStudent = studentRepository.findById(student.getId()).get();

        if(student.getName()!=null){
            oldStudent.setName(student.getName());
        }
        if(student.getEmail()!=null){
            oldStudent.setEmail(student.getEmail());
        }
        if(student.getCourses()!=null){
            oldStudent.setCourses(student.getCourses());
        }

        return studentRepository.save(oldStudent);
    }

   /* @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
        System.out.println("Student deleted with id"+id);
    }*/

}
