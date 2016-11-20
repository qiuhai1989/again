package com.haqiu.core.service.impl;

import com.haqiu.core.entity.Student;
import com.haqiu.core.repository.BaseRepository;
import com.haqiu.core.repository.StudentRepository;
import com.haqiu.core.service.StudentManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
@Service("commodityManager")
public class StudentManagerImpl extends BaseManagerImpl<Student,Long> implements StudentManager  {

    @Resource(name = "studentRepository")
    private StudentRepository studentRepository;

    @Override
    @Resource(name = "studentRepository")
    public void setBaseDao(BaseRepository<Student, Long> baseRepository) {
        this.baseRepository = studentRepository;
    }

    @Override
    public Student selectBySname(String sname) {
        return studentRepository.selectBySname(sname);
    }
}
