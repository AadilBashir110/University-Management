package com.adil.universitymanagement.service;


import com.adil.universitymanagement.model.Course;
import com.adil.universitymanagement.model.Student;
import com.adil.universitymanagement.repository.CourseRepository;
import com.adil.universitymanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student addStudent(Student student) {
        Student newStudent = new Student();
        newStudent.setName(student.getName());
        newStudent.setEmail(student.getEmail());
        newStudent.setId(student.getId());
        newStudent.setCourses(student.getCourses());

        Student savedStudent = studentRepository.save(newStudent);
        return savedStudent;
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
    public Student updateStudent(Long id, Student studentDetails) throws Exception {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()){
            throw new Exception("Student not exist with id"+id);
        }

        Student oldStudent = student.get();
        if(studentDetails.getName()!=null){
            oldStudent.setName(studentDetails.getName());
        }
        if(studentDetails.getEmail()!=null){
            oldStudent.setEmail(studentDetails.getEmail());
        }
        if(studentDetails.getCourses()!=null){
            oldStudent.setCourses(studentDetails.getCourses());
        }
        Student updatedStudent = studentRepository.save(oldStudent);
        return updatedStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
        System.out.println("Student deleted with id"+id);
    }

    @Override
    public List<Course> getCoursesByStudent(Student student) {
        return courseRepository.findByStudent(student);
    }
}
