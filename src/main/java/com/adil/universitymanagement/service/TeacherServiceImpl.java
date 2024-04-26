package com.adil.universitymanagement.service;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.Teacher;
import com.adil.universitymanagement.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
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
        Teacher teacher = new Teacher();
        teacher.setName(teacherBean.getName());
        teacher.setEmail(teacherBean.getEmail());
        teacher.setId(teacherBean.getId());

        teacherRepository.save(teacher);
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
