package game605.test.qs;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2740
 * @description TODO
 * @since 2024/7/26 10:01
 */
public class Q2740 {

    public int findValueOfPartition(int[] nums) {
        // 排序
        Arrays.sort(nums);
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length-1; i++) {
            ret = Math.min(ret, Math.abs(nums[i]-nums[i+1]));
        }
        return ret;
    }

    public static void main(String[] args) {

        Q2740 q2740 = new Q2740();
        System.out.println(q2740.findValueOfPartition(new int[]{84,11,100,100,75}));
    }

}
