package game605.servicelmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import game605.Application;
import game605.bean.User;
import game605.mapper.UserMapper;
import game605.myRedis.RedisService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Service
public class UserService {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    UserMapper um;

    @Autowired
    TokenService ts;

    @Autowired
    AuthorityService as;

    // 登录
    public Long login(int account, String password){
        // 检测 账号密码 是否正确
        User user = searchUser(account);
        if(user == null)
        {
            // 用户不存在  -2
            return (long)-2;
        }
        if(user.getPassword().equals(password)){
            // 用户存在
            return ts.getTokenAndToRedis(account);
        }else
        {
            // 用户存在但是密码不正确
            return (long)-1;
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

    @Test
    public void test01() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        Class userClass = Class.forName("game605.bean.User");
        Field[] fieldArray = userClass.getFields();
        for(Field f : fieldArray){
            System.out.println(f);
        }
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = userClass.getDeclaredFields();
        for(Field f : fieldArray){
            System.out.println(f);
        }
        System.out.println("*************获取公有字段**并调用***********************************");
        Field f = userClass.getDeclaredField("name");
        System.out.println(f);
        f.setAccessible(true);//暴力反射，解除私有限定
        //获取一个对象
        Object obj = userClass.getConstructor().newInstance();//产生Student对象--》Student stu = new Student();
        //为字段设置值
        f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
        //验证
        User user = (User)obj;
        System.out.println("验证姓名：" + user.getName());


        System.out.println("**************获取私有字段****并调用********************************");
        f = userClass.getDeclaredField("password");
        System.out.println(f);
        f.setAccessible(true);//暴力反射，解除私有限定
        f.set(obj, "18888889999");
        System.out.println("验证密码：" + user);

    }

}
