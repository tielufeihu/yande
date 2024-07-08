package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q724
 * @description TODO
 * @since 2024/7/8 9:50
 */
public class Q724 {

    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int l = 0;
        int r = sum - nums[0];
        if(l == r){
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            l += nums[i - 1];
            r -= nums[i];
            if(l == r){
                return i;
            }
        }
        return -1;
    }

}
