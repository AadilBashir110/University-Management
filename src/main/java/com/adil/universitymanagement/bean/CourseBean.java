package com.adil.universitymanagement.bean;


public class CourseBean {
    private Long id;
    private String name;
    private TeacherBean teacherBean;

    public CourseBean() {
    }

    public CourseBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseBean(Long id, String name, TeacherBean teacherBean) {
        this.id = id;
        this.name = name;
        this.teacherBean = teacherBean;
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
