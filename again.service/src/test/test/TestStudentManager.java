package test;

import com.haqiu.core.entity.Student;
import com.haqiu.core.service.StudentManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestStudentManager {

    @Autowired
    private StudentManager studentManager;

    @Test
    public void testListAll(){
        List<Student> list = studentManager.findAll();
        for(Student stu:list){
            System.out.println(stu.getSname()+"---"+stu.getSsex());
        }
    }

}
