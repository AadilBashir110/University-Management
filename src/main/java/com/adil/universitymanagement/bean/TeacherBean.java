package com.adil.universitymanagement.bean;


import com.adil.universitymanagement.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherBean {
    private Long id;
    private String name;
    private String email;

    private List<CourseBean> courseBean = new ArrayList<>();

    public TeacherBean() {
    }

    public TeacherBean(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public TeacherBean(Long id, String name, String email, List<CourseBean> courseBean) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courseBean = courseBean;
    }

    public TeacherBean(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CourseBean> getCourseBean() {
        return courseBean;
    }

    public void setCourseBean(List<CourseBean> courseBean) {
        this.courseBean = courseBean;
    }
}
