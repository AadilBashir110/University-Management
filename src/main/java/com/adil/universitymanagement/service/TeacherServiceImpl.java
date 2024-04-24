package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Teacher;
import com.adil.universitymanagement.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
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
