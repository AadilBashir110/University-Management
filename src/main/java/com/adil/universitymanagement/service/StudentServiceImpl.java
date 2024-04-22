package com.adil.universitymanagement.service;


import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Student;
import com.adil.universitymanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    @Override
    public void addStudent(Student student) {
        Student newStudent = new Student();
        newStudent.setName(student.getName());
        newStudent.setEmail(student.getEmail());
        newStudent.setId(student.getId());

        List<Course> newCourses = new ArrayList<>();

        List<Course> studentCourses = student.getCourses();
        for (Course course : studentCourses) {
            Course retrievedCourse = courseService.getCourseById(course.getId());
            if (retrievedCourse != null) {
                newCourses.add(retrievedCourse);
            }
        }

        newStudent.setCourses(newCourses);

        studentRepository.save(newStudent);
    }

    @Override
    public Student getStudentById(Long id){
        Student student = studentRepository.findById(id).get();
        if(student==null){
            throw new RuntimeException("could not find student");
        }
        return student;
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
