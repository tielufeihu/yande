package game605.es;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import game605.bean.ImgTag;
import game605.bean.Tag;
import game605.mapper.ImgTagMapper;
import game605.mapper.TagMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Koyou
 * @version 1.0.0
 * @className EsScheduled
 * @description Es启动任务
 * @since 2024/6/6 14:03
 */
@Component
public class EsScheduled {

    @Autowired
    TagMapper tagMapper;
    @Autowired
    ImgTagMapper imgTagMapper;
    @Autowired
    ESImgRepository esImgRepository;

    @PostConstruct
    public void init() {
        refresh();
    }

    /**
     * 初步测试通过
     * @since 2024/6/6 14:03
     * @author Koyou
     */
    private void refresh(){
        System.out.println("执行刷新es");
        // 获取mysql数据
        List<Tag> tagList = tagMapper.selectList(null);
        System.out.println("获取tag数据");
        List<ImgTag> imgTagList = imgTagMapper.selectList(new QueryWrapper<ImgTag>().last("limit 10000"));
        System.out.println("获取img数据");
        // 同步到es
        Map<Integer, List<ImgTag>> imgData = imgTagList.stream()
                .collect(Collectors.groupingBy(ImgTag::getImgId));
        List<ESImg> save = new ArrayList<>();
        imgData.forEach((imgId, imgTags) -> {
            ESImg esImg = new ESImg();
            List<String> tags = new ArrayList<>();
            esImg.setId(imgId);
            for (ImgTag imgTag : imgTags){
                // 搜索tag对应的tagName加入
                tagList.stream()
                        .filter(tag -> tag.getId()==imgTag.getTagId()).findAny()
                        .ifPresent(tag -> tags.add(tag.getName()));
            }
            esImg.setTags(tags);
            save.add(esImg);
        });
        System.out.println("执行插入es");
        esImgRepository.saveAll(save);
        System.out.println("完成");
    }


}
