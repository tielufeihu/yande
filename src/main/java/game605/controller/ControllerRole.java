package game605.controller;

import game605.bean.Role;
import game605.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "*")
public class ControllerRole {

    @Autowired
    RoleService rs;

    @RequestMapping("/add")
    public int addRole(@RequestParam String roleName, @RequestParam int roleGrade){
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleGrade(roleGrade);
        return rs.addRole(role);
    }

    @RequestMapping("/del")
    public int delRole(@RequestParam int roleId){
        return rs.removeRole(roleId);
    }

    @RequestMapping("/update")
    public int update(@RequestParam int roleId ,@RequestParam String roleName, @RequestParam int roleGrade){
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        role.setRoleGrade(roleGrade);
        return rs.updateRole(role);
    }

    @RequestMapping("/list")
    public List<Role> getList(){
        return rs.getRoleList();
    }

    @RequestMapping("/search")
    public Role searchRole(@RequestParam int id){
        return rs.searchRole(id);
    }

    @RequestMapping("/getInfo")
    public Role getRole(@RequestParam int id){
        return rs.searchRole(id);
    }

}
