package game605.test.qs;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 泰波那且数列
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/8 20:29
 **/
public class Q1134 {

    // 这个题递归非常简单，我纯纯晕递归都能随便写， 但是超时
    public int tribonacci(int n) {
        // 判断边界
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 1;
        return tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3);
    }

    // 看来这个题需要dp
    public int tribonacci2(int n) {
        // 判断边界
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 1;
        int[] dp = new int[n+1];
        dp[0] = 0; dp[1] = 1; dp[2] = 1;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        return dp[n];
    }

    @Test
    public void t01(){
        int ret = tribonacci2(25);
        System.out.println(ret);
    }

}
