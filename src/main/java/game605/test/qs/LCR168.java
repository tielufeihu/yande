package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className LCR168
 * @description 丑数 1 <= n <= 1690
 * @since 2024/9/2 16:35
 */
public class LCR168 {

    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        int p2=1, p3=1, p5=1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(Math.min(dp[p2]*2, dp[p3]*3), dp[p5]*5);
            if (dp[i] == dp[p2]*2) p2++;
            if (dp[i] == dp[p3]*3) p3++;
            if (dp[i] == dp[p5]*5) p5++;
        }
        return dp[n];
    }

}
