package game605.es;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import game605.bean.ImgTag;
import game605.bean.Imginfo;
import game605.bean.Tag;
import game605.bean.vo.ImgTagVO;
import game605.mapper.ImgTagMapper;
import game605.mapper.ImginfoMapper;
import game605.mapper.TagMapper;
import game605.service.impl.DBImgTagService;
import game605.service.impl.RedisImgTagService;
import game605.util.ByteUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class EsScheduled {

    @Autowired
    TagMapper tagMapper;
    @Autowired
    ImginfoMapper imgMapper;
    @Autowired
    ImgTagMapper imgTagMapper;
    @Autowired
    ESImgRepository esImgRepository;

    @Autowired
    DBImgTagService dts;

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
        List<ESImg> saveES = new ArrayList<>();
        // 初始化 mysql 数据
        log.info("获取mysql数据");
        Long startTime = System.currentTimeMillis();

        // 获取img数据并计算时间
        log.info("开始 获取 img 数据");
        Long beginTime = System.currentTimeMillis();
        List<Imginfo> allImg = imgMapper.selectList(new QueryWrapper<Imginfo>().select("id", "path"));
        Long endTime = System.currentTimeMillis();
        log.info("获取 img 数据完成, 耗时: {}ms", endTime - beginTime);

        // 逐个img进行更新 + 并行流加速
        log.info("开始 根据img获取tag 数据");
        beginTime = System.currentTimeMillis();
        allImg.parallelStream().forEach(t->{
            ESImg esImg = new ESImg();
            esImg.setId(t.getId());
            esImg.setPath(t.getPath());
            // 获取该Img的tag
            List<ImgTagVO> imgTags = imgTagMapper.getImgTagVOByImgId(t.getId());
            List<Integer> tagIds = imgTags.stream().map(ImgTagVO::getTagId).toList();
            List<String> tags = imgTags.stream().map(ImgTagVO::getTagName).toList();
            // 赋值
            esImg.setTagIds(tagIds);
            esImg.setTags(tags);
            saveES.add(esImg);
        });
        endTime = System.currentTimeMillis();
        log.info("根据img获取tag 数据完成, 耗时: {}ms", endTime - beginTime);

        // 保存到es
        beginTime = System.currentTimeMillis();
        esImgRepository.saveAll(saveES);
        endTime = System.currentTimeMillis();
        log.info("保存到es完成, 耗时: {}ms", endTime - beginTime);

        log.info("总耗时: {}ms", startTime - beginTime);
    }


}
