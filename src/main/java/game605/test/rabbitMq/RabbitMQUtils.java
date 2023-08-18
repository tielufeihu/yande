package game605.test.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbit工具类
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/8/7 10:52
 **/
public class RabbitMQUtils {

    private static final Connection conn;

    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        try {
            conn = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public static Channel getChannel() throws IOException {
        return conn.createChannel();
    }

}
