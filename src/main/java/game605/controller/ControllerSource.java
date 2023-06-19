package game605.controller;

import game605.Application;
import game605.bean.Tag;
import game605.servicelmpl.ImgInfoService;
import game605.servicelmpl.ImgTagService;
import game605.servicelmpl.TagService;
import game605.utilx.ImgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
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
    public byte[] getBlobFromImgId(@RequestParam int id){
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
