package game605.test.learn.spi;

import java.util.ServiceLoader;

/**
 * @author Koyou
 * @version 1.0.0
 * @className SPI_Learn
 * @description TODO
 * @since 2024/8/26 13:48
 */
public class SPI_Learn {

    public static void main(String[] args) {
        ServiceLoader<HelloService> helloServices = ServiceLoader.load(HelloService.class);
        // 加载不到， 因为实现类要放到META-INF/services/目录下， 目前没有放
        for (HelloService helloService : helloServices) {
            helloService.hello();
        }

    }

}
