package com.adil.universitymanagement.service;


import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Student;
import com.adil.universitymanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    @Override
   public void addStudent(StudentBean studentBean) {
        Student student = new Student();
        student.setName(studentBean.getName());
        student.setEmail(studentBean.getEmail());
        student.setId(studentBean.getId());

        List<CourseBean> newCourses = new ArrayList<>();

        List<CourseBean> studentCourses = studentBean.getCourseBean();
        for (CourseBean course : studentCourses) {
            CourseBean retrievedCourse = courseService.getCourseById(course.getId());
            if (retrievedCourse != null) {
                newCourses.add(retrievedCourse);
            }
        }

        student.setCourses(newCourses);

        studentRepository.save(student);
    }

    @Override
    public StudentBean getStudentById(Long id){
        if(id==null){
            throw new RuntimeException("could not find student");
        }
        Student student = studentRepository.findById(id).orElse(null);
        StudentBean studentBean = new StudentBean();
        studentBean.setId(student.getId());
        studentBean.setName(student.getName());
        studentBean.setEmail(student.getEmail());

        List<Course> courses = student.getCourses();
        for(Course course: courses){
            if(course != null) {
                CourseBean courseBean = new CourseBean();
                courseBean.setId(course.getId());
                courseBean.setName(course.getName());
                courseBean.setTeacherBean(new TeacherBean(course.getTeacher().getId(),
                        course.getTeacher().getName(),
                        course.getTeacher().getEmail()));

                studentBean.getCourseBean().add(courseBean);
            }
        }
        return studentBean;
    }

    @Override
    public List<StudentBean> getAllStudents() {

        return studentRepository.findAll().stream()
                .map(student -> {
                    StudentBean studentBean = new StudentBean();
                    studentBean.setId(student.getId());
                    studentBean.setName(student.getName());
                    studentBean.setEmail(student.getEmail());

                    List<Course> courses = student.getCourses();
                    for(Course course: courses){
                        if(course != null) {
                            CourseBean courseBean = new CourseBean();
                            courseBean.setId(course.getId());
                            courseBean.setName(course.getName());

                            courseBean.setTeacherBean(new TeacherBean(course.getTeacher().getId(),
                                    course.getTeacher().getName(),
                                    course.getTeacher().getEmail()));

                            studentBean.getCourseBean().add(courseBean);
                        }
                    }
                    return studentBean;
                })
                .collect(Collectors.toList());
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
