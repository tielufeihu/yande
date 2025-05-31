package game605.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import game605.bean.ImgTag;
import game605.bean.Imginfo;
import game605.bean.Tag;
import game605.constants.CommonConstant;
import game605.mapper.ImgTagMapper;
import game605.mapper.ImginfoMapper;
import game605.mapper.TagMapper;
import game605.redis.RedisService;
import game605.service.IImgTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * tag搜索 -- redis实现
 * @author Koyou
 */
@Primary
@Service
public class RedisImgTagService implements IImgTagService {

    @Autowired
    DBImgTagService dbImgTagService;

    @Autowired
    ImgTagMapper itm;

    @Autowired
    ImginfoMapper iim;

    @Autowired
    TagService ts;

    @Autowired
    TagMapper tm;

    @Autowired
    RedisService rs;


    @Override
    public List<Tag> getImgTagsFromId(int imgId){
        return dbImgTagService.getImgTagsFromId(imgId);
    }


    @Override
    public List<Integer> getImgsIdFromTag(int tagId){
        return dbImgTagService.getImgsIdFromTag(tagId);
    }


    @Override
    public List<Integer> getImgsIdFromTag(int tagId, int page, int sept){
        return rs.getImgsIdFromTag(tagId,page,sept);
    }


    @Override
    public List<Integer> getImgsIdFromTags(List<Integer> tagIds, int page, int sept){
        // 判断tag是否长度为1
        if(tagIds.isEmpty()){
            List<Integer> resList = new ArrayList<>();
            QueryWrapper<Imginfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("id").orderByDesc("id").last("Limit " + (page-1)*sept + ", " + sept);
            List<Imginfo> list = iim.selectList(queryWrapper);
            for (Imginfo imginfo: list) {
                resList.add(imginfo.getId());
            }
            return resList;
        }
        else if (tagIds.size() == 1){
            int tagId = tagIds.getFirst();
            return rs.getImgsIdFromTag(tagId,page,sept);
        }
        else{
            return rs.getImgsIdFromTags(tagIds,page,sept);
        }
    }


    @Override
    @Transactional
    public int addTagToImg(Imginfo img){
        Integer imgId = img.getId();
        List<ImgTag> tagList = img.getImgTags();
        for (ImgTag imgTag : tagList) {
            // 加入到redis缓存
            rs.addImgTag(imgId,imgTag.getTagId());
            imgTag.setImgId(imgId);
            UpdateWrapper<Tag> wrapper = new UpdateWrapper<>();
            wrapper.eq("id",imgTag.getTagId());
            // mybatis-plus 实现字段的自增
            wrapper.setSql("`img_count` = `img_count` + 1");
            tm.update(null,wrapper);
            itm.insert(imgTag);
        }
        return CommonConstant.SUCCESS;
    }

}
