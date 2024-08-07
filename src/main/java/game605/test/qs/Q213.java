package game605.test.qs;

public class Q213 {

    /**
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        return Math.max(nums[0] + rob1(nums, 2, n - 1), rob1(nums, 1, n));
    }

    // 198. 打家劫舍
    private int rob1(int[] nums, int start, int end) { // [start,end) 左闭右开
        int f0 = 0, f1 = 0;
        for (int i = start; i < end; ++i) {
            int newF = Math.max(f1, f0 + nums[i]);
            f0 = f1;
            f1 = newF;
        }
        return f1;
    }

}
