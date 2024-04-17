package com.adil.universitymanagement.service;

import com.adil.universitymanagement.model.Course;
import com.adil.universitymanagement.model.Student;
import com.adil.universitymanagement.model.Teacher;
import com.adil.universitymanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
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
    public Course getCourseById(Long id) throws Exception {

        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) {
            return course.get();
        }
        throw new Exception("Course not exist with id"+id);
    }

    @Override
    public Course updateCourse(Long id, Course courseDetails) throws Exception {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isEmpty()){
            throw new Exception("Course not exist with id"+id);
        }
        Course oldCourse = course.get();
        if(courseDetails.getName()!=null){
            oldCourse.setName(courseDetails.getName());
        }

        return null;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        System.out.println("Course deleted with id"+id);
    }

    @Override
    public Course assignTeacherToCourse(Long id, Teacher teacher) {
        return null;
    }

    @Override
    public Course enrollStudentToCourse(Long id, Student student) {
        return null;
    }
}
