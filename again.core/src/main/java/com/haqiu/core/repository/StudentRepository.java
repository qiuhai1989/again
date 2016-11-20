package com.haqiu.core.repository;

import com.haqiu.core.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {



}
