import com.haqiu.contract.demo.UserFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/9/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class HessianTest {

    @Autowired
    private UserFacade userService;

    @Test
    public void test() {



        userService.addUser();
        userService.updateUser();
        userService.delUser();
        String user = userService.findUser("ZhangSan");
        System.out.println(user);
        System.out.println("---------------------------------finished----------------------------------");
    }


}
