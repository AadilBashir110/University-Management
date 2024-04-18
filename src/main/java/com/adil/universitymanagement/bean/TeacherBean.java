package com.adil.universitymanagement.bean;

import com.adil.universitymanagement.model.Course;
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
public class TeacherBean {
    private Long id;
    private String name;
    private String email;
    private Set<Course> courses = new HashSet<>();
}
