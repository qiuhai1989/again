import com.haqiu.core.dto.StudentDto;
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
    @Test
    public void testFindBySname(){
        Student student = studentManager.findBySname("陈伟东");
        System.out.println(student.getSname()+"---"+student.getSage());
    }
    @Test
    public void testSelectBySageIsGreaterThan(){
        List<Student> students = studentManager.selectBySageIsGreaterThan((short) 25);
        for(Student stu:students){
            System.out.println(stu.getSname()+"---"+stu.getSage());
        }
    }
    @Test
    public void testCountBySageIsGreaterThan(){
        long num = studentManager.countStudentBySageIsGreaterThan((short) 25);
        System.out.println("年纪大于25以上人数为："+num);
    }
    @Test
    public void testList(){
        List<StudentDto> list = studentManager.selectAvgGreaterThanGrade(20);
        for(StudentDto dto:list){
            System.out.println(dto.getSname()+"---"+dto.getSno()+"---"+dto.getAvg());
        }

    }

}
