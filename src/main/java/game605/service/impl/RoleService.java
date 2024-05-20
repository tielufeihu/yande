package game605.service.impl;

import game605.bean.Role;
import game605.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleMapper rm;

    // 添加角色
    public int addRole(Role role){
        return rm.insert(role);
    }

    // 删除角色(检测是否有这个角色的用户 再删除)
    public int removeRole(int id){
        return rm.deleteById(id);
    }

    // 编辑角色
    public int updateRole(Role role){
        return rm.updateById(role);
    }

    // 查找角色
    public Role searchRole(int id){
        return rm.selectById(id);
    }

    // 返回所有角色
    public List<Role> getRoleList(){
        return rm.selectList(null);
    }

    // ......

}
