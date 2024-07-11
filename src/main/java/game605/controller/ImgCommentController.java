package game605.controller;

import game605.bean.ImgComment;
import game605.bean.web.ResponseResult;
import game605.service.ImgCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className CommentController
 * @description TODO
 * @since 2024/7/11 16:24
 */
@RestController
@RequestMapping("/imgComment")
public class ImgCommentController {

    @Autowired
    private ImgCommentService commentService;

    /**
     * 评论
     * @param comment
     * @return
     */
    @PostMapping("/comment")
    public ResponseResult comment(@RequestBody ImgComment comment){
        return ResponseResult.success(commentService.comment(comment));
    }


    /**
     * 删除评论
     */
    @PostMapping("/deleteComment")
    public ResponseResult deleteComment(@RequestBody ImgComment comment){
        return ResponseResult.success(commentService.deleteComment(comment));
    }


    /**
     * 获取图片评论列表
     */
    @GetMapping("/getCommentList")
    public ResponseResult getImgCommentList(@RequestBody Long imgId){
        return ResponseResult.success(commentService.getImgCommentList(imgId));
    }


}
