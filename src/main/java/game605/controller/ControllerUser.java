package game605.controller;

import game605.bean.User;
import game605.bean.web.ResponseResult;
import game605.service.impl.TokenService;
import game605.service.impl.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class ControllerUser {

    @Autowired
    UserService us;

    @Autowired
    TokenService ts;

    // 登录：成功返回 token，失败返回带 msg 的 error 响应
    @RequestMapping("/login")
    public ResponseResult login(@RequestParam int account, @RequestParam String password) {
        Long token = us.login(account, password);
        if (token.equals(-2L)) {
            return new ResponseResult(412, "用户不存在");
        }
        if (token.equals(-1L)) {
            return new ResponseResult(412, "密码错误");
        }
        return ResponseResult.success(token);
    }

    // 获取自身信息：token非法返回错误提示
    @RequestMapping("/getSelfInfo")
    public ResponseResult getSelfInfo(@RequestParam String token) {
        try {
            Long l_token = Long.valueOf(token);
            User user = us.tokenGetUserInfo(l_token);
            return ResponseResult.success(user);
        } catch (Exception e) {
            return new ResponseResult(412, "获取个人信息失败，token无效");
        }
    }

    // 判断 token 是否有效，返回 userId 或 -1
    @RequestMapping("/isLogin")
    public ResponseResult isLogin(@RequestParam Long token) {
        int userId = ts.checkTokenAndGetUserId(token);
        return ResponseResult.success(userId);
    }

    // 刷新 token 时间
    @RequestMapping("/refreshTokenTime")
    public ResponseResult refreshTokenTime(@RequestParam Long token) {
        int result = ts.refreshTokenTime(token);
        return ResponseResult.success(result);
    }

    // 注册用户
    @RequestMapping("/register")
    public ResponseResult register(@RequestParam String password, @RequestParam String name) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRoleId(2); // 默认普通用户角色
        int result = us.addUser(user);
        return ResponseResult.success(result);
    }
}
