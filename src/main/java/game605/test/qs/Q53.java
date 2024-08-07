package game605.test.qs;

public class Q53 {

    // 前缀后缀和超时
    public int maxSubArray(int[] nums) {
        // 前缀后缀和+遍历
        int len = nums.length;
        int[] prefixSum = new int[len+1];
        int[] suffixSum = new int[len+1];
        prefixSum[1] = nums[0];
        suffixSum[len-1] = nums[len-1];
        for (int i = 2; i <= len; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
        }
        for (int i = len-2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i+1] + nums[i];
        }
        int sum = prefixSum[len];
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int temp = sum - prefixSum[i] - suffixSum[j+1];
                ret = Math.max(temp,ret);
            }
        }
        return ret;
    }




    public static void main(String[] args) {
        Q53 q = new Q53();
        System.out.println(q.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

}
