package com.adil.universitymanagement.repository;

import com.adil.universitymanagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
