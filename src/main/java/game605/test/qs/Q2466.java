package game605.test.qs;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2466
 * @description TODO
 * @since 2024/7/30 15:58
 */
public class Q2466 {

    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] dp = new int[high+1];
        dp[zero]++;
        dp[one]++;
        for (int i = 1; i <= high; i++) {
            if(i-zero > 0){
                dp[i] = (dp[i] + dp[i-zero]) % 1000000007;
            }
            if(i-one > 0){
                dp[i] = (dp[i] + dp[i-one]) % 1000000007;
            }
        }
        int ret = 0;
        for (int i = low; i <= high; i++) {
            ret = (ret + dp[i]) % 1000000007;
        }
        return ret;
    }

    public static void main(String[] args) {
        Q2466 q2466 = new Q2466();
        System.out.println(q2466.countGoodStrings(3, 3, 1, 1));
    }

}
