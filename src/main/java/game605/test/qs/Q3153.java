package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3153
 * @description TODO
 * @since 2024/8/30 9:24
 */
public class Q3153 {

    // 暴力
    public long sumDigitDifferences(int[] nums) {
        long ret = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                ret += diffCount(nums[i], nums[j]);
            }
        }
        return ret;
    }


    // 差异位
    public int diffCount(int a, int b) {
        int ret = 0;
        while (a!=0 && b!=0){
            if ((a % 10) != (b % 10)) {
                ret++;
            }
            a /= 10;
            b /= 10;
        }
        return ret;
    }

    public static void main(String[] args) {
        Q3153 q3153 = new Q3153();
        System.out.println(q3153.sumDigitDifferences(new int[]{13,23,12}));
    }

}
