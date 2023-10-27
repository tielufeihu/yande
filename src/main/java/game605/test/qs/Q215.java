package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/24 16:06
 **/
public class Q215 {

    // 确实是很快就想到用堆排序了，但是只会调库不会自己手写堆
    public int findKthLargest(int[] nums, int k) {
        // 堆排序，维护前k个值
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            priorityQueue.add(Integer.MIN_VALUE);
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= priorityQueue.peek()){
                priorityQueue.offer(nums[i]);
                priorityQueue.poll();
            }
        }
        return priorityQueue.poll();
    }

    @Test
    public void t1(){
        //[3,2,1,5,6,4], k = 2
        //[3,2,3,1,2,4,5,5,6], k = 4
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }

}
