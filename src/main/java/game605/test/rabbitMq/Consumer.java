package game605.test.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import game605.utilx.ByteUtil;

/**
 * 消费者代码
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/8/8 10:42
 **/
public class Consumer {

    // 自动应答的消费者线程
    public void createAutoAckConsumer(String name){
        Thread thread = new Thread(()->{
            try (Channel c = RabbitMQUtils.getChannel()){
                c.basicConsume("QUEUE_NAME", true,
                        (consumerTag,msg)-> System.out.println("["+name+"]:消费消息：" + ByteUtil.bytesToString(msg.getBody())),
                        (consumerTag)-> System.out.println("["+name+"]:消费消息中断：" + consumerTag));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        thread.start();
    }

    // 手动应答的消费者线程
    public void createConsumer(String name){
        Thread thread = new Thread(()->{
            try (Channel c = RabbitMQUtils.getChannel()){
                c.basicConsume("QUEUE_NAME", false,
                        (consumerTag,msg)-> {
                            System.out.println("["+name+"]:消费消息：" + ByteUtil.bytesToString(msg.getBody()));
                            // 手动应答
                            /**
                             * basicAck(long deliveryTag, boolean multiple)
                             * deliveryTag : 应答的标识，表示应答的是哪一条消息，这非常重要
                             *              而这个标识就在msg.getEnvelope().getDeliveryTag()里面。
                             * multiple： 是否批量应答，这里是否，我们需要一个一个应答
                             */
                            c.basicAck(msg.getEnvelope().getDeliveryTag(),false);
                        },
                        (consumerTag)-> System.out.println("["+name+"]:消费消息中断：" + consumerTag));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        thread.start();
    }


    public static void main(String[] args) throws Exception{
        // 获取 channel
        /**
         * 值得注意的是， 这里的 channel 绑定消费者，
         * 当 channel 被关闭的时候， 相应的消费者也被中止
         * 所以使用 try with 包裹 channel 时 会直接退出。
         */
        Channel channel = RabbitMQUtils.getChannel();
        // 这里是使用回调的方式处理消息，不会阻塞
        channel.basicConsume("QUEUE_NAME",true,
                (consumerTag,msg)-> System.out.println("消费消息：" + ByteUtil.bytesToString(msg.getBody())),
                (consumerTag)-> System.out.println("消费消息：" + consumerTag));
    }

}
