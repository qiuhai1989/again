package test;

import com.haqiu.core.entity.Student;
import com.haqiu.core.mongo.StudentMongoDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestStudentMongoDAO {

    @Autowired
    private StudentMongoDAO studentMongoDAO;

    @Test
    public void testSave(){
        Student student = new Student();
        student.setSage((short) 15);
        student.setSdree("琶洲会展中心");
        student.setSname("韩梅梅");
        student.setSno(1L);
        student.setSsex("女");
        studentMongoDAO.save(student);
    }

}
