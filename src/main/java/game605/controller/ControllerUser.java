package game605.controller;

import game605.bean.User;
import game605.service.impl.TokenService;
import game605.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class ControllerUser {

    @Autowired
    UserService us;

    @Autowired
    TokenService ts;

    // 登录  成功返回Token  失败返回   用户不存在：-2 ||  密码错误：-1
    @RequestMapping("/login")
    public Long login(@RequestParam int account, @RequestParam String password, HttpServletResponse response) throws IOException {
        Long token = us.login(account,password);
        if(token.equals((long)-2)){
            response.sendError(412,"user nonentity！");
        }
        if(token.equals((long)-1)){
            response.sendError(412,"wrong password！");
        }
        return token;
    }

    @RequestMapping("/getSelfInfo")
    public User getSelfInfo(@RequestParam String token, HttpServletResponse response) throws IOException {
        try {
            Long l_token = Long.valueOf(token);
            return us.tokenGetUserInfo(l_token);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("token 不合法！");
            response.sendError(412, "获取个人信息失败！ token失效或token错误，请重新登录！");
            return null;
        }
    }

    /**
     * 判断 这个token是否登录，是否有效
     * 如果有效返回 userId
     * 无效返回 -1
     * @param token 令牌
     * @return  success： userId  ||  error：-1
     */
    @RequestMapping("/isLogin")
    public int isLogin(@RequestParam Long token){
        return ts.checkTokenAndGetUserId(token);
    }


    @RequestMapping("/refreshTokenTime")
    public int refreshTokenTime(@RequestParam Long token){
        return ts.refreshTokenTime(token);
    }

    @RequestMapping("/register")
    public int register(@RequestParam String password, @RequestParam String name){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRoleId(2);  // 默认为用户
        return us.addUser(user);
    }


}
