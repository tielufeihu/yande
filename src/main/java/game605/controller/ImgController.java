package game605.controller;

import game605.Application;
import game605.bean.Imginfo;
import game605.service.impl.ImgInfoService;
import game605.service.impl.RedisImgTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import game605.bean.web.ResponseResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/img")
@CrossOrigin(origins = "*")   // 解决跨越
public class ImgController {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    ImgInfoService iis;

    @Autowired
    RedisImgTagService its;


    /**
     * 根据id删除一个img
     */
    @PostMapping("/delete")
    public ResponseResult deleteImgInfo(@RequestBody Imginfo imginfo) {
        return ResponseResult.success(iis.delImgInfo(imginfo.getId()));
    }


    /**
     * 添加一个img
     * @param img
     * @return
     * @throws Exception
     */
    @PostMapping("/add")
    public ResponseResult uploadImg(@RequestBody MultipartFile img) throws Exception {
        return ResponseResult.success(iis.addImgInfo(img));
    }


    /**
     * 更新图片信息
     * @param img
     * @return
     */
    @PostMapping("/update")
    public ResponseResult updateImg(@RequestBody Imginfo img){
        return ResponseResult.success(its.addTagToImg(img));
    }


}
