package game605.controller;

import game605.bean.Imginfo;
import game605.bean.Tag;
import game605.bean.vo.ImgSearchVO;
import game605.bean.web.ResponseResult;
import game605.service.IImgTagService;
import game605.service.impl.ImgInfoService;
import game605.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    ImgInfoService iis;

    @Autowired
    TagService ts;

    @Autowired
    IImgTagService its;

    // 分页查询
    @GetMapping("/img")
    public ResponseResult searchImg(@RequestParam int page, @RequestParam int sept) {
        List<Integer> list = iis.searchImgId(page, sept);
        return ResponseResult.success(list);
    }

    // 分页查询 + 青少年模式
    @GetMapping("/img/teen")
    public ResponseResult getImgTeenMode(@RequestParam int page, @RequestParam int sept) {
        List<Integer> list = iis.getTeenImg(page, sept);
        return ResponseResult.success(list);
    }

    // 根据 单tag 分页 查询 imginfo
    @GetMapping("/imgFromTag")
    public ResponseResult searchImgFromTag(@RequestParam String tagName, @RequestParam int page, @RequestParam int sept) {
        List<Imginfo> list = iis.searchFromTag(tagName, page, sept);
        return ResponseResult.success(list);
    }

    // 根据 单tag 分页 查询 imginfo 青少年模式
    @GetMapping("/imgFromTagTeen")
    public ResponseResult searchImgFromTagTeen(@RequestParam String tagName, @RequestParam int page, @RequestParam int sept) {
        List<Imginfo> list = iis.searchFromTagTeen(tagName, page, sept);
        return ResponseResult.success(list);
    }

    /**
     *
     * <p>这个方法根据 多tag条件 分页 查询 imginfo<p/>
     *  @param params
     *  @return 符合要求的 imginfo list
     */
    @GetMapping("/imgFromTags")
    public ResponseResult searchImgFromTags(@RequestParam ImgSearchVO params) {
        List<Imginfo> list = iis.searchFromTags(params);
        return ResponseResult.success(list);
    }

    // 根据 多tag 分页 查询 imgId（缩略图除外）
    @GetMapping("/imgIdFromTags")
    public ResponseResult searchInfoFromTags(@RequestParam ImgSearchVO params) {
        List<Integer> list = iis.searchIdFromTags(params);
        return ResponseResult.success(list);
    }

    // search tag tag列表
    @GetMapping("/tag")
    public ResponseResult searchTag(@RequestParam String tagName) {
        List<Tag> list = ts.searchTag(tagName);
        return ResponseResult.success(list);
    }

    // 查找某个img的所有tag
    @GetMapping("/imgTags")
    public ResponseResult searchImgTags(@RequestParam int imgId) {
        List<Tag> list = its.getImgTagsFromId(imgId);
        return ResponseResult.success(list);
    }
}
