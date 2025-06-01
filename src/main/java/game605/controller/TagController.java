package game605.controller;

import game605.bean.Tag;
import game605.common.web.ResponseResult;
import game605.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = "*")
public class TagController {

    @Autowired
    TagService ts;

    // search tag tag列表
    @GetMapping("/list")
    public ResponseResult list(@RequestParam Tag tag) {
        List<Tag> list = ts.searchTag(tag.getTagName());
        return ResponseResult.success(list);
    }

    // 设置 tag 属性信息（封装）
    @Transactional
    @PostMapping("/update")
    public ResponseResult updateTag(@RequestBody Tag tag) {
        return ResponseResult.success(ts.updateTag(tag));
    }


    // 新增 Tag（封装）
    @Transactional
    @PostMapping("/add")
    public ResponseResult addTag(@RequestBody Tag tag) {
        int result = ts.addTag(tag);
        return ResponseResult.success(result);
    }



}
