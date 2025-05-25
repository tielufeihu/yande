package game605.controller;

import game605.bean.Tag;
import game605.bean.web.ResponseResult;
import game605.service.IImgTagService;
import game605.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = "*")
public class ControllerTag {

    @Autowired
    TagService ts;

    @Autowired
    IImgTagService its;

    // 设置 tag 属性信息（封装）
    @Transactional
    @RequestMapping("/set")
    public ResponseResult setClass(
            @RequestParam String tagName,
            @RequestParam String cnName,
            @RequestParam String clazz,
            @RequestParam String message
    ) {
        int re = 1;
        int tag_id = ts.getIdFromName(tagName);
        re *= ts.setTagCnNameFromId(tag_id, cnName);
        re *= ts.setTagClazzFromId(tag_id, clazz);
        re *= ts.setTagMessageFromId(tag_id, message);
        return ResponseResult.success(re);
    }

    // 新增 Tag（封装）
    @Transactional
    @RequestMapping("/add")
    public ResponseResult addTag(
            @RequestParam String name,
            @RequestParam String cnName,
            @RequestParam String clazz,
            @RequestParam String message
    ) {
        Tag tag = new Tag();
        tag.setImgCount(0);
        tag.setCnName(cnName);
        tag.setClazz(clazz);
        tag.setMessage(message);
        tag.setName(name);
        int result = ts.addTag(tag);
        return ResponseResult.success(result);
    }
}
