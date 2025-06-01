package game605.controller;

import game605.bean.Auth;
import game605.bean.RoleAuthority;
import game605.bean.web.ResponseResult;
import game605.service.impl.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthorityController {

    @Autowired
    AuthorityService as;

    /**
     * 为角色添加权限
     */
    @PostMapping("/add")
    public ResponseResult addAuth(@RequestBody RoleAuthority ra) {
        int result = as.roleAddAuthority(ra.getRoleId(), ra.getAuthorityId());
        return ResponseResult.success(result);
    }

    /**
     * 删除角色的权限
     */
    @PostMapping("/del")
    public ResponseResult delAuth(@RequestBody RoleAuthority ra) {
        int result = as.delRoleAuthority(ra.getRoleId(), ra.getAuthorityId());
        return ResponseResult.success(result);
    }

    /**
     * 获取角色的所有权限列表
     */
    @GetMapping("/getRoleAuthList")
    public ResponseResult getRoleAuths(@RequestParam Integer roleId) {
        List<RoleAuthority> ret = as.getRoleAuths(roleId);
        return ResponseResult.success(ret);
    }

    /**
     * 获取所有权限的详细信息
     */
    @GetMapping("/getAuthList")
    public ResponseResult getAuthsInfo() {
        List<Auth> list = as.getAuthsInfo();
        return ResponseResult.success(list);
    }


}
