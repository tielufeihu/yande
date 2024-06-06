package game605.es;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

/**
 * @author Koyou
 * @version 1.0.0
 * @className TestES
 * @description TODO
 * @since 2024/6/6 13:47
 */
@SpringBootTest
public class TestES {

    @Autowired
    private ElasticsearchTemplate elasticsearchRestTemplate;

    @Test
    public void test(){
    }

}
