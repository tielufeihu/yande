package game605.myRedis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

@Component
@PropertySource("classpath:application.yml")
public class RedisUtil {

    private RedisUtil() {}
    private static JedisPool jedisPool;
    private static int maxTotal;
    private static int maxWaitMillis;
    private static String host;
    private static int port;

    @Value("${redis.max-total}")
    public void setMaxTotal(int maxTotal) {
        RedisUtil.maxTotal = maxTotal;
    }

    @Value("${redis.max-wait-millis}")
    public void setMaxWaitMillis(int maxWaitMillis) {
        RedisUtil.maxWaitMillis = maxWaitMillis;
    }

    @Value("${redis.host}")
    public void setHost(String host) {
        RedisUtil.host = host;
    }

    @Value("${redis.port}")
    public void setPort(int port) {
        RedisUtil.port = port;
    }

    /*读取jedis.properties配置文件*/

    static{
        // ResourceBundle rb = ResourceBundle.getBundle("jedis");
        maxTotal = 200;  //Integer.parseInt(rb.getString("maxtotal"));
        maxWaitMillis = 5000;   //Integer.parseInt(rb.getString("maxwaitmillis"));
        host = "127.0.0.1";  //rb.getString("host");
        port = 6379;  //Integer.parseInt(rb.getString("port"));
    }

    /*创建连接池*/
    static{
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPool = new JedisPool(jedisPoolConfig,host,port);
    }

    /*获取jedis*/
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    /*关闭Jedis*/
    public static void close(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }

    public static Jedis getRedisConn(){
        //return new Jedis("localhost",6379);
        return getJedis();
    }

}
