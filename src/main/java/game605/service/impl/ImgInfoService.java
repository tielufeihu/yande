package game605.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import game605.Application;
import game605.bean.ImgTag;
import game605.bean.Imginfo;
import game605.bean.Tag;
import game605.bean.vo.ImgSearchVO;
import game605.mapper.ImgTagMapper;
import game605.mapper.ImginfoMapper;
import game605.mapper.TagMapper;
import game605.common.redis.RedisService;
import game605.service.IImgTagService;
import game605.common.util.ImgUtil;
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

    private static final String getFullImgUrl = "/source/getBlobFromId";


    @Autowired
    ImginfoMapper iim;

    @Autowired
    TagMapper tm;

    @Autowired
    ImgTagMapper itm;

    @Autowired
    IImgTagService its;

    @Autowired
    TagService ts;

    @Autowired
    RedisService rs;


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


    // 查询图片 for 多个tag  ok
    public List<Imginfo> searchFromTags(ImgSearchVO params){
        // 方法1 先读取单tag 然后再查询
        List<Integer> tagIds = params.getTagIds();
        int pageNum = params.getPageNum();
        int pageSize = params.getPageSize();
        boolean teenMode = params.isTeenMode();
        List<Integer> imgIdList = its.getImgsIdFromTags(tagIds,pageNum,pageSize);
        if(teenMode){
            imgIdList = teenFilter(imgIdList);
        }
        List<Imginfo> res = iim.selectBatchIds(imgIdList);
        return res;
    }

    public List<Integer> searchIdFromTags(ImgSearchVO params){
        // 方法1 先读取单tag 然后再查询
        List<Integer> tagIds = params.getTagIds();
        int pageNum = params.getPageNum();
        int pageSize = params.getPageSize();
        boolean teenMode = params.isTeenMode();
        List<Integer> imgIdList = null;
        if(tagIds.isEmpty())
        {
            imgIdList = searchImgId(pageNum,pageSize);
        }else {
            imgIdList = its.getImgsIdFromTags(tagIds,pageNum,pageSize);
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
    public int addImgInfo(MultipartFile imgFile) throws Exception {
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
        iim.insertImginfoSeq();
        int new30id = iim.getNextImginfoId();
        imginfo.setId(new30id);
        // 2.设置 路径
        imginfo.setPath(fileNameAdd);
        // 3.设置缩略图
        imginfo.setImg(small_img);
        // 4.存数据库
        iim.insert(imginfo);
        // 插入 ImgTag
        // for (int tagId: tagIds) {
        //     re*=itm.insert(new ImgTag(new30id,tagId));
        //     rs.addImgTag(new30id,tagId);
        //     // tag_count ++
        //     UpdateWrapper<Tag> wrapper = new UpdateWrapper<Tag>();
        //     wrapper.eq("id",tagId);
        //     wrapper.setSql("`img_count` = `img_count` + 1");  // mybatis-plus 实现字段的自增
        //     re*=tm.update(null,wrapper);
        // }
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


    /**
     * 查询图片详情
     * @param imgId
     * @return
     */
    public Imginfo getImgInfo(int imgId) {
        Imginfo img = iim.getImginfoById(imgId);
        List<ImgTag> imgTags = itm.selectList(new QueryWrapper<ImgTag>().eq("img_id",imgId));
        img.setImgTags(imgTags);
        img.setFullImgPath(getFullImgUrl + "?imgId=" + imgId);
        return iim.selectById(imgId);
    }


}
