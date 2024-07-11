package game605.test.qs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q42
 * @description 接雨水
 * @since 2024/7/11 11:12
 */
public class Q42 {


    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int ret = 0;
        for (int i = 0; i < height.length; i++) {
            ret += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ret;
    }

    // 单调栈
    public int trap2(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int ret = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = height[stack.pop()];
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currW = i - left - 1;
                int currH = Math.min(height[left], height[i]) - top;
                ret += currW * currH;
            }
            stack.push(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        new Q42().trap2(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
    }

}
