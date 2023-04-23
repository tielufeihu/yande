package game605.controller;

import game605.bean.Imginfo;
import game605.bean.Tag;
import game605.servicelmpl.ImgInfoService;
import game605.servicelmpl.ImgTagService;
import game605.servicelmpl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@ResponseBody
@RequestMapping("/search")
@CrossOrigin(origins = "*")
public class ControllerSearch {

    @Autowired
    ImgInfoService iis;

    @Autowired
    TagService ts;

    @Autowired
    ImgTagService its;

    // 分页查询
    @RequestMapping("/img")
    public List<Imginfo> searchImg(@RequestParam int page, @RequestParam int sept){
        return iis.searchImg(page,sept);
    }

    // 根据 单tag 分页 查询 imginfo
    @RequestMapping("/imgFromTag")
    public List<Imginfo> searchImgFromTag(@RequestParam String tagName, @RequestParam int page, @RequestParam int sept){
        return iis.searchFromTag(tagName,page,sept);
    }


    /**
     *
     * <p>这个方法根据 多tag条件 分页 查询 imginfo<p/>
     *  @param params json格式，示例：
     *  {
     *     "tags": ["tag1","tag2","tag3","tag4","tag5","tag6","tag7","tags8"],
     *     "page":[1,100]
     *  }
     *  @return 符合要求的 imginfo list
     */
    @RequestMapping("/imgFromTags")
    public List<Imginfo> searchImgFromTags(@RequestBody Map<String,String[]> params){
        return iis.searchFromTags(params);
    }

    // 根据 多tag 分页 查询 imgId（缩略图除外）
    @RequestMapping("/imgIdFromTags")
    public List<Integer> searchInfoFromTags(@RequestBody Map<String,String[]> params){
        return iis.searchIdFromTags(params);
    }

    // search tag  tag列表
    @RequestMapping("/tag")
    public List<Tag> searchTag(@RequestParam String tagName){
        return ts.searchTag(tagName);
    }

    //查找某个img的所有tag
    @RequestMapping("/imgTags")
    public List<Tag> searchImgTags(@RequestParam int imgId){
        return its.getImgTagsFromId(imgId);
    }


}
