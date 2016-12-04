package spittr.alerts;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spittr.domain.Spittle;

public class SpittleJmsMain {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-jms.xml");
    AlertService alertService = context.getBean(AlertService.class);
    
    Spittle spittle = new Spittle(1L, null, "Hello", new Date());
    alertService.sendSpittleAlert(spittle);

    /**
     * 如果消息已经被别的地方获取则此处就会获取不到而一直阻塞 ,例如 配置了监听器的情况
     */
    Spittle receive = alertService.retrieveSpittleAlert();
    System.out.println("同步调用方式获取"+receive.getMessage());
    
  }

}
