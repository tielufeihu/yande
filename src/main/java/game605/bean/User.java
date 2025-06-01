package game605.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
@ToString
public class User {

    @TableId
    private Integer account;
    private String password;
    private String name;
    private Integer roleId;

}
