package game605.controller;

import game605.bean.RoleAuthority;
import game605.servicelmpl.AuthorityService;
import net.bytebuddy.description.field.FieldDescription;
import org.python.antlr.op.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class ControllerAuth {

    @Autowired
    AuthorityService as;

    // 为角色x 添加权限a
    @RequestMapping("/addAuth")
    public int addAuth(int roleId, int authId){
        return as.roleAddAuthority(roleId,authId);
    }

    // 为角色x 移除权限a
    @RequestMapping("/delAuth")
    public int delAuth(int roleId, int authId){
        return as.delRoleAuthority(roleId,authId);
    }


    // 获得角色x 所有权限
    @RequestMapping("/getRoleAuths")
    public List<Integer> getRoleAuths(int roleId){
        List<RoleAuthority> list = as.getRoleAuths(roleId);
        List<Integer> list1 = new ArrayList<>();
        for (RoleAuthority r: list) {
            list1.add(r.getAuthorityId());
        }
        return list1;
    }


}
