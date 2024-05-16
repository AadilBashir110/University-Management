package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Role;
import com.adil.universitymanagement.entity.Student;
import com.adil.universitymanagement.entity.Teacher;
import com.adil.universitymanagement.repository.CourseRepository;
import com.adil.universitymanagement.repository.StudentRepository;
import com.adil.universitymanagement.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    private final UserService userService;

    @Override
    public List<CourseBean> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> {
                    CourseBean courseBean = new CourseBean();
                    courseBean.setId(course.getId());
                    courseBean.setName(course.getName());
                    courseBean.setTeacherBean(new TeacherBean(course.getTeacher()));
                    return courseBean;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void createCourse(CourseBean courseBean) {
        Course oldCourse = courseRepository.findCourseByName(courseBean.getName());
        if(oldCourse == null){
            Course course = new Course();
            course.setName(courseBean.getName());

            courseRepository.save(course);
        }
        else {
            throw new RuntimeException("This course is already present");
        }
    }

    @Override
    public Course getCourseById(Long id) {
        if(id == null){
            throw new RuntimeException("Could not find course with id "+id);
        }
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCourse(CourseBean courseBean) {
        Course oldCourse = courseRepository.findById(courseBean.getId()).get();
        if(courseBean.getName()!=null){
            oldCourse.setName(courseBean.getName());
        }
        courseRepository.save(oldCourse);
    }

   @Override
    public String assignTeacherToCourse(List<Long> courseIds, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);

        for (Long courseId : courseIds) {
            Course course = getCourseById(courseId);
            if (course != null) {
                course.setTeacher(teacher);
                courseRepository.save(course);
            }
        }
        return "Teacher assigned successfully";
    }

    public String enrollStudentToCourse(List<Long> courseIds, Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if(userService.getUsernameFromToken().equals(student.getEmail())
                || userService.getRoleFromUsername().equals(Role.ROLE_ADMIN)){
            for (Long courseId : courseIds) {
                Course course = getCourseById(courseId);
                if (course != null) {
                    student.getCourses().add(course);
                }
            }
            studentRepository.save(student);
            return "Student enrolled successfully";
        }
        else {
            throw new RuntimeException("Cannot access another student's details");
        }

    }

     /* @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        System.out.println("Course deleted with id"+id);
    }*/

}
