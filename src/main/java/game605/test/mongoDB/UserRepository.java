package game605.test.mongoDB;

import game605.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *   MongoRepository<T, ID>
 *   其中T 是collection对应的实体类
 *   ID是 collection的主键类型
 */
@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    List<User> findByName(String name);  // 需要符合Spring Data规范
}
