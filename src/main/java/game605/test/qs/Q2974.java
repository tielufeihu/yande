package game605.test.qs;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2974
 * @description TODO
 * @since 2024/7/12 9:40
 */
public class Q2974 {

    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int[] ret = new int[nums.length];
        int n1,n2;
        for (int i = 0; i < nums.length; i+=2) {
            n1 = nums[i];
            n2 = nums[i+1];
            ret[i] = n2;
            ret[i+1] = n1;
        }
        return ret;
    }

}
