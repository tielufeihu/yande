package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2970
 * @description TODO
 * @since 2024/7/10 9:41
 */
public class Q2970 {


    // 555 感觉挺对的 O(n)解法，结果还是120用例过不去
    public int incremovableSubarrayCount(int[] nums) {
        if(nums.length == 1) {
            return 1;
        }

        // 先找出不连续的个数
        int exception = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] - nums[i - 1] <= 0) {
                exception++;
            }
        }
        int count = nums.length - exception;
        return exception == nums.length+1?3:((count+1)*count/2) + exception;
    }


    // 暴力
    public int incremovableSubarrayCount2(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isIncreasing(nums, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    public boolean isIncreasing(int[] nums, int l, int r) {
        for (int i = 1; i < nums.length; i++) {
            if (i >= l && i <= r + 1) {
                continue;
            }
            if (nums[i] <= nums[i - 1]) {
                return false;
            }
        }
        if (l - 1 >= 0 && r + 1 < nums.length && nums[r + 1] <= nums[l - 1]) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        Q2970 q2970 = new Q2970();
        System.out.println(q2970.incremovableSubarrayCount(new int[]{8,7,6,6}));
    }

}
