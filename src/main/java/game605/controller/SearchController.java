package game605.controller;

import game605.bean.Imginfo;
import game605.bean.Tag;
import game605.bean.vo.ImgSearchVO;
import game605.common.web.ResponseResult;
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


    /**
     *
     * <p>这个方法根据 多tag条件 分页 查询 imginfo<p/>
     *  @param params
     *  @return 符合要求的 imginfo list
     */
    @GetMapping("/imgList")
    public ResponseResult imgList(@RequestParam ImgSearchVO params) {
        List<Imginfo> list = iis.searchFromTags(params);
        return ResponseResult.success(list);
    }

    // 根据 多tag 分页 查询 imgId（缩略图除外）
    @GetMapping("/imgIdList")
    public ResponseResult imgIdList(@RequestParam ImgSearchVO params) {
        List<Integer> list = iis.searchIdFromTags(params);
        return ResponseResult.success(list);
    }



}
