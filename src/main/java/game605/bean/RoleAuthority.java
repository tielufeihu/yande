package game605.bean;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_authority")
@ToString
public class RoleAuthority {

    private int roleId;
    private int authorityId;

}
