package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Role;
import com.adil.universitymanagement.entity.Teacher;
import com.adil.universitymanagement.entity.User;
import com.adil.universitymanagement.repository.CourseRepository;
import com.adil.universitymanagement.repository.TeacherRepository;
import com.adil.universitymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
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

        Teacher oldTeacher = teacherRepository.findTeacherByEmail(teacherBean.getEmail());

        if(oldTeacher == null) {
            Teacher teacher = new Teacher();
            teacher.setName(teacherBean.getName());
            teacher.setEmail(teacherBean.getEmail());

            teacherRepository.save(teacher);

            // If admin creates a teacher
            if(teacherBean.getPassword()!=null) {
                String encodedPassword = passwordEncoder.encode(teacherBean.getPassword());

                User user = new User();
                user.setUsername(teacherBean.getEmail());
                user.setPassword(encodedPassword);
                user.setRole(Role.ROLE_TEACHER);

                userRepository.save(user);
            }
        }
        else {
            throw new RuntimeException("This username is already taken");
        }

    }

    @Override
    public TeacherBean getTeacherById(Long id) {
        if(id==null){
            throw new RuntimeException("Could not find teacher");
        }
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if(teacher!=null){
            if(userService.getUsernameFromToken().equals(teacher.getEmail())
                    || userService.getRoleFromUsername().equals(Role.ROLE_ADMIN)){
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
            else {
                throw new RuntimeException("You cannot access other teacher details");
            }
        }
        else {
            throw new RuntimeException("No teacher exist with id "+id);
        }
    }
    @Override
    public void updateTeacher(TeacherBean teacherBean) {
        Teacher oldTeacher = teacherRepository.findById(teacherBean.getId()).get();

        if(userService.getUsernameFromToken().equals(oldTeacher.getEmail())
        || userService.getRoleFromUsername().equals(Role.ROLE_ADMIN)){
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
        else {
            throw new RuntimeException("You cannot modify other teacher's details");
        }

    }

    @Override
    public String deleteTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        User user = userRepository.findByUsername(teacher.getEmail()).orElseThrow();
        List<Course> courses = teacher.getCourses();
        if (teacher!=null) {
            for (Course course : courses) {
                course.setTeacher(null);
                courseRepository.save(course); // Update the course
            }
            teacherRepository.delete(teacher);
            userRepository.delete(user);

            return "{\"message\": \"Teacher deleted successfully\"}";
        } else {
            throw new RuntimeException("No teacher exist with id "+teacherId);
        }
    }

    @Override
    public Long getTeacherCount() {
        return teacherRepository.count();
    }
}
