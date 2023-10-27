package game605.test.qs;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 掷骰子等于目标和的方法数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/24 9:54
 **/
public class Q1155 {

    // 求种数，而不是求结果集。显然要用dp
    // 背包问题，我没看出来
    // 二维dp ，没想到
    // dp[i][j] 表示使用i个物品凑成j的方案数
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n+1][target+1];
        // 初始状态
        for (int i = 0; i < n+1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < target+1; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < target+1 && i <= k; i++){
            dp[1][i] = 1;
        }
        for (int i = k+1; i < target+1; i++){
            dp[1][i] = 0;
        }
        for (int i=2;i<=n;i++){
            for (int j = 0; j < target+1; j++) {
                int sum = 0;
                for (int l = 1; j-l>=0 && l <= k; l++) {
                    sum += dp[i-1][j-l];
                    sum %= 1000000007;
                }
                dp[i][j] = sum;
            }
        }
        return dp[n][target];
    }

    @Test
    public void t1(){
        //n = 1, k = 6, target = 3
        //n = 2, k = 6, target = 7  6
        //n = 30, k = 30, target = 500
        //对 109 + 7 取模
        System.out.println(numRollsToTarget(30,30,500));
    }

}
