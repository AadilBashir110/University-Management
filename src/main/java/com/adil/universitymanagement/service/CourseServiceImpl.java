package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Student;
import com.adil.universitymanagement.entity.Teacher;
import com.adil.universitymanagement.repository.CourseRepository;
import com.adil.universitymanagement.repository.StudentRepository;
import com.adil.universitymanagement.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public String assignTeacherToCourse(Long courseId, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        Course course = getCourseById(courseId);
        if (course != null) {
            course.setTeacher(teacher);
        }
        courseRepository.save(course);
        return "{\"message\": \"Teacher assigned successfully\"}";
    }

    public String enrollStudentToCourse(Long courseId, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = getCourseById(courseId);
        if (course != null) {
            student.getCourses().add(course);
        }
        studentRepository.save(student);
        return "{\"message\": \"Student enrolled successfully\"}";
    }

    @Override
    public String deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        if(course!=null){
            for (Student student : course.getStudents()) {
                student.getCourses().remove(course);
                studentRepository.save(student);
            }
            courseRepository.delete(course);
            return "{\"message\": \"Course deleted successfully\"}";
        }
        else {
            throw new RuntimeException("No Course exists with id "+courseId);
        }
    }

    @Override
    public Long getCourseCount() {
        return courseRepository.count();
    }
}
