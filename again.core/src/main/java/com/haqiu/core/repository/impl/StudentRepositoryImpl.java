package com.haqiu.core.repository.impl;

import com.haqiu.core.dto.StudentDto;
import com.haqiu.core.repository.StudentCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by H-QIU on 2016/11/20.
 */
public class StudentRepositoryImpl implements StudentCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StudentDto> selectAvgGreaterThanGrade(int grade) {
        StringBuilder sb = new StringBuilder();
        sb.append("select student.sno,student.sname,avg(score.grade) ").append("from t_student student JOIN t_score score ")
                .append(" on student.sno = score.sno ").append(" group by student.sno,student.sname")
                .append(" HAVING(avg(score.grade)>60)");
        Query query = entityManager.createNativeQuery(sb.toString());
        List<Object[]> resultList = query.getResultList();
        List<StudentDto> list = new ArrayList<>();
        for (Object[] obj : resultList) {
            StudentDto studentDto = new StudentDto();
            studentDto.setSno(((BigInteger)obj[0]).longValue());
            studentDto.setSname((String) obj[1]);
            studentDto.setAvg(((Double)obj[2]).floatValue());
            list.add(studentDto);
        }
        return list;
    }
}
