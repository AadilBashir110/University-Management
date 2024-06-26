package com.adil.universitymanagement.service;


import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Role;
import com.adil.universitymanagement.entity.Student;
import com.adil.universitymanagement.entity.User;
import com.adil.universitymanagement.repository.StudentRepository;
import com.adil.universitymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final CourseService courseService;
    private final UserRepository userRepository;
    private final UserService userService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void createStudent(StudentBean studentBean) {

        Student oldStudent = studentRepository.findStudentByEmail(studentBean.getEmail());

        if(oldStudent==null){
            Student student = new Student();
            student.setName(studentBean.getName());
            student.setEmail(studentBean.getEmail());

            Set<Course> newCourses = new HashSet<>();

           /* List<Long> courseIds = studentBean.getCourseIds();
            for (Long courseId : courseIds) {
                Course course = courseService.getCourseById(courseId);
                if (course != null) {
                    newCourses.add(course);
                } else {
                    throw new RuntimeException("No course exists with id "+courseId);
                }
            }
            student.setCourses(newCourses);*/

            studentRepository.save(student);
            // If admin creates a student
            if(studentBean.getPassword()!=null) {
                String encodedPassword = passwordEncoder.encode(studentBean.getPassword());

                User user = new User();
                user.setUsername(studentBean.getEmail());
                user.setPassword(encodedPassword);
                user.setRole(Role.ROLE_STUDENT);

                userRepository.save(user);
            }
        }
        else {
            throw new RuntimeException("This username is already taken.");
        }

    }

    @Override
    public StudentBean getStudentById(Long id){
        if(id==null){
            throw new RuntimeException("Could not find student");
        }
        Student student = studentRepository.findById(id).orElse(null);
        if(student!=null){
            // Check whether logged in user is this student or not
            if(userService.getUsernameFromToken().equals(student.getEmail())
                    || userService.getRoleFromUsername().equals(Role.ROLE_ADMIN)) {
                StudentBean studentBean = new StudentBean();
                studentBean.setId(student.getId());
                studentBean.setName(student.getName());
                studentBean.setEmail(student.getEmail());

                Set<Course> courses = student.getCourses();
                for (Course course : courses) {
                    if (course != null) {
                        CourseBean courseBean = new CourseBean();
                        courseBean.setId(course.getId());
                        courseBean.setName(course.getName());
                       /* courseBean.setTeacherBean(new TeacherBean(course.getTeacher().getId(),
                                course.getTeacher().getName(),
                                course.getTeacher().getEmail()));
*/
                        studentBean.getCourseBean().add(courseBean);
                    }
                }
                return studentBean;
            }
            else {
                throw new RuntimeException("You cannot access other student details");
            }
        }
        else {
            throw new RuntimeException("No student exist with id "+id);
        }
    }

    @Override
    public List<StudentBean> getAllStudents() {

        return studentRepository.findAll().stream()
                .map(student -> {
                    StudentBean studentBean = new StudentBean();
                    studentBean.setId(student.getId());
                    studentBean.setName(student.getName());
                    studentBean.setEmail(student.getEmail());

                    Set<Course> courses = student.getCourses();
                    for(Course course: courses){
                        if(course != null) {
                            CourseBean courseBean = new CourseBean();
                            courseBean.setId(course.getId());
                            courseBean.setName(course.getName());

                           /* courseBean.setTeacherBean(new TeacherBean(course.getTeacher().getId(),
                                    course.getTeacher().getName(),
                                    course.getTeacher().getEmail()));*/

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

        if(userService.getUsernameFromToken().equals(oldStudent.getEmail())
                || userService.getRoleFromUsername().equals(Role.ROLE_ADMIN)){
            String oldStudentEmail = oldStudent.getEmail();

            if(studentBean.getName()!=null){
                oldStudent.setName(studentBean.getName());
            }
            if(studentBean.getEmail()!=null){
                oldStudent.setEmail(studentBean.getEmail());
            }

            studentRepository.save(oldStudent);

            //Update that student user when the student is updated
            String encodedPassword = passwordEncoder.encode(studentBean.getPassword());

            userService.updateUser(oldStudentEmail,studentBean.getEmail(),encodedPassword);
        }
        else {
            throw new RuntimeException("You cannot modify other student's details");
        }

    }

    @Override
    public String deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        User user = userRepository.findByUsername(student.getEmail()).orElseThrow();
        if(student!=null){
            studentRepository.delete(student);
            userRepository.delete(user);
            return "{\"message\": \"Student deleted successfully\"}";
        } else {
            throw new RuntimeException("No teacher exist with id "+studentId);
        }
    }

    @Override
    public Long getStudentCount() {
        return studentRepository.count();
    }
}


