package game605.test.qs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1475
 * @description 商品折扣后的最终价格
 * @since 2024/6/19 16:32
 */
public class Q1475 {

    /**
     * 单调栈原地改
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            while (!stack.isEmpty() && prices[stack.peek()] >= price) {
                int idx = stack.pop();
                prices[idx] = prices[idx] - price;
            }
            stack.push(i);
        }
        return prices;
    }

}
