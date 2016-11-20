package com.haqiu.core.service.impl;

import com.haqiu.core.dto.StudentDto;
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
    public Student findBySname(String sname) {
        return studentRepository.findBySname(sname);
    }

    @Override
    public List<Student> selectBySageIsGreaterThan(Short sage) {
        return studentRepository.findBySageIsGreaterThan(sage);
    }

    @Override
    public long countStudentBySageIsGreaterThan(Short sage) {
        return studentRepository.countStudentBySageIsGreaterThan(sage);
    }

    @Override
    public List<StudentDto> selectAvgGreaterThanGrade(int grade) {
        return studentRepository.selectAvgGreaterThanGrade(grade);
    }
}
