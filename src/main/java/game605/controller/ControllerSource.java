package game605.controller;

import game605.Application;
import game605.bean.Tag;
import game605.service.impl.ImgInfoService;
import game605.service.impl.TagService;
import game605.util.ImgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/source")
@CrossOrigin(origins = "*")   // 解决跨越
public class ControllerSource {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    ImgInfoService iis;

    @Autowired
    TagService ts;


    //根据id 返回该图片的blob数据图片 （大图）
    @RequestMapping("/getBlobFromId")
    public byte[] getBlobFromImgId(@RequestParam int id, HttpServletResponse response){
        String path = iis.getPathFromId(id);
        try {
            return ImgUtil.getImgByte(path);
        }catch (Exception e){
            log.error("id转byte[] 失败！ Exception：{}  ",e.toString());
            return null;
        }
    }

    // 获取文件 url
    @RequestMapping("/getFilePath")
    public String getImgFileUrl(@RequestParam int id){
        return iis.getPathFromId(id);
    }

    // 根据id 获取缩略图
    @RequestMapping("/getSmallImg")
    public byte[] getSmallBlobFromImgId(@RequestParam int id){
        return iis.getSmallImgFromId(id);
    }

    //获取tag列表 根据tagcount大小降序排列
    @RequestMapping("/getTagList")
    public List<Tag> getTagList(@RequestParam int page, @RequestParam int step){
        return ts.getTagsPageOrderCount(page,step);
    }

}
