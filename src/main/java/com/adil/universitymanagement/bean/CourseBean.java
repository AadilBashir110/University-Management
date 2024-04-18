package com.adil.universitymanagement.bean;

import com.adil.universitymanagement.model.Student;
import com.adil.universitymanagement.model.Teacher;
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
public class CourseBean {
    private Long id;
    private String name;
    private Teacher teacher;
    private Set<Student> students = new HashSet<>();
}
