package com.adil.universitymanagement.bean;



import java.util.ArrayList;
import java.util.List;


public class StudentBean {

    private Long id;
    private String name;
    private String email;

    private List<CourseBean> courseBean = new ArrayList<>();
    public StudentBean() {
    }

    public StudentBean(Long id, String name, String email, List<CourseBean> courseBean) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courseBean = courseBean;
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
