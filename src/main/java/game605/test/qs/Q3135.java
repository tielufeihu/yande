package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3135
 * @description TODO
 * @since 2024/8/13 10:00
 */
public class Q3135 {

    public boolean isArraySpecial(int[] nums) {
        if(nums.length == 1) return true;
        int prev = nums[0] % 2;
        for (int i = 1; i < nums.length; i++) {
            if(prev == 0 && nums[i] % 2 == 0) return false;
            if(prev == 1 && nums[i] % 2 == 1) return false;
            prev = nums[i] % 2;
        }
        return true;
    }

}
