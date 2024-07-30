package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q377
 * @description 组合4
 * @since 2024/7/30 11:19
 */
public class Q377 {

    // 经典dp题
    public int combinationSum4(int[] nums, int target) {
        // 考虑最后一步  是target - nums[i]
        int[] dp = new int[target + 1];
        dp[0] = 0;
        // 初始化
        for (int num : nums) {
            if (num <= target) {
                dp[num]++;
            }
        }
        for (int i = 1; i < target + 1; i++) {
            for (int num : nums) {
                if (i - num > 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}
