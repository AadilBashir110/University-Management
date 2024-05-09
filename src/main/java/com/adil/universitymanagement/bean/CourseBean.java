package com.adil.universitymanagement.bean;


import com.adil.universitymanagement.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseBean {
    private Long id;
    private String name;
    private TeacherBean teacherBean;


    public CourseBean(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.teacherBean =  new TeacherBean(course.getTeacher());
    }

}
