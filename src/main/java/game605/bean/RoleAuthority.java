package game605.bean;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 角色权限关联类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_authority")
@ToString
public class RoleAuthority {

    private Integer roleId;
    private Integer authorityId;

}
