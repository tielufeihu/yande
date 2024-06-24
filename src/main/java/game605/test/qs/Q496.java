package game605.test.qs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q496
 * @description 下一个更大元素（单调栈）
 * @since 2024/6/24 15:35
 */
public class Q496 {

    public static void main(String[] args) {
        Q496 q496 = new Q496();
        int[] nums1 = {1,6,5,2,4};
        int[] nums2 = {5,4,1,2,6};
        int[] res = q496.nextGreaterElement(nums1, nums2);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    // 哈希表+单调栈
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // kv idx val
        Map<Integer,Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]){
                map.put(nums2[stack.pop()], nums2[i]);
            }
            stack.push(nums2[i]);
        }

        // 获取结果
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i],-1);
        }
        return res;
    }

}
