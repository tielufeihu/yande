package game605.test.qs;

import java.util.PriorityQueue;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1337
 * @description 前k
 * @since 2024/6/14 11:44
 */
public class Q1337 {


    class Node1337 {
        public int sum;
        public int idx;
        public Node1337(int sum, int idx) {
            this.sum = sum;
            this.idx = idx;
        }
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<Node1337> queue = new PriorityQueue<>((o1, o2) -> {
            if(o1.sum == o2.sum){
                return o1.idx-o2.idx;
            }
            return o1.sum-o2.sum;
        });
        int row = mat.length;
        for (int i = 0; i < row; i++) {
            // 这边可以二分
            int[] temp = mat[i];
            // 对temp执行二分查找，找到第一个0的位置
            int sum = binarySearch(temp);
            queue.add(new Node1337(sum, i));
        }
        // 取出前k个
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = queue.poll().idx;
        }
        return ret;
    }


    // 二分查找(必须是特化的二分)
    public int binarySearch(int[] nums) {
        // 特判
        if(nums[0] == 0){
            return 0;
        }
        if(nums[nums.length-1] == 1){
            return nums.length;
        }
        int left = 0;
        int right = nums.length-1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == 1) {
                if(nums[mid+1] == 0){
                    return mid+1;
                }
                // 向右查找
                left = mid + 1;
            } else if(nums[mid] == 0){
                if(nums[mid-1] == 1){
                    return mid;
                }
                // 向左查找
                right = mid - 1;
            }else {
                return -1;
            }
        }
        return -1;
    }


}
