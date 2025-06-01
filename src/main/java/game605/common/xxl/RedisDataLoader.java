package game605.common.xxl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxl.job.core.handler.annotation.XxlJob;
import game605.bean.Tag;
import game605.mapper.ImgTagMapper;
import game605.mapper.TagMapper;
import game605.common.redis.RedisUtil;
import game605.service.impl.RedisImgTagService;
import game605.service.impl.TagService;
import game605.common.util.ByteUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Koyou
 * @version 1.0.0
 * @className RedisDataLoader
 * @description TODO
 * @since 2024/6/7 18:01
 */
@Component
@Slf4j
public class RedisDataLoader {

    @Autowired
    TagMapper tm;
    @Autowired
    ImgTagMapper itm;
    @Autowired
    TagService ts;
    @Autowired
    RedisImgTagService its;


    public void init() {
        log.info("-----------  redis 初始化数据  ------------");
        Jedis jedis = RedisUtil.getRedisConn();
        Pipeline pipeline = jedis.pipelined();
        StampedLock pipelineLock = new StampedLock();
        jedis.flushDB();
        // 初始化 redis 数据
        List<Tag> allTags = tm.selectList(new QueryWrapper<Tag>().select("id"));
        // 逐个tag进行更新 + 并行流加速
        allTags.parallelStream().forEach(t->{
            int tagId = t.getId();
            byte[] byte_tagId = ByteUtil.intToBytes(tagId);
            List<Integer> imgIds = its.getImgsIdFromTag(tagId);
            if(imgIds.size() > 0){
                // 加锁解决并发问题
                long stamp = pipelineLock.writeLock();
                try {
                    pipeline.sadd(byte_tagId,ByteUtil.intListToByteArrArr(imgIds));   // 插入新的条目
                }finally {
                    pipelineLock.unlockWrite(stamp);
                }
            }
        });
        pipeline.sync();  //提交
        try {
            pipeline.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        jedis.close();
        log.info("tag数据初始化完成！");
    }


    public void refresh(){
        Jedis jedis = RedisUtil.getRedisConn();
        // 获取所有Tag信息
        List<Tag> allTags = tm.selectList(new QueryWrapper<Tag>().select("id"));
        // 逐个tag进行更新
        for (Tag t: allTags) {
            int tagId = t.getId();
            byte[] byte_tagId = ByteUtil.intToBytes(tagId);
            List<Integer> imgIds = its.getImgsIdFromTag(tagId);
            if(imgIds.size() == 0){
                continue;
            }
            byte[][] toRedis = ByteUtil.intListToByteArrArr(imgIds);
            jedis.del(byte_tagId);          // 删除旧的条目
            jedis.sadd(byte_tagId,toRedis); // 插入新的条目
        }
        jedis.close();
    }


    @XxlJob("redisDataLoader")
    public void redisDataLoader() {
        log.info("redisDataLoader");
        init();
        log.info("redisDataLoader success");
    }

}
