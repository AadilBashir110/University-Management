package com.adil.universitymanagement.bean;


import com.adil.universitymanagement.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TeacherBean {
    private Long id;
    private String name;
    private String email;

    private List<CourseBean> courseBean = new ArrayList<>();
    private String password;


    public TeacherBean(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public TeacherBean(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
    }
}
