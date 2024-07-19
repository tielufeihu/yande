package game605.test.qs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q703
 * @description TODO
 * @since 2024/7/18 17:29
 */
public class Q703 {

    int k;
    PriorityQueue<Integer> stack = new PriorityQueue<>(((o1, o2) -> o2 - o1));

    public Q703(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            stack.add(num);
        }
    }

    public int add(int val) {
        stack.offer(val);
        if (stack.size() > k) {
            stack.poll();
        }
        return stack.peek();
    }

}
