package game605.test.qs;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q209
 * @description TODO
 * @since 2024/7/26 15:42
 */
public class Q209 {

    // 枚举超时
    public int minSubArrayLen(int target, int[] nums) {
        if(Arrays.stream(nums).sum() < target){
            return 0;
        }
        int wSize = 1;
        while (wSize <= nums.length){
            for (int i = 0; i < nums.length-wSize+1; i++) {
                // 计算窗口和
                int sum = 0;
                for (int j = i; j < i+wSize; j++) {
                    sum += nums[j];
                }
                if(sum >= target){
                    return wSize;
                }
            }
            wSize++;
        }
        return 0;
    }


    // 双指针
    public int minSubArrayLen2(int target, int[] nums) {
        if(Arrays.stream(nums).sum() < target){
            return 0;
        }
        int p1=0,p2=0;
        int sum = 0;
        int ret = nums.length;
        while (p2< nums.length && p1<=p2){
            if(sum >= target){
                ret = Math.min(ret,p2-p1);
                sum -= nums[p1];
                p1++;
            }else {
                sum += nums[p2];
                p2++;
            }
        }
        while (sum >= target && p1 < nums.length){
            ret = Math.min(ret,p2-p1);
            sum -= nums[p1];
            p1++;
        }

        return ret;
    }


    public static void main(String[] args) {

        Q209 q209 = new Q209();
        System.out.println(q209.minSubArrayLen2(7, new int[]{2,3,1,2,4,3}));
    }

}
