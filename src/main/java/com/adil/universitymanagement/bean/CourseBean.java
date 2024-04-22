package com.adil.universitymanagement.bean;

import com.adil.universitymanagement.entity.Student;
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
public class CourseBean {
    private Long id;
    private String name;
    private TeacherBean teacherBean;
    private List<StudentBean> studentBeans = new ArrayList<>();
}
