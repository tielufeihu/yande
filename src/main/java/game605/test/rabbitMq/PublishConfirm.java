package game605.test.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import org.python.antlr.ast.Str;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 发布确认
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/8/8 10:24
 **/
public class PublishConfirm {

    /**
     * 异步发布确认
     * @param messages  要发布的消息list
     * @return  发送失败的消息list
     * @throws Exception
     */
    public List<String> asyncConfirm(List<String> messages) throws Exception{
        // 获取信道
        Channel channel = RabbitMQUtils.getChannel();
        // 待确认 map 区
        ConcurrentHashMap<Long, String> waitAckMap = new ConcurrentHashMap<>();
        // 发送失败 准备重发 list
        List<String> nackList = new Vector<>();  // 线程安全版的list
        // 创建 mq消息队列 (已有队列则不用创建)
        channel.queueDeclare("QUEUE_NAME", true, true, false, null);
        // 开启发送方确认模式
        channel.confirmSelect();
        // 绑定 ack监听器 和 nack 监听器
        ConfirmListener confirmListener = channel.addConfirmListener(
                // 确认回调
                (deliveryTag, multiple) ->{
                    System.out.println("确认消息" + deliveryTag);
                    // 收到ack后 删除 待确认map中相应的元素
                    waitAckMap.remove(deliveryTag);
                    // 输出当前map 确认是否有效
                    System.out.println("当前 waitMap" + waitAckMap);
                },
                // nack回调
                (deliveryTag, multiple) ->{
                    System.out.println("nack 消息" + deliveryTag);
                    // 将这条消息加入到 nackList里
                    nackList.add(waitAckMap.get(deliveryTag));
                }
        );
        // 发送编号（deliveryTag） 从1开始
        long publishSeqNo = 1L;
        // 发送消息
        for (String msg: messages) {
            channel.basicPublish("", "QUEUE_NAME", null, msg.getBytes(StandardCharsets.UTF_8));
            // 加入到待确认 map中
            waitAckMap.put(publishSeqNo, msg);
            publishSeqNo = channel.getNextPublishSeqNo();
        }
        return nackList;
    }

    public static void main(String[] args) throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("消息1");
        messages.add("消息2");
        messages.add("消息3");
        messages.add("消息4");
        messages.add("消息5");
        messages.add("消息6");
        messages.add("消息7");
        List<String> nackList = new PublishConfirm().asyncConfirm(messages);
        // 可以重发
        if(nackList.size() > 0){
            System.out.println(nackList);
            new PublishConfirm().asyncConfirm(nackList);
        }
    }

}
