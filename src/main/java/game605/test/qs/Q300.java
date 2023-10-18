package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;

/**
 * 最长递增子序列
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/18 21:01
 **/
public class Q300 {

    // 评论区大神 双95
    public int lengthOfLIS2(int[] nums) {
        int[] dp =  new int[nums.length];
        int ans=0;
        Arrays.fill(dp,-11111);
        for(int i=0;i<nums.length;i++){
            int temp = Arrays.binarySearch(dp,0,ans+1,nums[i]);   //二分区域搜索
            if(temp<0){
                if(-temp-1 ==  ans+1){     //如果是当前的最大值，则直接赋值
                    dp[ans++] = nums[i];
                }
                else{
                    dp[-temp-1] = nums[i]; //否则使用更小的值进行替换
                }
            }
        }
        return ans;
    }

    // 看题解还是懂了的  我的dp
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        // 初始状态填充1
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            // 开始往前找
            int tempMax = 0;
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i] && dp[j]>tempMax){
                    tempMax = dp[j];
                }
            }
            dp[i] = tempMax+1;
        }
        Arrays.sort(dp);
        return dp[len-1];
    }

    // 先不想别的，尝试暴力解挨个， 显然不行，并不是遇到大的就能取的
    @Deprecated
    public int lengthOfLIS1(int[] nums) {
        int maxLen = 1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int prev = nums[i];
            maxLen = 1;
            for (int j = 1; j < len; j++) {
                int curr = nums[j];
                if(curr > prev){
                    prev = curr;
                    maxLen++;
                }
            }
        }

        return maxLen;
    }

    @Test
    public void t1(){
        System.out.println(lengthOfLIS2(new int[]{10,9,2,5,3,7,101,18}));
    }


}
