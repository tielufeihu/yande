package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;

/**
 * 零钱兑整
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/27 1:11
 **/
public class Q322 {

    public int coinChange(int[] coins, int amount) {
        if(amount==0) return 0;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int coin : coins) {
            if(coin<=amount) dp[coin] = 1;
        }
        for (int i = 1; i <= amount; i++) {
            if(dp[i] == 1)
                continue;
            int currMin = dp[i];
            for (int coin : coins)
                if(i-coin>=0 && dp[i-coin]<currMin) currMin = dp[i-coin];
            if(currMin!=Integer.MAX_VALUE) dp[i] = currMin+1;
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }

    @Test
    public void t1(){
        /**
         * 输入：coins = [1, 2, 5], amount = 11
         * 输出：3
         * 解释：11 = 5 + 5 + 1
         */
        System.out.println(coinChange(new int[]{1, 2, 5},11));
    }

}
