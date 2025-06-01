package game605.common.redis;

import game605.Application;
import game605.common.util.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;

@Service
public class RedisService {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    private List<byte[]> searchImgsIdFromTags(List<Integer> tagIds){
        Jedis jedis = RedisUtil.getRedisConn();
        // 求交集 sinter key [key ...]
        byte[][] ttt = new byte[tagIds.size()][4];
        int i = 0;
        for (Integer tagId: tagIds) {
            ttt[i++] = ByteUtil.intToBytes(tagId);
        }
        Set<byte[]> setsinter = jedis.sinter(ttt);
        // 求交集并存储到指定集合
        // jedis.sinterstore("sinter", "fruit", "food");
        List<byte[]> re = new ArrayList<>(setsinter);   //这个是无序的
        jedis.close();
        return re;
    }

    private List<byte[]> searchListLimit(byte[] key, int page, int sept){
        Jedis jedis = RedisUtil.getRedisConn();
        List<byte[]> re = jedis.lrange(key, (long)(page - 1) * sept,(long)page * sept -1);
        Long remainTime = jedis.ttl(key);
        jedis.expire(key,remainTime.intValue()+600);  //重置生命时间
        jedis.close();
        return re;
    }

    public List<byte[]> searchImgsIdFromTag(byte[] byte_tagId){
        Jedis jedis = RedisUtil.getRedisConn();
        Set<byte[]> sre = jedis.smembers(byte_tagId);
        List<byte[]> re = new ArrayList<>(sre);   //这个是无序的
        jedis.close();
        return re;
    }

    public List<Integer> getImgsIdFromTag(int tagId, int page, int sept){
        Jedis jedis = RedisUtil.getRedisConn();
        // 查询是否存在该查询的结果缓存
        byte[] toRedisKey = (tagId+"c").getBytes();
        if(jedis.exists(toRedisKey)){
            // 如果存在 则 获取相应的数据并重置生命时间返回
            List<byte[]> re = searchListLimit(toRedisKey, page, sept);
            jedis.close();
            return ByteUtil.intByteListToList(re);
        }else {
            // 如果不存在 则 先查询出结果再返回
            List<byte[]> byteList = searchImgsIdFromTag(ByteUtil.intToBytes(tagId));  // 查出所有结果（无序）
            List<Integer> intList = ByteUtil.intByteListToList(byteList);  // 转intList
            intList.sort(Comparator.naturalOrder());  //排序
            byte[][] toRedis = ByteUtil.intListToByteArrArr(intList); // 准备插入redis的数据
            jedis.lpush(toRedisKey, toRedis);  // 插入 redis
            List<byte[]> re = searchListLimit(toRedisKey, page, sept);  // 查出我们要的结果（降序+分页）
            jedis.close();
            return ByteUtil.intByteListToList(re);
        }
    }

    public List<Integer> getImgsIdFromTags(List<Integer> tagIds, int page, int sept){
        Jedis jedis = RedisUtil.getRedisConn();
        tagIds.sort(Comparator.naturalOrder());  // 排序避免不同序造成的key不一致
        // 查询是否存在该查询的结果缓存
        byte[] toRedisKey;
        StringBuilder toRedisKeyStrTemp = new StringBuilder();
        for (Integer n:tagIds) {
            toRedisKeyStrTemp.append(n).append(",");
        }
        toRedisKey = toRedisKeyStrTemp.toString().getBytes();
        if(jedis.exists(toRedisKey)){
            // 如果存在 则 获取相应的数据并重置生命时间返回
            List<byte[]> re = searchListLimit(toRedisKey, page, sept);
            jedis.close();
            return ByteUtil.intByteListToList(re);
        }else {
            // 如果不存在 则 先查询出结果再返回
            List<byte[]> byteList = searchImgsIdFromTags(tagIds);  // 查出所有结果（无序）
            if(byteList.size() == 0){
                jedis.close();
                return null;
            }
            List<Integer> intList = ByteUtil.intByteListToList(byteList);  // 转intList
            intList.sort(Comparator.naturalOrder());  //排序
            byte[][] toRedis = ByteUtil.intListToByteArrArr(intList); // 准备插入redis的数据
            jedis.lpush(toRedisKey, toRedis);  // 插入 redis
            List<byte[]> re = searchListLimit(toRedisKey, page, sept);  // 查出我们要的结果（降序+分页）
            jedis.close();
            return ByteUtil.intByteListToList(re);
        }
    }

    // 成功return 字符串的 "OK"
    public String saveToken(int userId, Long token, int time){
        Jedis jedis = RedisUtil.getRedisConn();
        byte[] key = ByteUtil.longToBytes(token);
        byte[] value = ByteUtil.intToBytes(userId);
        String re = jedis.set(key,value);
        jedis.expire(key,time);  // 设置过期时间 (s)
        jedis.close();
        return re;
    }

    // 检测Token是否存在  如果存在返回用户id  不存在返回null
    public byte[] checkToken(byte[] token){
        try (Jedis jedis = RedisUtil.getRedisConn()) {
            if (jedis.exists(token)) {
                return jedis.get(token);
            }
        } catch (Exception e) {
            log.warn("checkToken error:{}", e.toString());
        }
        return null;
    }

    public int setTokenTime(Long token, int s){
        Jedis jedis = RedisUtil.getRedisConn();
        byte[] bytes_token = ByteUtil.longToBytes(token);
        if (!jedis.exists(bytes_token)){
            jedis.close();
            return 0;
        }
        jedis.expire(bytes_token, s);
        jedis.close();
        return 1;
    }

    public int addImgTag(int imgId, int tagId){
        Jedis jedis = RedisUtil.getRedisConn();
        jedis.sadd(ByteUtil.intToBytes(tagId), ByteUtil.intToBytes(imgId));
        byte[] toRedisKey = (tagId+"c").getBytes();
        jedis.del(toRedisKey);  // 清除旧的缓存

        jedis.close();
        return 0;
    }

    public byte[] getObjectByte(byte[] redisKey) {
        byte[] bytes = null;
        try (Jedis jedis = RedisUtil.getRedisConn()){
            bytes = jedis.get(redisKey);
        }
        return bytes;
    }

}
