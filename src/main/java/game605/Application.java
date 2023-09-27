package game605;

import game605.servicelmpl.ImgInfoService;
import game605.test.mongoDB.ConnTest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.DispatcherServlet;


@SpringBootApplication
@EnableScheduling
//@ImportResource(locations = "classpath:beans.xml")
@MapperScan("game605.mapper")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        ConnTest t = run.getBean(ConnTest.class);
        //t.testSave();
        //t.testGet();

        t.testFindByName("李四");

    }

}
