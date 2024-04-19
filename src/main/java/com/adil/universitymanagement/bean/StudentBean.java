package com.adil.universitymanagement.bean;

import com.adil.universitymanagement.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentBean {
    private Long id;
    private String name;
    private String email;
    private Set<Course> courses = new HashSet<>();
}
