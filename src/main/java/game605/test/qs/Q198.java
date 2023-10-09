package game605.test.qs;

import org.junit.Test;

/**
 * 打家劫舍 一维dp
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/28 15:48
 **/
public class Q198 {

    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return Math.max(dp[len-1], dp[len-2]);
    }

    @Test
    public void t2(){
        System.out.println(rob(new int[]{2,7,9,3,1}));
    }

}
