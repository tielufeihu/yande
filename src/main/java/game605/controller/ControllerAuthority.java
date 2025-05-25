package game605.controller;

import game605.bean.Auth;
import game605.bean.RoleAuthority;
import game605.bean.web.ResponseResult;
import game605.service.impl.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class ControllerAuthority {

    @Autowired
    AuthorityService as;

    // 为角色添加权限
    @RequestMapping("/add")
    public ResponseResult addAuth(@RequestParam int roleId, @RequestParam int authId) {
        int result = as.roleAddAuthority(roleId, authId);
        return ResponseResult.success(result);
    }

    // 删除角色的权限
    @RequestMapping("/del")
    public ResponseResult delAuth(@RequestParam int roleId, @RequestParam int authId) {
        int result = as.delRoleAuthority(roleId, authId);
        return ResponseResult.success(result);
    }

    // 获取角色的所有权限 ID 列表
    @RequestMapping("/get/list")
    public ResponseResult getRoleAuths(@RequestParam int roleId) {
        List<Integer> res = new ArrayList<>();
        List<RoleAuthority> rrr = as.getRoleAuths(roleId);
        for (RoleAuthority r : rrr) {
            res.add(r.getAuthorityId());
        }
        return ResponseResult.success(res);
    }

    // 获取所有权限的详细信息
    @RequestMapping("/get/info")
    public ResponseResult getAuthsInfo() {
        List<Auth> list = as.getAuthsInfo();
        return ResponseResult.success(list);
    }

    // 根据 authId 获取权限名称
    @RequestMapping("/get/name")
    public ResponseResult getAuthName(@RequestParam int authId) {
        String name = as.getAuthName(authId);
        return ResponseResult.success(name);
    }

    // 根据权限名称获取 authId
    @RequestMapping("/get/id")
    public ResponseResult getAuthId(@RequestParam String authName) {
        int id = as.getAuthId(authName);
        return ResponseResult.success(id);
    }
}
