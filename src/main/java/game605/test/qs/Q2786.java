package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2786
 * @description 获取最大的分数
 * @since 2024/6/14 10:16
 */
public class Q2786 {

    public long maxScore(int[] nums, int x) {
        // 这个dp数组存前面奇数跳最大 以及 偶数跳最大
        long[] dp = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        dp[nums[0]%2] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[nums[i]%2] = Math.max(dp[nums[i]%2]+nums[i], dp[(nums[i] + 1)%2] + nums[i] - x);
        }
        return Math.max(dp[0], dp[1]);
    }

}
