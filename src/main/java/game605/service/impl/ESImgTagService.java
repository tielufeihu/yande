package game605.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import game605.bean.ImgTag;
import game605.bean.Tag;
import game605.es.ESImgRepository;
import game605.mapper.ImgTagMapper;
import game605.mapper.TagMapper;
import game605.redis.RedisService;
import game605.service.IImgTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ESImgTagService
 * @description 图片tag搜素 -- ES实现
 * @since 2024/5/19 16:38
 */
@Service
@Primary
public class ESImgTagService implements IImgTagService {

    @Autowired
    DBImgTagService dbImgTagService;

    @Autowired
    ImgTagMapper itm;

    @Autowired
    TagService ts;

    @Autowired
    RedisService rs;

    @Autowired
    TagMapper tm;

    @Autowired
    ESImgRepository esImgRepository;

    @Override
    public List<Tag> getImgTagsFromId(int imgId) {
        return dbImgTagService.getImgTagsFromId(imgId);
    }

    @Override
    public List<Integer> getImgsIdFromTag(int tagId) {
        return dbImgTagService.getImgsIdFromTag(tagId);
    }

    @Override
    public List<Integer> getImgsIdFromTag(int tagId, int page, int sept) {
        return rs.getImgsIdFromTag(tagId,page,sept);
    }

    /**
     * 先使用ES 实现多tag查询的方法
     * @param tags tag列表
     * @param page 页号
     * @param sept 步长
     * @return
     */
    @Override
    public List<Integer> getImgsIdFromTags(String[] tags, int page, int sept) {
        List<String> tagNames = new ArrayList<>();
        for (String tag : tags) {
            // 从数据库查询
            tagNames.add(tm.getTagByName(tag).getName());
        }
        return esImgRepository.getImgsIdFromTags(tagNames, PageRequest.of(page,sept)).toList();
    }

    @Override
    public int addTagToImg(int imgId, int tagId) {
        // 加入到redis缓存
        rs.addImgTag(imgId,tagId);
        int re = 1;
        ImgTag imgTag = new ImgTag();
        imgTag.setImgId(imgId);
        imgTag.setTagId(tagId);
        UpdateWrapper<Tag> wrapper = new UpdateWrapper<Tag>();
        wrapper.eq("id",tagId);
        wrapper.setSql("`img_count` = `img_count` + 1");  // mybatis-plus 实现字段的自增
        re*=tm.update(null,wrapper);
        re*=itm.insert(imgTag);
        return re;
    }

}
