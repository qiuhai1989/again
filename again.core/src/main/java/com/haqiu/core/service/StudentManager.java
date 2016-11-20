package com.haqiu.core.service;

import com.haqiu.core.dto.StudentDto;
import com.haqiu.core.entity.Student;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface StudentManager extends  BaseManager<Student,Long> {

    Student findBySname(String sname);

    List<Student> selectBySageIsGreaterThan(Short sage);

    long countStudentBySageIsGreaterThan(Short sage);

    List<StudentDto> selectAvgGreaterThanGrade(int grade);

}
