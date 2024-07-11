package game605.controller;

import game605.bean.dto.ImgCollectionDTO;
import game605.bean.web.ResponseResult;
import game605.service.ImgCollectionDetailService;
import game605.service.ImgCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ImgCollectionController
 * @description 画集控制器
 * @since 2024/7/10 17:51
 */
@Controller
@RequestMapping("/imgCollection")
public class ImgCollectionController {

    @Autowired
    private ImgCollectionService imgCollectionService;

    @Autowired
    private ImgCollectionDetailService imgCollectionDetailService;

    /**
     * 添加图集
     * @param imgCollectionDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseResult addImgCollection(@RequestBody ImgCollectionDTO imgCollectionDTO) {
        return ResponseResult.success(imgCollectionService.addImgCollection(imgCollectionDTO));
    }

    /**
     * 删除图集
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseResult deleteImgCollection(@RequestParam long id) {
        return ResponseResult.success(imgCollectionService.deleteImgCollection(id));
    }

    /**
     * 获取单个图集详细信息
     * @param id
     * @return
     */
    @GetMapping("/getInfo")
    public ResponseResult getImgCollectionInfo(@RequestParam long id) {
        return ResponseResult.success(imgCollectionService.getImgCollectionInfo(id));
    }

}
