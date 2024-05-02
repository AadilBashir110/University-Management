package com.adil.universitymanagement.service;


import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Student;
import com.adil.universitymanagement.entity.User;
import com.adil.universitymanagement.entity.Role;
import com.adil.universitymanagement.repository.StudentRepository;
import com.adil.universitymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    private final UserRepository userRepository;

    @Override
   public void createStudent(StudentBean studentBean) {
        Student student = new Student();
        student.setName(studentBean.getName());
        student.setEmail(studentBean.getEmail());
        student.setId(studentBean.getId());

        List<Course> newCourses = new ArrayList<>();

        List<Long> courseIds = studentBean.getCourseIds();
        for (Long courseId : courseIds) {
            Course course = courseService.getCourseById(courseId);
            if (course != null) {
                newCourses.add(course);
            } else {
                throw new RuntimeException("No course exists with id "+courseId);
            }
        }
        student.setCourses(newCourses);

        studentRepository.save(student);

        User user = new User();
        user.setUsername(student.getEmail()); // Use email as username for simplicity
        user.setPassword(generateRandomPassword()); // Generate a random password
        user.setRole(Role.STUDENT); // Set the role for the user

        // Save the user entity
        userRepository.save(user);
    }

    private String generateRandomPassword() {
        // Implement your logic to generate a random password
        return UUID.randomUUID().toString();
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
    public void updateStudent(StudentBean studentBean) {
        Student oldStudent = studentRepository.findById(studentBean.getId()).get();

        if(studentBean.getName()!=null){
            oldStudent.setName(studentBean.getName());
        }
        if(studentBean.getEmail()!=null){
            oldStudent.setEmail(studentBean.getEmail());
        }

        studentRepository.save(oldStudent);
    }

   /* @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
        System.out.println("Student deleted with id"+id);
    }*/

}
