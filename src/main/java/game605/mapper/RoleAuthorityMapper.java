package game605.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import game605.bean.RoleAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleAuthorityMapper extends BaseMapper<RoleAuthority> {
}
