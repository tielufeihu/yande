package game605.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import game605.bean.ImgComment;
import game605.bean.vo.ImgCommentVO;
import game605.service.ImgCommentService;
import game605.mapper.ImgCommentMapper;
import org.apache.ibatis.javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Koyou
* @description 针对表【img_comment(图片评论)】的数据库操作Service实现
* @createDate 2024-07-10 15:54:10
*/
@Service
public class ImgCommentServiceImpl extends ServiceImpl<ImgCommentMapper, ImgComment>
    implements ImgCommentService{

    @Autowired
    private ImgCommentMapper imgCommentMapper;

    /**
     * 评论
     * @param comment
     * @return
     */
    @Override
    public int comment(ImgComment comment) {
        // 设置时间
        comment.setCommentDate(new Date());
        // 保存
        return imgCommentMapper.insert(comment);
    }

    @Override
    public int deleteComment(ImgComment comment) {
        return imgCommentMapper.deleteById(comment.getId());
    }

    @Override
    public List<ImgCommentVO> getImgCommentList(Integer imgId) {
        // 先全部查询出来再处理
        List<ImgCommentVO> all = imgCommentMapper
                .selectList(new QueryWrapper<ImgComment>()
                        .eq("img_id", imgId)
                        .orderByDesc("comment_date"))
                .stream().map(ImgCommentVO::new).collect(Collectors.toList());
        // 查出根节点
        List<ImgCommentVO> roots = all.stream()
                .filter(comment -> comment.getReference() == 0)
                .collect(Collectors.toList());
        // 递归查询子节点
        roots.forEach(root -> root.setReplyList(getChildren(root.getId(), all)));
        return roots;
    }

    private List<ImgCommentVO> getChildren(Integer id, List<ImgCommentVO> all) {
        List<ImgCommentVO> replies = all.stream()
                .filter(comment -> comment.getReference().equals(id))
                .collect(Collectors.toList());
        replies.forEach(reply -> reply.setReplyList(getChildren(reply.getId(), all)));
        return replies;
    }


}




