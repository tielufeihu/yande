package game605.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import game605.Application;
import game605.bean.ImgTag;
import game605.bean.Imginfo;
import game605.bean.Tag;
import game605.mapper.ImgTagMapper;
import game605.mapper.ImginfoMapper;
import game605.mapper.TagMapper;
import game605.redis.RedisService;
import game605.util.ImgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@PropertySource("classpath:application.yml")
public class ImgInfoService {

    private static Logger log = LoggerFactory.getLogger(Application.class);
    @Value("${file-path.upload-save-path}")
    public String savePath;

    @Autowired
    ImginfoMapper iim;

    @Autowired
    TagMapper tm;

    @Autowired
    ImgTagMapper itm;

    @Autowired
    RedisImgTagService its;

    @Autowired
    TagService ts;

    @Autowired
    RedisService rs;

    // 查询图片 from id
    public Imginfo searchFromId(String id){
        return iim.selectById(id);
    }


    // 分页查询图片
    public List<Imginfo> searchImg(int page,int step){
        QueryWrapper<Imginfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id").orderByDesc("id").last("Limit " + (page-1)*step + ", " + step);
        List<Imginfo> list = iim.selectList(queryWrapper);
        return list;
    }

    //分页降序查询图片id
    public List<Integer> searchImgId(int page,int step){
        List<Integer> idlist = new ArrayList<>();
        List<Imginfo> list = searchImg(page,step);
        for (Imginfo t:list) {
            idlist.add(t.getId());
        }
        return idlist;
    }

    // 查询图片 from 单个tag   success
    public List<Imginfo> searchFromTag(String tagName,int page,int step){
        int tagId = ts.getIdFromName(tagName);
        List<Integer> imgIdList = its.getImgsIdFromTag(tagId, page, step);
        List<Imginfo> res = iim.selectBatchIds(imgIdList);
        return res;
    }

    // 查询图片 from 单个tag  teen   success
    public List<Imginfo> searchFromTagTeen(String tagName,int page,int step){
        int tagId = ts.getIdFromName(tagName);
        List<Integer> imgIdList = its.getImgsIdFromTag(tagId, page, step);
        List<Imginfo> res = iim.selectBatchIds(teenFilter(imgIdList));
        return res;
    }

    // 查询图片 for 多个tag  ok
    public List<Imginfo> searchFromTags(Map<String,String[]> params){
        // 方法1 先读取单tag 然后再查询
        String[] tagNames = params.get("tags");
        String[] ps = params.get("page");
        String[] teen = params.get("teen");

        // 安全性验证
        if(tagNames.length < 1 && ps.length != 2)
        {
            log.warn("多tag查询时 参数不合法！ 参数：{}",params);
            return null;
        }
        int page = Integer.parseInt(ps[0]);
        int sept = Integer.parseInt(ps[1]);
        boolean teenMode = false;
        if(teen.length != 0){
            if(teen[0].equals("true"))
            {
                teenMode = true;
            }
        }
        List<Integer> imgIdList = its.getImgsIdFromTags(tagNames,page,sept);
        List<Imginfo> res = iim.selectBatchIds(imgIdList);
        return res;
    }

    public List<Integer> searchIdFromTags(Map<String,String[]> params){
        // 方法1 先读取单tag 然后再查询
        String[] tagNames = params.get("tags");
        String[] ps = params.get("page");
        String[] teen = params.get("teen");
        int page;
        int sept;
        boolean teenMode = false;

        try{
            page = Integer.parseInt(ps[0]);
            sept = Integer.parseInt(ps[1]);
            if(teen.length != 0){
                if(teen[0].equals("true"))
                {
                    teenMode = true;
                }
            }
        }catch (Exception e){
            log.error("页面数据不合法！ ps:{}", (Object) ps);
            return null;
        }
        List<Integer> imgIdList = null;
        if(tagNames.length == 0 && ps.length == 2)
        {
            imgIdList = searchImgId(page,sept);
        }else {
            imgIdList = its.getImgsIdFromTags(tagNames,page,sept);
        }
        if(teenMode){
            imgIdList = teenFilter(imgIdList);
        }
        return imgIdList;
    }

    //根据id 获取 图片的 path
    public String getPathFromId(int id){
        QueryWrapper<Imginfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("path").eq("id",id);  // 单列查询加快速度
        Imginfo i = iim.selectOne(queryWrapper);
        if (i!=null)
            return i.getPath();
        else
            return null;
    }

    //根据id 获取 图片的 缩略图
    public byte[] getSmallImgFromId(int id){
        return iim.selectById(id).getImg();
    }

    /***
     *  删除img
     * @param id id
     * @return  有异常返回0，无异常返回1
     */
    @Transactional
    public int delImgInfo(int id){
        QueryWrapper<Imginfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("path").eq("id",id);
        Imginfo t_imginfo = iim.selectOne(queryWrapper);
        String img_path = t_imginfo.getPath();
        int re = 1;
        // 1.相关tag-1
        List<Tag> tags = its.getImgTagsFromId(id);
        for (Tag tag:tags) {
            UpdateWrapper<Tag> wrapper = new UpdateWrapper<>();
            wrapper.eq("id",tag.getId());
            wrapper.setSql("`img_count` = `img_count` - 1");  // mybatis-plus 实现字段的自-
            re*=tm.update(tag,wrapper);
        }
        // 2.删除记录
        re *= iim.deleteById(id);
        // 3.删除本地路径的文件
        File file = new File(img_path);
        if (file.isFile() && file.exists()) {
            if(!file.delete()){
                re = 0;
            }
        }
        return re;
    }

    //添加img
    @Transactional
    public int addImgInfo(MultipartFile imgFile, List<Integer> tagIds) throws Exception {
        byte[] small_img = null;
        int re = 1;
        //获取文件名
        String fileName = imgFile.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if(!(suffixName.equals(".jpg") || suffixName.equals(".png"))){
            log.info("上传的文件格式不正确！");
            return -1;
        }
        //获取时间
        java.util.Date day = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
        String timeString = sdf.format(day);
        String fileNameAdd = savePath + timeString + fileName;
        // 保存文件
        imgFile.transferTo(new File(fileNameAdd));
        // 生成缩略图
        //byte[] bytes_img = imgFile.getBytes();
        byte[] bytes_img = ImgUtil.getImgByte(fileNameAdd);
        // 生成缩略图
        small_img = ImgUtil.resizeImg(bytes_img);
        Imginfo imginfo = new Imginfo();
        // 1.设置id（先获取 最新id的 ）
        Imginfo imginfo30 = iim.selectById(30);
        // 加上30的前缀
        int new30id = Integer.parseInt(imginfo30.getPath());
        new30id = Integer.parseInt("30" + String.valueOf(new30id));
        imginfo30.setNewIdAdd1();  //自增1
        re*=iim.updateById(imginfo30);  //保存数据库
        imginfo.setId(new30id);
        // 2.设置 路径
        imginfo.setPath(fileNameAdd);
        // 3.设置缩略图
        imginfo.setImg(small_img);
        // 4.存数据库
        iim.insert(imginfo);
        // 插入 ImgTag
        for (int tagId: tagIds) {
            re*=itm.insert(new ImgTag(new30id,tagId));
            rs.addImgTag(new30id,tagId);
            // tag_count ++
            UpdateWrapper<Tag> wrapper = new UpdateWrapper<Tag>();
            wrapper.eq("id",tagId);
            wrapper.setSql("`img_count` = `img_count` + 1");  // mybatis-plus 实现字段的自增
            re*=tm.update(null,wrapper);
        }
        // 插入上传者信息 imgTag
        re*=itm.insert(new ImgTag(new30id,133504));  //我的上传tag id
        rs.addImgTag(new30id,133504);
        // tag_count ++
        UpdateWrapper<Tag> wrapper = new UpdateWrapper<Tag>();
        wrapper.eq("id",133504);
        wrapper.setSql("`img_count` = `img_count` + 1");  // mybatis-plus 实现字段的自增
        re*=tm.update(null,wrapper);

        return re;
    }

    public List<Integer> getTeenImg(int page, int step) {
        QueryWrapper<Imginfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id").orderByDesc("id").last("Limit " + (page-1)*step + ", " + step);
        List<Imginfo> list = iim.selectList(queryWrapper);
        List<Integer> rel = new ArrayList<>();
        for (Imginfo i: list) {
            rel.add(i.getId());
        }
        return teenFilter(rel);
    }

    public List<Integer> teenFilter(List<Integer> idList){
        List<Integer> reList = new ArrayList<>();
        for (int imgId: idList) {
            // 获取该img的tag list
            List<Tag> tags = its.getImgTagsFromId(imgId);
            // 排除 class 为 h的图片
            boolean flag = false;
            for(Tag tag: tags){
                if(tag.getClazz().equals("h")){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                reList.add(imgId);
            }
        }
        return reList;
    }
}
