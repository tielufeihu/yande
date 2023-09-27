package game605.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
@ToString
@Document("User")
public class User {

    @TableId
    @Id           // 这个注解会将 account 映射到 mongoDB 的主键 _id 上
    private int account;
    private String password;
    private String name;
    private int roleId;

}
