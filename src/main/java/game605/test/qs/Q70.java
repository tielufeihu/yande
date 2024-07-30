package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q70
 * @description TODO
 * @since 2024/7/30 9:42
 */
public class Q70 {

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        // 爬上n级楼梯是 n-2级 + n-1级
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
