package game605.bean.vo;

import game605.bean.ImgComment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ImgCommentVO
 * @description 评论返回列表VO
 * @since 2024/7/11 16:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImgCommentVO extends ImgComment{

    private List<ImgCommentVO> replyList;
    private Integer replyCount;

    public ImgCommentVO(ImgComment imgComment) {
        this.setId(imgComment.getId());
        this.setImgId(imgComment.getImgId());
        this.setUserId(imgComment.getUserId());
        this.setContent(imgComment.getContent());
        this.setReference(imgComment.getReference());
        this.setCommentDate(imgComment.getCommentDate());
    }

    public ImgCommentVO() {}


}
