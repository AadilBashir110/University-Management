package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Role;
import com.adil.universitymanagement.entity.Teacher;
import com.adil.universitymanagement.entity.User;
import com.adil.universitymanagement.repository.TeacherRepository;
import com.adil.universitymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Override
    public List<TeacherBean> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacher -> {
                    TeacherBean teacherBean = new TeacherBean();
                    teacherBean.setId(teacher.getId());
                    teacherBean.setName(teacher.getName());
                    teacherBean.setEmail(teacher.getEmail());

                    List<Course> courses = teacher.getCourses();
                    for(Course course: courses){
                        if(course != null) {
                            CourseBean courseBean = new CourseBean();
                            courseBean.setId(course.getId());
                            courseBean.setName(course.getName());

                            teacherBean.getCourseBean().add(courseBean);
                        }
                    }
                    return teacherBean;
                })
                .collect(Collectors.toList());
    }
    @Override
    public void createTeacher(TeacherBean teacherBean) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherBean.getName());
        teacher.setEmail(teacherBean.getEmail());
        teacher.setId(teacherBean.getId());

        teacherRepository.save(teacher);

        User user = new User();
        user.setUsername(teacher.getEmail()); // Use email as username for simplicity
        user.setPassword(generateRandomPassword()); // Generate a random password
        user.setRole(Role.TEACHER); // Set the role for the user

        // Save the user entity
        userRepository.save(user);
    }
    private String generateRandomPassword() {
        // Implement your logic to generate a random password
        return UUID.randomUUID().toString();
    }

    @Override
    public TeacherBean getTeacherById(Long id) {
        if(id==null){
            throw new RuntimeException("could not find teacher");
        }
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        TeacherBean teacherBean = new TeacherBean();

        teacherBean.setId(teacher.getId());
        teacherBean.setName(teacher.getName());
        teacherBean.setEmail(teacher.getEmail());

        List<Course> teacherCourses = teacher.getCourses();
        for(Course course: teacherCourses){
            if(course != null) {
                CourseBean courseBean = new CourseBean();
                courseBean.setId(course.getId());
                courseBean.setName(course.getName());

                teacherBean.getCourseBean().add(courseBean);
            }
        }

        return teacherBean;
    }
    @Override
    public void updateTeacher(TeacherBean teacherBean) {
        Teacher oldTeacher = teacherRepository.findById(teacherBean.getId()).get();
       if(teacherBean.getName()!=null){
           oldTeacher.setName(teacherBean.getName());
       }
       if(teacherBean.getEmail()!=null){
           oldTeacher.setEmail(teacherBean.getEmail());
       }
       teacherRepository.save(oldTeacher);
    }
    /*
    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    } */

}
