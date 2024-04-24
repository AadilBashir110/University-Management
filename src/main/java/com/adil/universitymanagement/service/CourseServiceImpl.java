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

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        Course newCourse = new Course();
        newCourse.setName(course.getName());
        newCourse.setId(course.getId());
        newCourse.setStudents(course.getStudents());
        newCourse.setTeacher(course.getTeacher());

        return courseRepository.save(newCourse);
    }

    @Override
    public Course getCourseById(Long id) {
        if(id == null){
            throw new RuntimeException("could not find course with id "+id);
        }
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course updateCourse(Course course) {
        Course oldCourse = courseRepository.findById(course.getId()).get();

        if(course.getName()!=null){
            oldCourse.setName(course.getName());
        }
        if(course.getStudents()!=null){
            oldCourse.setStudents(course.getStudents());
        }
        if(course.getTeacher()!=null){
            oldCourse.setTeacher(course.getTeacher());
        }
        return courseRepository.save(oldCourse);

    }

   /* @Override
    public Course assignTeacherToCourse(List<Long> courseIds, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);

        for (Long courseId : courseIds) {
            Course course = getCourseById(courseId);
            if (course != null) {
                course.setTeacher(teacher);
                courseRepository.save(course);
            }
        }
        return null;
    }
*/
    /*public Course enrollStudentToCourse(List<Long> courseIds, Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);

        for (Long courseId : courseIds) {
            Course course = getCourseById(courseId);
            if (course != null) {
                student.getCourses().add(course);
            }
        }
        studentRepository.save(student);
        return null;
    }*/

    @Override
    public List<Course> findCourseByTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        return courseRepository .findByTeacher(teacher);
    }

    @Override
   public List<Course> findCoursesByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return courseRepository.findByStudents(student);
    }


     /* @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        System.out.println("Course deleted with id"+id);
    }*/

}
