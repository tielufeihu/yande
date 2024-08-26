package game605.test.learn.spi;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Hello1
 * @description TODO
 * @since 2024/8/26 13:50
 */
public class Hello1 implements HelloService {

    @Override
    public void hello() {
        System.out.println("hello1");
    }

    @Override
    public String doSomething() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("hello1").append(i);
        }
        return sb.toString();
    }

}

