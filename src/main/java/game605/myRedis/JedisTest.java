package game605.myRedis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import game605.bean.Tag;
import game605.mapper.ImgTagMapper;
import game605.mapper.TagMapper;
import game605.servicelmpl.ImgTagService;
import game605.servicelmpl.TagService;
import game605.utilx.ByteUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JedisTest {


    @Autowired
    TagMapper tm;
    @Autowired
    ImgTagMapper itm;
    @Autowired
    TagService ts;
    @Autowired
    ImgTagService its;


    @Test
    public void test01(){
        //连接本地的 Redis 服务
        Jedis jedis = getRedisConn();
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

    }

    @Test
    public void test02(){
        Jedis jedis = getRedisConn();

        jedis.set("灵异事件", "?????");
        long token_l = 832209812077936640L;
        byte[] token = ByteUtil.longToBytes(token_l);
        if(jedis.exists(token)){
            System.out.println("存在");
            byte[] userId = jedis.get(token);
            System.out.println(ByteUtil.bytesToInt(userId));
        }
        System.out.println(jedis.get("灵异事件"));
        jedis.close();
    }

    public Jedis getRedisConn(){
        return new Jedis("localhost",6379);
    }



}
