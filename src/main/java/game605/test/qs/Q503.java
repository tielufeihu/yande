package game605.test.qs;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q503
 * @description 下一个更大元素 II
 * @since 2024/6/24 14:27
 */
public class Q503 {

    public ThreadLocal<Integer> localInt = new ThreadLocal<>();

    // 先试暴力枚举
    public int[] nextGreaterElements(int[] nums) {
        int[] ret = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = (i+1) % nums.length;
            while (j!=i){
                if (nums[j] > nums[i]){
                    ret[i] = nums[j];
                    break;
                }
                j = (j+1) % nums.length;
            }
            if(j == i) ret[i] = -1;
        }
        return ret;
    }


    // 单调栈
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }


    public static void main(String[] args) {
        Q503 q = new Q503();
        System.out.println(Arrays.toString(q.nextGreaterElements2(new int[]{1,2,1})));
    }

}
