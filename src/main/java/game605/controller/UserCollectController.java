package game605.controller;

import game605.bean.UserCollect;
import game605.bean.web.ResponseResult;
import game605.service.UserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Koyou
 * @version 1.0.0
 * @className UserCollectController
 * @description 用户收藏功能
 * @since 2024/7/11 16:57
 */
@Controller
@RequestMapping("/userCollect")
public class UserCollectController {

    @Autowired
    private UserCollectService userCollectService;

    /**
     * 添加收藏
     * @param userCollect
     * @return
     */
    @PostMapping("/collect")
    public ResponseResult collect(@RequestBody UserCollect userCollect){
        return ResponseResult.success(userCollectService.collect(userCollect));
    }

    /**
     * 取消收藏
     * @param userCollect
     * @return
     */
    @PostMapping("/cancelCollect")
    public ResponseResult cancelCollect(@RequestBody UserCollect userCollect){
        return ResponseResult.success(userCollectService.cancelCollect(userCollect));
    }

    /**
     * 查询收藏
     * @param userCollect
     * @return
     */
    @GetMapping("/queryCollect")
    public ResponseResult queryCollect(UserCollect userCollect){
        return ResponseResult.success(userCollectService.queryCollect(userCollect));
    }


}
