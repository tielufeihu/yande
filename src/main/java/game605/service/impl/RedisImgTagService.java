package game605.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import game605.bean.ImgTag;
import game605.bean.Imginfo;
import game605.bean.Tag;
import game605.mapper.ImgTagMapper;
import game605.mapper.ImginfoMapper;
import game605.mapper.TagMapper;
import game605.myRedis.RedisService;
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
@Service
@Primary
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
    public List<Integer> getImgsIdFromTags(String[] tags, int page, int sept){
        // 判断tag是否长度为1
        if(tags.length == 0){
            List<Integer> resList = new ArrayList<>();
            QueryWrapper<Imginfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("id").orderByDesc("id").last("Limit " + (page-1)*sept + ", " + sept);
            List<Imginfo> list = iim.selectList(queryWrapper);
            for (Imginfo imginfo: list) {
                resList.add(imginfo.getId());
            }
            return resList;
        }
        else if (tags.length == 1){
            int tagId = ts.getIdFromName(tags[0]);
            return rs.getImgsIdFromTag(tagId,page,sept);
        }
        else{
            List<Integer> tagIdList = new ArrayList<>();
            for (String tagName: tags) {
                tagIdList.add(ts.getIdFromName(tagName));
            }
            return rs.getImgsIdFromTags(tagIdList,page,sept);
        }
    }


    @Override
    @Transactional
    public int addTagToImg(int imgId, int tagId){
        // 加入到redis缓存
        rs.addImgTag(imgId,tagId);
        int re = 1;
        ImgTag imgTag = new ImgTag();
        imgTag.setImgId(imgId);
        imgTag.setTagId(tagId);
        UpdateWrapper<Tag> wrapper = new UpdateWrapper<Tag>();
        wrapper.eq("id",tagId);
        // mybatis-plus 实现字段的自增
        wrapper.setSql("`img_count` = `img_count` + 1");
        re*=tm.update(null,wrapper);
        re*=itm.insert(imgTag);
        return re;
    }

}
