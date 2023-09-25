package game605.servicelmpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import game605.bean.ImgTag;
import game605.bean.Imginfo;
import game605.bean.Tag;
import game605.mapper.ImgTagMapper;
import game605.mapper.ImginfoMapper;
import game605.mapper.TagMapper;
import game605.myRedis.RedisService;
import org.junit.Test;
import org.python.antlr.op.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImgTagService {

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


    @PostConstruct
    public void init(){
        // TODO
        /*
          初始化 一个Tag Count map
         */
    }


    //查询 某个 img的所有tag
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

    //查询 图片分页  弃用
//    public List<String> getImgs(int page,int sept){
//        QueryWrapper<ImgTag> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .select("img_id")
//                .orderByDesc("img_id + 1")    //此排序耗时很长
//                .last("Limit " + (page-1)*sept + ", " + sept);
//        List<ImgTag> itlist = itm.selectList(queryWrapper);
//        List<String> imgIdList = new ArrayList<>();
//        for (ImgTag t:itlist) {
//            imgIdList.add(t.getImgId());
//        }
//        return imgIdList;
//    }

    //查询 具有某个tag的所有imgId
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

    //查询 具有某个tag的所有imgId  旧的
//    public List<Integer> getImgsIdFromTag(int tagId,int page,int sept){
//        QueryWrapper<ImgTag> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .eq("tag_id",tagId)
//                .select("img_id")
//                .orderByDesc("img_id")
//                .last("Limit " + (page-1)*sept + ", " + sept);
//        List<ImgTag> itlist = itm.selectList(queryWrapper);
//        List<Integer> imgIdList = new ArrayList<>();
//        for (ImgTag t:itlist) {
//            imgIdList.add(t.getImgId());
//        }
//        return imgIdList;
//    }

    // 新的使用 redis
    public List<Integer> getImgsIdFromTag(int tagId,int page,int sept){
        return rs.getImgsIdFromTag(tagId,page,sept);
    }

    //查询 具有多个 tag的 imgId  ok  旧的
//    public List<Integer> getImgsIdFromTags(String[] tags,int page,int sept){
//        QueryWrapper<ImgTag> queryWrapper = new QueryWrapper<>();
//        //SELECT img_id FROM img_tag WHERE tag_id IN (82, 196) GROUP BY img_id HAVING COUNT(*) = 2
//        StringBuilder insql = new StringBuilder();
//        for (String tag:tags) {
//            insql.append(ts.getIdFromName(tag));
//            insql.append(",");
//        }
//        insql.deleteCharAt(insql.length()-1);
//        queryWrapper
//                .select("img_id")
//                .orderByDesc("img_id")
//                .groupBy("img_id")
//                .having("COUNT(*) = " + tags.length)
//                .inSql("tag_id",insql.toString())  // "82, 196"
//                .last("Limit " + (page-1)*sept + ", " + sept);
//        List<ImgTag> itlist = itm.selectList(queryWrapper);
//        List<Integer> imgIdList = new ArrayList<>();
//        for (ImgTag t:itlist) {
//            imgIdList.add(t.getImgId());
//        }
//        return imgIdList;
//    }

    // 新的 使用redis
    public List<Integer> getImgsIdFromTags(String[] tags,int page,int sept){
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

    // 为图片添加一个tag
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
        wrapper.setSql("`img_count` = `img_count` + 1");  // mybatis-plus 实现字段的自增
        re*=tm.update(null,wrapper);
        re*=itm.insert(imgTag);
        return re;
    }

}
