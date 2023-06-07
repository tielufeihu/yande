package game605.controller;

import game605.bean.ImgTag;
import game605.bean.Tag;
import game605.servicelmpl.ImgTagService;
import game605.servicelmpl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/tag")
@CrossOrigin(origins = "*")
public class ControllerTag {

    @Autowired
    TagService ts;

    @Autowired
    ImgTagService its;


    //设置 tag
    @Transactional   // 事务
    @RequestMapping("/set")
    public int setClass(@RequestParam String tagName, @RequestParam String cnName, @RequestParam String clazz, @RequestParam String message){
        int re = 1;
        int tag_id = ts.getIdFromName(tagName);
        re *= ts.setTagCnNameFromId(tag_id, cnName);
        re *= ts.setTagClazzFromId(tag_id, clazz);
        re *= ts.setTagMessageFromId(tag_id, message);
        return re;
    }

    //新增一个Tag
    @Transactional   // 事务
    @RequestMapping("/add")
    public int addTag(@RequestParam String name, @RequestParam String cnName, @RequestParam String clazz, @RequestParam String message){
        Tag tag = new Tag();
        tag.setImgCount(0);
        tag.setCnName(cnName);
        tag.setClazz(clazz);
        tag.setMessage(message);
        tag.setName(name);
        return ts.addTag(tag);
    }


}
