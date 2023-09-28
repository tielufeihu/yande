package game605.test.qs;

/**
 * 打家劫舍 一维dp
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/28 15:48
 **/
public class Q198 {

    public int rob(int[] nums) {
        int[] dpSum = new int[nums.length];
        dpSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i-1];
            int na1 = nums[i];
            dpSum[i] = dpSum[i-1] - n + Math.max(n,na1);
        }
        return dpSum[nums.length-1];
    }

}
