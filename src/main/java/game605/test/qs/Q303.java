package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q303
 * @description TODO
 * @since 2024/7/8 16:22
 */
public class Q303 {

    private int[] nums;
    private int[] sums;

    public Q303(int[] nums) {
        this.nums = nums;
        sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i-1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if(left == right) return nums[left];
        return sums[right] - ((left-1<0)?0:sums[left-1]);
    }

    public static void main(String[] args) {
         Q303 q303 = new Q303(new int[]{1,4,-6});
        System.out.println(q303.sumRange(1,2));
    }

}
