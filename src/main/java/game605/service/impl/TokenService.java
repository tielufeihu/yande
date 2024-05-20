package game605.service.impl;

import game605.myRedis.RedisService;
import game605.utilx.ByteUtil;
import game605.utilx.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.yml")
public class TokenService {

    @Autowired
    RedisService rs;

    private static SnowflakeIdUtil tokenBuilder;
    @Value("${token-config.save-time}")
    public int saveTime;


    // 静态注入 雪花算法工具类
    static {
        tokenBuilder = new SnowflakeIdUtil(0,0);
    }

    // 为用户生成一个Token 并保存到redis数据库
    public Long getTokenAndToRedis(int userId){
        Long token = tokenBuilder.nextId();   // 雪花算法util
        rs.saveToken(userId,token,saveTime);  // 默认有效时间为14天
        return token;
    }

    // 检测Token是否有效 如果有效返回 userid 无效返回 -1
    public int checkTokenAndGetUserId(Long token){
        byte[] userId = rs.checkToken(ByteUtil.longToBytes(token));
        if(userId == null)
            return -1;
        else
            return ByteUtil.bytesToInt(userId);
    }

    // 刷新令牌时间
    public int refreshTokenTime(Long token){
        return rs.setTokenTime(token,saveTime);
    }

    // 判断 用户id 为x 的用户是否已有 token  如果有返回token 无返回null
    public Long isHaveTokenFromUserId(int userId){
        // 废弃
        return null;
    }

    /*
    @Test
    public void t01(){
        Long token = tokenBuilder.nextId();
        System.out.println(token);
    }
     */

}
