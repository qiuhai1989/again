package com.haqiu.core.mongo.impl;

import com.haqiu.core.entity.Student;
import com.haqiu.core.mongo.StudentMongoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/24.
 */
@Service("studentMongoDAO")
public class StudentMongoDAOImpl extends BaseMongoDAOImpl<Student> implements StudentMongoDAO {

    @Autowired
    @Override
    protected void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
