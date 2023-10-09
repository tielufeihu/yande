package game605.test.qs;

import org.junit.Test;

/**
 * 使用最小花费爬楼梯  （经典爬楼梯问题）
 * 这个题是一个典型的dp
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/8 21:12
 **/
public class Q746 {

    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        return Math.min(dp[len-1], dp[len-2]);
    }

    @Test
    public void t1(){
        System.out.println(minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
    }

}
