package game605.test.qs;

import java.util.PriorityQueue;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1962
 * @description TODO
 * @since 2024/8/9 15:18
 */
public class Q1962 {

    // 堆排序
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for (int pile : piles) {
            pq.offer(pile);
        }
        for (int i = 0; i < k; i++) {
            int num = pq.poll();
            pq.offer(num - num / 2);
        }
        return pq.stream().mapToInt(Integer::intValue).sum();
    }

}
