package game605.test.rabbitMq;

import org.junit.Test;

public class Test01 {

    @Test
    public void t01() {
        System.out.println("hello1");
        int a = 1;
        if(a == 1)
            throw new RuntimeException("ex01");
        System.out.println("hello2");
    }

}
