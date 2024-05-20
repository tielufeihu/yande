package game605.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import game605.Application;
import game605.bean.User;
import game605.mapper.UserMapper;
import game605.myRedis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    UserMapper um;

    @Autowired
    TokenService ts;

    @Autowired
    AuthorityService as;

    @Autowired
    RedisService rs;

    // 登录
    public Long login(int account, String password){
        // 检测 账号密码 是否正确
        User user = searchUser(account);
        if(user == null)
        {
            return (long)-2;  // 用户不存在  -2
        }
        if(user.getPassword().equals(password)){
            return ts.getTokenAndToRedis(account);   // 用户存在  且密码正确返回相应的token
        }else
        {
            return (long)-1;  // 用户存在但是密码不正确
        }
    }

    // 添加用户(注册)
    public int addUser(User user){
        return um.insert(user);
    }

    // 删除用户
    public int removeUser(int id){
        return um.deleteById(id);
    }

    // 修改用户
    public int updateUser(User user){
        return um.updateById(user);
    }

    // 查找用户
    public User searchUser(int id){
        return um.selectById(id);
    }

    // 设置用户角色
    public int setUserRole(int userId, int roleId){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("account", userId);
        updateWrapper.set("role_id", roleId);
        return um.update(null,updateWrapper);
    }

    // 检测用户user是否有权限x
    public boolean userIfAuth(int userId, int authId){
        //获取用户角色Id
        User user = um.selectById(userId);
        System.out.println(user);
        return as.roleIfAuth(user.getRoleId(), authId);
    }

    public User tokenGetUserInfo(Long token){
        int userId = ts.checkTokenAndGetUserId(token);
        if(userId == -1)
            return null;
        return searchUser(userId);
    }

}
