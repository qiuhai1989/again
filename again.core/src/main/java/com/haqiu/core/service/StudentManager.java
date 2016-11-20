package com.haqiu.core.service;

import com.haqiu.core.entity.Student;
import com.haqiu.core.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface StudentManager extends  BaseManager<Student,Long> {

    Student selectBySname(String sname);


}
