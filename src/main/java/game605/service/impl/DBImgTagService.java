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
import game605.service.IImgTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className DBImgTagService
 * @description tag搜索 -- DB实现
 * @since 2024/5/19 16:31
 */
@Service
public class DBImgTagService implements IImgTagService {

    @Autowired
    ImgTagMapper itm;

    @Autowired
    ImginfoMapper iim;

    @Autowired
    TagService ts;

    @Autowired
    TagMapper tm;


    @Override
    public List<Tag> getImgTagsFromId(int imgId){
        QueryWrapper<ImgTag> queryWrapper = new QueryWrapper();
        queryWrapper.eq("img_id",imgId);
        List<ImgTag> tags = itm.selectList(queryWrapper);
        List<Tag> reTags = new ArrayList<>();
        for(ImgTag t: tags){
            Tag ttag = ts.getTagFromId(t.getTagId());
            reTags.add(ttag);
        }
        return reTags;
    }


    @Override
    public List<Integer> getImgsIdFromTag(int tagId){
        QueryWrapper<ImgTag> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("tag_id",tagId)
                .select("img_id")
                .orderByDesc("img_id");
        List<ImgTag> itlist = itm.selectList(queryWrapper);
        List<Integer> imgIdList = new ArrayList<>();
        for (ImgTag t:itlist) {
            imgIdList.add(t.getImgId());
        }
        return imgIdList;
    }

    @Override
    public List<Integer> getImgsIdFromTag(int tagId, int page, int sept){
        QueryWrapper<ImgTag> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("tag_id",tagId)
                .select("img_id")
                .orderByDesc("img_id")
                .last("Limit " + (page-1)*sept + ", " + sept);
        List<ImgTag> itlist = itm.selectList(queryWrapper);
        List<Integer> imgIdList = new ArrayList<>();
        for (ImgTag t:itlist) {
            imgIdList.add(t.getImgId());
        }
        return imgIdList;
    }


    @Override
    public List<Integer> getImgsIdFromTags(List<Integer> tagIds, int page, int sept){
        QueryWrapper<ImgTag> queryWrapper = new QueryWrapper<>();
        //SELECT img_id FROM img_tag WHERE tag_id IN (82, 196) GROUP BY img_id HAVING COUNT(*) = 2
        StringBuilder insql = new StringBuilder();
        for (Integer tagId:tagIds) {
            insql.append(tagId);
            insql.append(",");
        }
        insql.deleteCharAt(insql.length()-1);
        queryWrapper
                .select("img_id")
                .orderByDesc("img_id")
                .groupBy("img_id")
                .having("COUNT(*) = " + tagIds.size())
                 // "82, 196"
                .inSql("tag_id",insql.toString())
                .last("Limit " + (page-1)*sept + ", " + sept);
        List<ImgTag> itlist = itm.selectList(queryWrapper);
        List<Integer> imgIdList = new ArrayList<>();
        for (ImgTag t:itlist) {
            imgIdList.add(t.getImgId());
        }
        return imgIdList;
    }


    @Override
    @Transactional
    public int addTagToImg(Imginfo img){
        Integer imgId = img.getId();
        List<ImgTag> imgTags = img.getImgTags();
        for (ImgTag t: imgTags) {
            t.setImgId(imgId);
            UpdateWrapper<Tag> wrapper = new UpdateWrapper<Tag>();
            wrapper.eq("id",t.getTagId());
            // mybatis-plus 实现字段的自增
            wrapper.setSql("`img_count` = `img_count` + 1");
            tm.update(null,wrapper);
            itm.insert(t);
        }
        return CommonConstant.SUCCESS;
    }

}
