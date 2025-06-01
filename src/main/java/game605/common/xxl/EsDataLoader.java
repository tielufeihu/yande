//package game605.xxl;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.google.common.collect.Lists;
//import com.xxl.job.core.handler.annotation.XxlJob;
//import game605.bean.Imginfo;
//import game605.bean.vo.ImgTagVO;
//import game605.es.ESImg;
//import game605.es.ESImgRepository;
//import game605.mapper.ImgTagMapper;
//import game605.mapper.ImginfoMapper;
//import game605.mapper.TagMapper;
//import game605.service.impl.DBImgTagService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Koyou
// * @version 1.0.0
// * @className EsDataLoader
// * @description TODO
// * @since 2024/6/7 18:01
// */
//@Component
//@Slf4j
//public class EsDataLoader {
//
//    @Autowired
//    TagMapper tagMapper;
//    @Autowired
//    ImginfoMapper imgMapper;
//    @Autowired
//    ImgTagMapper imgTagMapper;
//    @Autowired
//    ESImgRepository esImgRepository;
//    @Autowired
//    ElasticsearchClient elasticsearchClient;
//    @Autowired
//    ElasticsearchOperations elasticsearchOperations;
//
//    @Autowired
//    DBImgTagService dts;
//
//    /**
//     * 初步测试通过
//     * @since 2024/6/6 14:03
//     * @author Koyou
//     */
//    private void refresh(){
//        // 删除原索引
//        elasticsearchOperations.indexOps(IndexCoordinates.of("es_img")).delete();
//
//        List<ESImg> saveES = new ArrayList<>();
//        // 初始化 mysql 数据
//        log.info("获取mysql数据");
//        long startTime = System.currentTimeMillis();
//
//        // 获取img数据并计算时间
//        log.info("开始 获取 img 数据");
//        long beginTime = System.currentTimeMillis();
//        List<Imginfo> allImg = imgMapper.selectList(new QueryWrapper<Imginfo>().select("id", "path"));
//        long endTime = System.currentTimeMillis();
//        log.info("获取 img 数据完成, 耗时: {}ms", endTime - beginTime);
//
//        // 逐个img进行更新 + 并行流加速
//        log.info("开始 根据img获取tag 数据");
//        beginTime = System.currentTimeMillis();
//        allImg.parallelStream().forEach(t->{
//            ESImg esImg = new ESImg();
//            esImg.setId(t.getId());
//            esImg.setPath(t.getPath());
//            // 获取该Img的tag
//            List<ImgTagVO> imgTags = imgTagMapper.getImgTagVOByImgId(t.getId());
//            List<Integer> tagIds = imgTags.stream().map(ImgTagVO::getTagId).toList();
//            List<String> tags = imgTags.stream().map(ImgTagVO::getTagName).toList();
//            // 赋值
//            esImg.setTagIds(tagIds);
//            esImg.setTags(tags);
//            saveES.add(esImg);
//        });
//        endTime = System.currentTimeMillis();
//        log.info("根据img获取tag 数据完成, 耗时: {}ms", endTime - beginTime);
//
//
//        // 保存到es
//        log.info("开始 保存到es");
//        beginTime = System.currentTimeMillis();
//        // 单次保存会出现 Request Entity Too Large 的异常，因此需要切片分batch
//        List<List<ESImg>> batches = Lists.partition(saveES, 500);
//        for (List<ESImg> batch : batches) {
//            esImgRepository.saveAll(batch);
//        }
//        endTime = System.currentTimeMillis();
//        log.info("保存到es完成, 耗时: {}ms", endTime - beginTime);
//        log.info("总耗时: {}ms", endTime - startTime);
//    }
//
//
//    @XxlJob("esDataLoader")
//    public void esDataLoader() {
//        log.info("esDataLoader");
//        refresh();
//        log.info("esDataLoader success");
//    }
//
//}
