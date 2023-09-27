package game605.test.mongoDB;

import game605.bean.User;
import org.junit.Test;
import org.python.antlr.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConnTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository repository;


    public void testSave(){
        User user = new User();
        user.setName("张三");
        user.setPassword("123123");
        user.setRoleId(123123);
        for (int i = 0; i < 100; i++) {
            user.setAccount(i);
            User ret = mongoTemplate.insert(user);
        }
    }

    public void testGet(){
        User u = mongoTemplate.findById("65138946b7c45122c88d0ed3", User.class);
        System.out.println(u);
        Query query = new Query(Criteria
                .where("name").is("张三"))
                .skip(40).limit(15);   // 从第二页开始， 拿两条数据
        System.out.println(mongoTemplate.find(query, User.class));
        List<User> users = mongoTemplate.findAll(User.class);
        System.out.println(users);
    }

    public void testRemove(){
        repository.deleteById(50); // 删除成功
        repository.deleteAll(); // ok

    }

    public void testFindByName(String name){
        System.out.println(repository.findByName(name));  // ok

    }


}
