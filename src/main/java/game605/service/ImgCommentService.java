package game605.service;

import game605.bean.ImgComment;
import com.baomidou.mybatisplus.extension.service.IService;
import game605.bean.vo.ImgCommentVO;

import java.util.List;

/**
* @author Koyou
* @description 针对表【img_comment(图片评论)】的数据库操作Service
* @createDate 2024-07-10 15:54:10
*/
public interface ImgCommentService extends IService<ImgComment> {

    /**
     * 评论
     * @param comment
     * @return
     */
    int comment(ImgComment comment);

    /**
     * 删除评论
     * @param comment
     * @return
     */
    int deleteComment(ImgComment comment);

    /**
     * 获取评论列表
     * @param imgId
     * @return
     */
    List<ImgCommentVO> getImgCommentList(Integer imgId);
}
