package game605.controller;

import game605.bean.User;
import game605.common.web.ResponseResult;
import game605.service.impl.TokenService;
import game605.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService us;

    @Autowired
    TokenService ts;

    // 登录：成功返回 token，失败返回带 msg 的 error 响应
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        Long token = us.login(user.getAccount(), user.getPassword());
        if (token.equals(-2L)) {
            return new ResponseResult(412, "用户不存在");
        }
        if (token.equals(-1L)) {
            return new ResponseResult(412, "密码错误");
        }
        return ResponseResult.success("Bearer " + token);
    }

    // 获取自身信息：token非法返回错误提示
    @GetMapping("/getSelfInfo")
    public ResponseResult getSelfInfo(@RequestParam String token) {
        try {
            // 去除Bearer
            token = token.substring(7);
            Long l_token = Long.valueOf(token);
            User user = us.tokenGetUserInfo(l_token);
            return ResponseResult.success(user);
        } catch (Exception e) {
            return new ResponseResult(412, "获取个人信息失败，token无效");
        }
    }

    // 判断 token 是否有效，返回 userId 或 -1
    @GetMapping("/isLogin")
    public ResponseResult isLogin(@RequestParam String token) {
        token = token.substring(7);
        Long lToken = Long.valueOf(token);
        int userId = ts.checkTokenAndGetUserId(lToken);
        return ResponseResult.success(userId);
    }

    // 刷新 token 时间
    @PostMapping("/refreshTokenTime")
    public ResponseResult refreshTokenTime(@RequestBody String token) {
        token = token.substring(7);
        Long lToken = Long.valueOf(token);
        int result = ts.refreshTokenTime(lToken);
        return ResponseResult.success(result);
    }

    // 注册用户
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        user.setRoleId(2); // 默认普通用户角色
        int result = us.addUser(user);
        return ResponseResult.success(result);
    }
}
