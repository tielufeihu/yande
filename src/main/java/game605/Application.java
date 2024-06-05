package game605;

import game605.es.Book;
import game605.es.ESBookRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;


@SpringBootApplication
@EnableScheduling
@MapperScan("game605.mapper")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        //ESBookRepository esBookRepository = run.getBean(ESBookRepository.class);
        //Book b = new Book();
        //b.setId("12");
        //b.setAuthor("koyou");
        //b.setTitle("标题11");
        //b.setPrice(456456.65);
        //b.setCreateTime(new Date());
        //esBookRepository.save(b);
    }

}
