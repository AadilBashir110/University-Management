package com.adil.universitymanagement.service;

import com.adil.universitymanagement.model.Teacher;
import com.adil.universitymanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
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

        return teacherRepository.save(newTeacher);
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
    public Teacher updateTeacher(Teacher teacher) {
        Teacher oldTeacher = teacherRepository.findById(teacher.getId()).get();
       if(teacher.getName()!=null){
           oldTeacher.setName(teacher.getName());
       }
       if(teacher.getCourses()!=null){
           oldTeacher.setCourses(teacher.getCourses());
       }
       if(teacher.getEmail()!=null){
           oldTeacher.setEmail(teacher.getEmail());
       }
       return teacherRepository.save(oldTeacher);
    }
    /*
    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    } */

}
