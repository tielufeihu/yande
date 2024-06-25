package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1752
 * @description TODO
 * @since 2024/6/25 15:32
 */
public class Q1752 {

    public static void main(String[] args) {
        Q1752 q1752 = new Q1752();
        int[] nums = {1,3,2};
        System.out.println(q1752.check(nums));
    }

    public boolean check(int[] nums) {
        int n = nums.length, x = 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] < nums[i - 1]) {
                x = i;
                break;
            }
        }
        if (x == 0) {
            return true;
        }
        for (int i = x + 1; i < n; ++i) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return nums[0] >= nums[n - 1];
    }


    public void f() throws Throwable {
        super.finalize();
    }

}
