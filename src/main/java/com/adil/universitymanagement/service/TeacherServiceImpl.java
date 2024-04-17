package com.adil.universitymanagement.service;

import com.adil.universitymanagement.model.Course;
import com.adil.universitymanagement.model.Teacher;
import com.adil.universitymanagement.repository.CourseRepository;
import com.adil.universitymanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    @Override
    public Teacher addTeacher(Teacher teacher) {
        Teacher newTeacher = new Teacher();
        newTeacher.setName(teacher.getName());
        newTeacher.setEmail(teacher.getEmail());
        newTeacher.setId(teacher.getId());
        newTeacher.setCourses(teacher.getCourses());
        Teacher savedTeacher = teacherRepository.save(newTeacher);
        return savedTeacher;
    }

    @Override
    public Teacher getTeacherById(Long id) throws Exception {

        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isPresent()){
           return teacher.get();
        }
        throw new Exception("Teacher not exist with id"+id);
    }
    @Override
    public Teacher updateTeacher(Long id, Teacher teacherDetails) throws Exception {
        Optional<Teacher> teacher = teacherRepository.findById(id);
       if(teacher.isEmpty()){
           throw new Exception("Teacher not exist with id"+id);
       }
       Teacher oldTeacher = teacher.get();
       if(teacherDetails.getName()!=null){
           oldTeacher.setName(teacherDetails.getName());
       }
       if(teacherDetails.getCourses()!=null){
           oldTeacher.setCourses(teacherDetails.getCourses());
       }
       if(teacherDetails.getEmail()!=null){
           oldTeacher.setEmail(teacherDetails.getEmail());
       }
       Teacher updatedTeacher = teacherRepository.save(oldTeacher);
       return updatedTeacher;
    }
    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Course> getCoursesByTeacher(Teacher teacher) {
        return courseRepository.findByTeacher(teacher);
    }
}
