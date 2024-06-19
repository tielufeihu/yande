package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1475
 * @description 商品折扣后的最终价格
 * @since 2024/6/19 16:32
 */
public class Q1475 {

    /**
     * 题意理解错误，简单迭代即可
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        if(prices.length<=1){
            return prices;
        }
        // 记录 dp[i] 之后最大值
        int[] dp = new int[prices.length];
        dp[prices.length-1] = prices[prices.length-1];
        for (int i = prices.length-2; i >= 0; i--) {
            dp[i] = Math.max(prices[i], dp[i + 1]);
        }
        for (int i = 0; i < prices.length-1; i++) {
            int t = prices[i];
            for (int j = i+1; j < prices.length-1; j++) {
                if(dp[j] < t){
                    t = t - dp[j];
                    break;
                }
            }
            prices[i] = t;
        }
        return prices;
    }

}
