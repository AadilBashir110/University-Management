package com.adil.universitymanagement.bean;


import java.util.ArrayList;
import java.util.List;


public class StudentBean {

    private Long id;
    private String name;
    private String email;

    private List<CourseBean> courses = new ArrayList<>();
    public StudentBean() {
    }

    public StudentBean(Long id, String name, String email, List<CourseBean> courses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courses = courses;
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

    public List<CourseBean> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseBean> courses) {
        this.courses = courses;
    }
}
