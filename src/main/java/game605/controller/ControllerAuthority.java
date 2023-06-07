package game605.controller;


import game605.bean.RoleAuthority;
import game605.servicelmpl.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth")
@ResponseBody
@CrossOrigin(origins = "*")
public class ControllerAuthority {

    @Autowired
    AuthorityService as;

    // 为角色r 添加权限 a
    @RequestMapping("/add")
    public int addAuth( @RequestParam int roleId, @RequestParam int authId){
        return as.roleAddAuthority(roleId,authId);
    }

    // 删除角色r 的权限 a
    @RequestMapping("/del")
    public int delAuth(@RequestParam int roleId, @RequestParam int authId){
        return as.delRoleAuthority(roleId,authId);
    }

    // 获取角色r 的所有权限a—list
    @RequestMapping("/get/list")
    public List<Integer> getRoleAuths(@RequestParam int roleId){
        List<Integer> res = new ArrayList<>();
        List<RoleAuthority> rrr = as.getRoleAuths(roleId);
        for (RoleAuthority r: rrr) {
            res.add(r.authorityId);
        }

        return res;
    }


    // authId to authName
    @RequestMapping("/get/name")
    public String getAuthName(@RequestParam int authId){
        return as.getAuthName(authId);
    }


    // authName to authId
    @RequestMapping("/get/id")
    public int getAuthId(@RequestParam String authName){
        return as.getAuthId(authName);
    }


}
