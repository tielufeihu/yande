package game605.test.qs;

import org.junit.Test;

/**
 * 卖掉股票的最佳时机 dp
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/25 17:45
 **/
public class Q121 {

    // dp 就是遍历的过程中存储有用的信息
    public int maxProfit(int[] prices) {
        // dp 记录前n天的最小值， 即可得出 第 n+1卖出的最大利润为 p[n+1] - min[n]
        int len = prices.length;
        int[] minDP = new int[len];
        int max = 0;
        for (int i = 0; i < len; i++) {
            if(i == 0){
                minDP[i] = prices[i];
                // 第一天刚刚买入，卖出必定是0
                continue;
            }
            if(prices[i] < minDP[i-1]){
                minDP[i] = prices[i];
            }else{
                minDP[i] = minDP[i-1];
            }
            // 计算今天卖出的收益
            int earnings = prices[i] - minDP[i-1];
            if(earnings > max)
                max = earnings;
        }
        return max;
    }

    @Test
    public void test01(){
        int res = maxProfit(new int[]{7,1,5,3,6,4});
        System.out.println(res);
    }

}
