package game605.test.learn.spi;

import java.util.UUID;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Hello2
 * @description TODO
 * @since 2024/8/26 13:52
 */
public class Hello2 implements HelloService {

    private String s1;
    private transient String s2;

    @Override
    public void hello() {
        System.out.println("Hello2");
    }

    @Override
    public String doSomething() {
        return UUID.randomUUID().toString();
    }
}
