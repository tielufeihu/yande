package game605.controller;

import game605.bean.TagClazz;
import game605.bean.web.ResponseResult;
import game605.service.TagClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ControllerTagClazz
 * @description 标签类型控制器
 * @since 2024/7/10 16:08
 */
@Controller
@RequestMapping("/tagClazz")

public class ControllerTagClazz {

    @Autowired
    private TagClazzService tagClazzService;

    // 新增
    @PostMapping("/add")
    public ResponseResult add(@RequestBody TagClazz tagClazz)  {
        return ResponseResult.success(tagClazzService.save(tagClazz));
    }

    // 删除
    @DeleteMapping("/delete")
    public ResponseResult delete(@RequestParam Long clazzId)  {
        return ResponseResult.success(tagClazzService.removeById(clazzId));
    }

    // 修改
    @PostMapping("/update")
    public ResponseResult update(@RequestBody TagClazz tagClazz)  {
        return ResponseResult.success(tagClazzService.updateById(tagClazz));
    }

    // 查询单个
    @GetMapping("/query")
    public ResponseResult query(@RequestParam Long clazzId) {
       return ResponseResult.success(tagClazzService.getById(clazzId));
    }

    // 查询列表
    @GetMapping("/queryList")
    public ResponseResult queryList() {
        return ResponseResult.success(tagClazzService.list());
    }

}
