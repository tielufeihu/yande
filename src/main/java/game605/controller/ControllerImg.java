package game605.controller;

import game605.Application;
import game605.servicelmpl.ImgInfoService;
import game605.servicelmpl.ImgTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/img")
@ResponseBody
@CrossOrigin(origins = "*")   // 解决跨越
public class ControllerImg {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    ImgInfoService iis;

    @Autowired
    ImgTagService its;


    //根据id删除一个img
    @RequestMapping("/delImgInfo")
    public int delImgInfo(@RequestParam int id){
        return iis.delImgInfo(id);
    }


    //添加一个img
    @Transactional
    @RequestMapping("/addImg")
    public int addImg(@RequestParam MultipartFile img, @RequestParam String tagIds) throws Exception {
        if(Objects.equals(tagIds, "")){
            return -1;
        }
        List<Integer> tagIdList = new ArrayList<>();
        String[] tagIds_str = tagIds.split(",");
        for (String tagId: tagIds_str) {
            tagIdList.add(Integer.valueOf(tagId));
        }
        return iis.addImgInfo(img,tagIdList);
    }



    //为一个图片添加一个tag
    @RequestMapping("/addTag")
    public int addTag(@RequestParam int imgId, @RequestParam int tagId){
        System.out.println("进入/addTag addTag 函数入口");
        return its.addTagToImg(imgId,tagId);
    }


}
