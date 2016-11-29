package com.haqiu.core.repository;

import com.haqiu.core.dto.StudentDto;

import java.util.List;

/**
 * 用于扩展Repository
 * Created by H-QIU on 2016/11/20.
 */
public interface StudentCustom {

    List<StudentDto> selectAvgGreaterThanGrade(int grade);

}
