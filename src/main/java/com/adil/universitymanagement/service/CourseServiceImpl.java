package com.adil.universitymanagement.service;

import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Student;
import com.adil.universitymanagement.entity.Teacher;
import com.adil.universitymanagement.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;

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

        Course savedCourse = courseRepository.save(newCourse);
        return savedCourse;
    }

    @Override
    public Course getCourseById(Long id) {
        Course course = courseRepository.findById(id).get();
        if(course == null){
            throw new RuntimeException("could not find course");
        }
        return course;
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

    @Override
    public Course assignTeacherToCourse(Long id, Teacher teacher) {
        Course course = courseRepository.findById(id).orElse(null);
        if(course!=null){
            course.setTeacher(teacher);
            return courseRepository.save(course);
        }
        return null;
    }

    @Override
    public Course enrollStudentToCourse(Long id, Student student) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            course.getStudents().add(student);
            return courseRepository.save(course);
        }
        return null;
    }
    @Override
    public List<Course> getCoursesByTeacher(Teacher teacher) {
        return courseRepository.findByTeacher(teacher);
    }

    @Override
    public List<Course> getCoursesByStudent(Student student) {
        return courseRepository.findByStudents(student);
    }


     /* @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        System.out.println("Course deleted with id"+id);
    }*/

}
