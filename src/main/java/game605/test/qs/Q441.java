package game605.test.qs;

import org.junit.Test;

/**
 * 排列硬币
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/24 13:37
 **/
public class Q441 {

    public int arrangeCoins(int n) {
        return (int) Math.floor((-0.5 + Math.sqrt((0.5 * 0.5) - (4 * 0.5 * (-n)))));
    }

    @Test
    public void t1(){
        System.out.println(arrangeCoins(5));
    }

}
