package game605.controller;

import game605.bean.UserCollect;
import game605.bean.web.ResponseResult;
import game605.service.UserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className UserCollectController
 * @description 用户收藏功能
 * @since 2024/7/11 16:57
 */
@RestController
@RequestMapping("/userCollect")
public class UserCollectController {

    @Autowired
    private UserCollectService userCollectService;

    /**
     * 添加收藏
     */
    @PostMapping("/collect")
    public ResponseResult collect(@RequestBody UserCollect userCollect){
        return ResponseResult.success(userCollectService.collect(userCollect));
    }

    /**
     * 取消收藏
     */
    @PostMapping("/cancelCollect")
    public ResponseResult cancelCollect(@RequestBody UserCollect userCollect){
        return ResponseResult.success(userCollectService.cancelCollect(userCollect));
    }

    /**
     * 查询收藏
     */
    @GetMapping("/queryCollect")
    public ResponseResult queryCollect(@RequestParam Integer userId , @RequestParam Integer imgId, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        return ResponseResult.success(userCollectService.queryCollect(userId, imgId, pageNum, pageSize));
    }


}
