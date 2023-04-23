package game605.servicelmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import game605.bean.Tag;
import game605.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    TagMapper tm;

    // 根据tag id获取 Tag
    public Tag getTagFromId(int tagId){
        return tm.selectById(tagId);
    }

    //  根据tag name 获取 tag id
    public int getIdFromName(String name){
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return tm.selectOne(queryWrapper).getId();
    }

    // 获取TagList根据TagCount排序
    public List<Tag> getTagsPageOrderCount(int page, int sept){
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("img_count").last("Limit " + (page-1)*sept + ", "+sept);
        return tm.selectList(queryWrapper);
    }

    // 模糊搜索tag
    public List<Tag> searchTag(String tagNameOrCnName){
        //System.out.println("--tagNameOrCnName--" + tagNameOrCnName);

        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Tag> queryWrapper2 = new QueryWrapper<>();
        queryWrapper.like("name",tagNameOrCnName);
        queryWrapper2.like("cn_name",tagNameOrCnName);

        List<Tag> listA = tm.selectList(queryWrapper);
        List<Tag> listB = tm.selectList(queryWrapper2);
        //listA.addAll(listB);

        List<Tag>  bingList = new ArrayList<>(listA);
        bingList.removeAll(listB);
        bingList.addAll(listB);

        return bingList;
    }

    // 设置Tag Class内容
    public int setTagClazzFromId(int tagId,String clazz){
        UpdateWrapper<Tag> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",tagId).set("clazz", clazz);
        return tm.update(null,updateWrapper);
    }

    // 设置Tag CnName内容
    public int setTagCnNameFromId(int tagId,String cnName){
        UpdateWrapper<Tag> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",tagId).set("cn_name", cnName);
        return tm.update(null,updateWrapper);
    }

    // 设置Tag Message内容
    public int setTagMessageFromId(int tagId,String message){
        UpdateWrapper<Tag> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",tagId).set("message", message);
        return tm.update(null,updateWrapper);
    }

    // 添加tag
    public int addTag(Tag tag){
        return tm.insert(tag);
    }


    // 删除tag
    public int delTag(int id){
        return tm.deleteById(id);
    }

    // tag 自减

}
