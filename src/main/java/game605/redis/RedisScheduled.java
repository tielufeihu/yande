package game605.redis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import game605.Application;
import game605.bean.Tag;
import game605.mapper.ImgTagMapper;
import game605.mapper.TagMapper;
import game605.service.impl.RedisImgTagService;
import game605.service.impl.TagService;
import game605.util.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

@Component
public class RedisScheduled {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    TagMapper tm;
    @Autowired
    ImgTagMapper itm;
    @Autowired
    TagService ts;
    @Autowired
    RedisImgTagService its;

    public int i;

/*
    @PostConstruct // 构造函数之后执行
    public void init() {
        log.info("-----------  redis 初始化数据  ------------");
        // 开新线程，不影响系统启动
        new Thread(() -> {
            i = 1;
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
        }, "MyThread").start();
    }

*/
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


    /**
     * 通过定时任务 同步mysql 和 redis 的数据
     *
     */
    //@Scheduled(fixedRate = 12000000)      // 3小时刷新一次 7200000  4 9600000
    public void SynchronizationData() {
        //System.out.println("------------- 定时任务 ------------");
        if (i++ != 1) {
            log.info("开始定时任务：刷新redis数据");
            refresh();
            log.info("完成定时任务：刷新redis数据");
        }
    }

}
