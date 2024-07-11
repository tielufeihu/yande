package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q41
 * @description TODO
 * @since 2024/7/11 10:23
 */
public class Q41 {

    // 原地hash
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        // 先把负数变成N+1
        for (int i = 0; i < length; i++) {
            if(nums[i] <= 0){
                nums[i] = length + 1;
            }
        }
        // 再使用符号标记
        for (int i = 0; i < length; i++) {
            int val = Math.abs(nums[i]);
            if(val<=length){
                nums[val - 1] = -Math.abs(nums[val - 1]);
            }
        }
        for (int i = 0; i < length; i++) {
            if(nums[i] > 0){
                return i + 1;
            }
        }
        return length + 1;
    }


    public static void main(String[] args) {
        System.out.println(new Q41().firstMissingPositive(new int[]{7,8,9,11,12}));
    }

}
