package game605.test.qs;

public class Q1749 {

    public int maxAbsoluteSum(int[] nums) {
        if(nums.length == 1){
            return Math.abs(nums[0]);
        }

        int ret = 0;
        int[] dp = new int[nums.length];
        dp[0] = Math.abs(nums[0]);
        ret = Math.max(ret, dp[0]);
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(Math.abs(nums[i]+dp[i-1]), nums[i]);
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

}
