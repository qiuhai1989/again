import com.haqiu.core.utils.VelocityMailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestMail {

    @Autowired
    private VelocityMailUtil welcomeMailTemplate;

    @Test
    public void testSend(){
        Map<String, Object> map = new HashMap<>();
        map.put("username", "小李");
        map.put("password", "1qaz@WSX");

        welcomeMailTemplate.sendMime("qiuhai1989@163.com", map);
        System.out.println("发送结束");
    }

}
