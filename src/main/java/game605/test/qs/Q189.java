package game605.test.qs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 轮转数组
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/23 17:43
 **/
public class Q189 {

    // 借助队列，最直观
    public void rotate1(int[] nums, int k) {
        Queue<Integer> queue = new LinkedList<>();
        int len = nums.length;
        for (int i = len-1; i >= 0; i--) {
            queue.offer(nums[i]);
        }
        k = k % len;
        for (int i = 0; i < k; i++) {
            queue.offer(queue.poll());
        }
        for (int i = len-1; i >= 0; i--) {
            nums[i] = queue.poll();
        }
    }

    // 直接子串+拧
    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        k  = k % len;
        int[] tempK = new int[k];
        int c = 0;
        for (int i = len-k; i < len; i++) {
            tempK[c++] = nums[i];
        }
    }

}
