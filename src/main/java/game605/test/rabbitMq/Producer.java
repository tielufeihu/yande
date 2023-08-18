package game605.test.rabbitMq;
import com.rabbitmq.client.*;
/**
 * 生产者代码
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/8/8 11:35
 **/
public class Producer {
    public static void main(String[] argv) throws Exception {
        // 连接工厂  http://127.0.0.1:15672   guest
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");    // 127.0.0.1
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        // 创建连接 和 channel
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // 创建一个 Declare 队列
            /**
             * Declare a queue
             * @see com.rabbitmq.client.AMQP.Queue.Declare
             * @see com.rabbitmq.client.AMQP.Queue.DeclareOk
             * @param queue 队列名
             * @param durable 是否持久化
             * @param exclusive  是否允许多个消费者消费
             * @param autoDelete 是否自动删除（当很久没用使用这个队列的时候）
             * @param arguments 其他的参数
             * @return a declaration-confirm method to indicate the queue was successfully declared
             * @throws java.io.IOException if an error is encountered
             */
            channel.queueDeclare("QUEUE_NAME", false, false, false, null);
            String message = "Hello World!";
            // 发送一条消息
            /**
             * 1. 交换机名
             * 2. 路由Key 本次是队列名
             * 3. 参数 本次没有
             * 4. 消息体
             */
            channel.basicPublish("", "QUEUE_NAME", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

}
