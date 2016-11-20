package com.haqiu.core.repository;

import com.haqiu.core.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("ALL")
@Repository("studentRepository")
public interface StudentRepository extends  BaseRepository<Student,Long>,StudentCustom{

    Student findBySname(String sname);

    List<Student> findBySageIsGreaterThan(Short sage);

    long countStudentBySageIsGreaterThan(Short sage);
}
