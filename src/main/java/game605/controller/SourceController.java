package game605.controller;

import game605.Application;
import game605.bean.Tag;
import game605.common.web.ResponseResult;
import game605.service.impl.ImgInfoService;
import game605.service.impl.TagService;
import game605.common.util.ImgUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/source")
@CrossOrigin(origins = "*")
public class SourceController {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    ImgInfoService iis;



    /**
     * 返回图片原图二进制
     * @param imgId
     * @return
     */
    @GetMapping("/getBlobFromId")
    public ResponseResult getBlobFromImgId(@RequestParam int imgId) {
        String path = iis.getPathFromId(imgId);
        try {
            return ResponseResult.success(ImgUtil.getImgByte(path));
        } catch (Exception e) {
            log.error("id转byte[] 失败！ Exception：{}  ", e.toString());
            return ResponseResult.error(null, "图片获取失败！");
        }
    }


    /**
     * 返回缩略图二进制
     * @param imgId
     * @return
     */
    @GetMapping("/getSmallImg")
    public ResponseResult getSmallBlobFromImgId(@RequestParam int imgId) {
        return ResponseResult.success(iis.getSmallImgFromId(imgId));
    }




}
