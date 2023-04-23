package game605.utilx;

import java.util.Random;

//订单编号 or 其他编号生成器
public class NumberBuilder {

    //雪花算法+id
    public static String getOrderId(String userId){
        SnowflakeIdUtil sf = new SnowflakeIdUtil(0,0);
        return sf.nextId() + userId;
    }


    //用户id生成器  时间戳/1000 + 随机数
    public static String getUserId(String userId){
        long times = System.currentTimeMillis() / 1000;
        Random random = new Random(times);
        return String.valueOf(times) + String.valueOf(random.nextInt(1000));
    }

    //商家id生成器  时间戳/1000 + 随机数
    public static String getMerchantId(String userId){
        long times = System.currentTimeMillis() / 1000;
        Random random = new Random(times);
        return String.valueOf(times) + String.valueOf(random.nextInt(1000));
    }

    //商品id生成器  时间戳/1000 + 随机数
    public static String getGoodId(String userId){
        long times = System.currentTimeMillis() / 1000;
        Random random = new Random(times);
        return String.valueOf(times) + String.valueOf(random.nextInt(1000));
    }




}
