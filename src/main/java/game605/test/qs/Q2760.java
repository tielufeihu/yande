package game605.test.qs;

import org.junit.Test;

/**
 * 最长奇偶子数组
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/16 1:55
 **/
public class Q2760 {

    // 明显的贪心
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        boolean prevIsOdd = false;
        int arrLen = nums.length;
        int currLen = 0;
        int maxLen = 0;
        for (int i = 0; i < arrLen; i++) {
            // 判断是否超过门槛
            if(nums[i] > threshold){
                currLen = 0;
                continue;
            }
            // 当前数是不是奇数
            boolean currIsOdd = nums[i]%2==1;
            // 判断是否交替,这里必须偶数开始
            if(currLen==0){
                if(!currIsOdd){
                    currLen++;
                    if(currLen>=maxLen) maxLen=currLen;
                }
                prevIsOdd = currIsOdd;
                continue;
            }
            if(currIsOdd != prevIsOdd){
                // 此时是交替的
                currLen++;
                if(currLen>=maxLen) maxLen=currLen;
            }else {
                // 不交替了 重新计数
                currLen = !currIsOdd?1:0;
            }
            prevIsOdd = currIsOdd;
        }
        return maxLen;
    }

    @Test
    public void t1(){
        // 3,2,5,4
        System.out.println(longestAlternatingSubarray(new int[]{2,2,5,1,6,7,8},111));
    }

}
