package game605.controller;

import game605.Application;
import game605.bean.Tag;
import game605.bean.web.ResponseResult;
import game605.service.impl.ImgInfoService;
import game605.service.impl.TagService;
import game605.util.ImgUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/source")
@CrossOrigin(origins = "*")
public class ControllerSource {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    ImgInfoService iis;

    @Autowired
    TagService ts;

    // 返回图片原图二进制（不封装）
    @GetMapping("/getBlobFromId")
    public byte[] getBlobFromImgId(@RequestParam int id, HttpServletResponse response) {
        String path = iis.getPathFromId(id);
        try {
            return ImgUtil.getImgByte(path);
        } catch (Exception e) {
            log.error("id转byte[] 失败！ Exception：{}  ", e.toString());
            return null;
        }
    }

    // 获取图片文件路径（封装）
    @GetMapping("/getFilePath")
    public ResponseResult getImgFileUrl(@RequestParam int id) {
        String path = iis.getPathFromId(id);
        return ResponseResult.success(path);
    }

    // 返回缩略图二进制（不封装）
    @GetMapping("/getSmallImg")
    public byte[] getSmallBlobFromImgId(@RequestParam int id) {
        return iis.getSmallImgFromId(id);
    }

    // 获取热门 tag 列表（封装）
    @GetMapping("/getTagList")
    public ResponseResult getTagList(@RequestParam int page, @RequestParam int step) {
        List<Tag> tags = ts.getTagsPageOrderCount(page, step);
        return ResponseResult.success(tags);
    }
}
