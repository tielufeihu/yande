package game605.bean;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_authority")
public class RoleAuthority {

    public int roleId;
    public int authorityId;

}
