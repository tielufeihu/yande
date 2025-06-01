package game605.controller;

import game605.bean.Role;
import game605.common.web.ResponseResult;
import game605.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    RoleService rs;

    @RequestMapping("/add")
    public ResponseResult addRole(@RequestParam String roleName, @RequestParam int roleGrade) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleGrade(roleGrade);
        int result = rs.addRole(role);
        return ResponseResult.success(result);
    }

    @RequestMapping("/del")
    public ResponseResult delRole(@RequestParam int roleId) {
        int result = rs.removeRole(roleId);
        return ResponseResult.success(result);
    }

    @RequestMapping("/update")
    public ResponseResult update(@RequestParam int roleId, @RequestParam String roleName, @RequestParam int roleGrade) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        role.setRoleGrade(roleGrade);
        int result = rs.updateRole(role);
        return ResponseResult.success(result);
    }

    @RequestMapping("/list")
    public ResponseResult getList() {
        List<Role> list = rs.getRoleList();
        return ResponseResult.success(list);
    }

    @RequestMapping("/search")
    public ResponseResult searchRole(@RequestParam int id) {
        Role role = rs.searchRole(id);
        return ResponseResult.success(role);
    }

    @RequestMapping("/getInfo")
    public ResponseResult getRole(@RequestParam int id) {
        Role role = rs.searchRole(id);
        return ResponseResult.success(role);
    }
}
