package game605.controller;

import game605.bean.Imginfo;
import game605.bean.Tag;
import game605.bean.web.ResponseResult;
import game605.service.IImgTagService;
import game605.service.impl.ImgInfoService;
import game605.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*")
public class ControllerSearch {

    @Autowired
    ImgInfoService iis;

    @Autowired
    TagService ts;

    @Autowired
    IImgTagService its;

    // 分页查询
    @RequestMapping("/img")
    public ResponseResult searchImg(@RequestParam int page, @RequestParam int sept) {
        List<Integer> list = iis.searchImgId(page, sept);
        return ResponseResult.success(list);
    }

    // 分页查询 + 青少年模式
    @RequestMapping("/img/teen")
    public ResponseResult getImgTeenMode(@RequestParam int page, @RequestParam int sept) {
        List<Integer> list = iis.getTeenImg(page, sept);
        return ResponseResult.success(list);
    }

    // 根据 单tag 分页 查询 imginfo
    @RequestMapping("/imgFromTag")
    public ResponseResult searchImgFromTag(@RequestParam String tagName, @RequestParam int page, @RequestParam int sept) {
        List<Imginfo> list = iis.searchFromTag(tagName, page, sept);
        return ResponseResult.success(list);
    }

    // 根据 单tag 分页 查询 imginfo 青少年模式
    @RequestMapping("/imgFromTagTeen")
    public ResponseResult searchImgFromTagTeen(@RequestParam String tagName, @RequestParam int page, @RequestParam int sept) {
        List<Imginfo> list = iis.searchFromTagTeen(tagName, page, sept);
        return ResponseResult.success(list);
    }

    /**
     *
     * <p>这个方法根据 多tag条件 分页 查询 imginfo<p/>
     *  @param params json格式，示例：
     *  {
     *     "tags": ["tag1","tag2","tag3","tag4","tag5","tag6","tag7","tags8"],
     *     "page":[1,100],
     *     "teen": [true or false]
     *  }
     *  @return 符合要求的 imginfo list
     */
    @RequestMapping("/imgFromTags")
    public ResponseResult searchImgFromTags(@RequestBody Map<String, String[]> params) {
        List<Imginfo> list = iis.searchFromTags(params);
        return ResponseResult.success(list);
    }

    // 根据 多tag 分页 查询 imgId（缩略图除外）
    @RequestMapping("/imgIdFromTags")
    public ResponseResult searchInfoFromTags(@RequestBody Map<String, String[]> params) {
        List<Integer> list = iis.searchIdFromTags(params);
        return ResponseResult.success(list);
    }

    // search tag tag列表
    @RequestMapping("/tag")
    public ResponseResult searchTag(@RequestParam String tagName) {
        List<Tag> list = ts.searchTag(tagName);
        return ResponseResult.success(list);
    }

    // 查找某个img的所有tag
    @RequestMapping("/imgTags")
    public ResponseResult searchImgTags(@RequestParam int imgId) {
        List<Tag> list = its.getImgTagsFromId(imgId);
        return ResponseResult.success(list);
    }
}
