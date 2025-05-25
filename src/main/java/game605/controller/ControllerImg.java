package game605.controller;

import game605.Application;
import game605.service.impl.ImgInfoService;
import game605.service.impl.RedisImgTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import game605.bean.web.ResponseResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/img")
@CrossOrigin(origins = "*")   // 解决跨越
public class ControllerImg {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    ImgInfoService iis;

    @Autowired
    RedisImgTagService its;


    //根据id删除一个img
    @RequestMapping("/delImgInfo")
    public ResponseResult delImgInfo(@RequestParam int id){
        return ResponseResult.success(iis.delImgInfo(id));
    }


    //添加一个img
    @Transactional
    @RequestMapping("/addImg")
    public ResponseResult addImg(@RequestParam MultipartFile img, @RequestParam String tagIds) throws Exception {
        if(Objects.equals(tagIds, "")){
            return ResponseResult.error(-1);
        }
        List<Integer> tagIdList = new ArrayList<>();
        String[] tagIds_str = tagIds.split(",");
        for (String tagId: tagIds_str) {
            tagIdList.add(Integer.valueOf(tagId));
        }
        return ResponseResult.success(iis.addImgInfo(img,tagIdList));
    }



    //为一个图片添加一个tag
    @RequestMapping("/addTag")
    public ResponseResult addTag(@RequestParam int imgId, @RequestParam int tagId){
        return ResponseResult.success(its.addTagToImg(imgId,tagId));
    }


}
