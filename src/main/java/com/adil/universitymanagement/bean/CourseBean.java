package com.adil.universitymanagement.bean;


import com.adil.universitymanagement.entity.Course;

public class CourseBean {
    private Long id;
    private String name;
    private TeacherBean teacherBean;

    public CourseBean() {
    }

    public CourseBean(Long id, String name, TeacherBean teacherBean) {
        this.id = id;
        this.name = name;
        this.teacherBean = teacherBean;
    }

    public CourseBean(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.teacherBean =  new TeacherBean(course.getTeacher());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherBean getTeacherBean() {
        return teacherBean;
    }

    public void setTeacherBean(TeacherBean teacherBean) {
        this.teacherBean = teacherBean;
    }
}
