package game605.es;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Test01
 * @description TODO
 * @since 2024/5/20 11:36
 */

public class Test01 {

    @Autowired
    ESBookRepository esBookRepository;

    @Test
    public void t(){
        Book b = new Book();
        b.setId("12");
        b.setAuthor("koyou");
        b.setTitle("标题11");
        b.setPrice(456456.65);
        b.setCreateTime(new Date());
        esBookRepository.save(b);
    }

}
