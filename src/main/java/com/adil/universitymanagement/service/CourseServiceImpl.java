package com.adil.universitymanagement.service;

import com.adil.universitymanagement.model.Course;
import com.adil.universitymanagement.model.Student;
import com.adil.universitymanagement.model.Teacher;
import com.adil.universitymanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

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
        if(courseDetails.getStudents()!=null){
            oldCourse.setStudents(courseDetails.getStudents());
        }
        if(courseDetails.getTeacher()!=null){
            oldCourse.setTeacher(courseDetails.getTeacher());
        }
        Course updatedCourse = courseRepository.save(oldCourse);
        return updatedCourse;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
        System.out.println("Course deleted with id"+id);
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
}
