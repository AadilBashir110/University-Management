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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

        teacherRepository.save(teacher);

        // If a teacher creates another teacher
        if(teacherBean.getPassword()!=null) {
            String encodedPassword = passwordEncoder.encode(teacherBean.getPassword());

            User user = new User();
            user.setUsername(teacherBean.getEmail());
            user.setPassword(encodedPassword);
            user.setRole(Role.ROLE_TEACHER);

            userRepository.save(user);
        }
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
        String oldTeacherEmail = oldTeacher.getEmail();
        if(teacherBean.getName()!=null){
            oldTeacher.setName(teacherBean.getName());
        }
        if(teacherBean.getEmail()!=null){
            oldTeacher.setEmail(teacherBean.getEmail());
        }
        teacherRepository.save(oldTeacher);

        //Update that teacher user when the teacher is updated
        String encodedPassword = passwordEncoder.encode(teacherBean.getPassword());

        userService.updateUser(oldTeacherEmail,teacherBean.getEmail(),encodedPassword);
    }

    /*
    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    } */

}
