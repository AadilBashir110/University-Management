package com.adil.universitymanagement.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentBean {

    private Long id;
    private String name;
    private String email;

    private List<CourseBean> courseBean = new ArrayList<>();
    private List<Long> courseIds;
    private String password;
}
