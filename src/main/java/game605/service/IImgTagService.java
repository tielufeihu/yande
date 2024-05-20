package game605.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import game605.bean.ImgTag;
import game605.bean.Imginfo;
import game605.bean.Tag;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className IImgTagService
 * @description 图片检索接口类
 * @since 2024/5/19 16:21
 */
public interface IImgTagService {


    /**
     * 查询 某个 img的所有tag
     * @param imgId
     * @return 该img的tags
     */
    public List<Tag> getImgTagsFromId(int imgId);


    /**
     * 查询 具有某个tag的所有imgId
     * @param tagId
     * @return 具有该tag的imgs
     */
    public List<Integer> getImgsIdFromTag(int tagId);


    /**
     * 查询 具有某个tag的所有imgId  旧的
     * @param tagId tagId
     * @param page 页号
     * @param sept 步长
     * @return
     */
    public List<Integer> getImgsIdFromTag(int tagId,int page,int sept);


    /**
     * 查询 具有多个 tag的 imgId
     * @param tags tag列表
     * @param page 页号
     * @param sept 步长
     * @return
     */
    public List<Integer> getImgsIdFromTags(String[] tags,int page,int sept);

    /**
     * 为图片添加一个tag
     * @param imgId
     * @param tagId
     * @return
     */
    public int addTagToImg(int imgId, int tagId);

}
