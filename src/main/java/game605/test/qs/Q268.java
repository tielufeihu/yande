package game605.test.qs;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q268
 * @description TODO
 * @since 2024/7/8 15:41
 */
public class Q268 {

    // 标记简单
    public int missingNumber(int[] nums) {
        boolean[] flags = new boolean[nums.length+1];
        Arrays.fill(flags, false);
        for (int num : nums) {
            flags[num] = true;
        }
        for (int i = 0; i < nums.length+1; i++) {
            if (!flags[i]) {
                return i;
            }
        }
        return -1;
    }

    public int missingNumber2(int[] nums) {
        return (nums.length+1) * nums.length / 2 - Arrays.stream(nums).sum();
    }

}
