package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2748
 * @description TODO
 * @since 2024/6/20 10:33
 */
public class Q2748 {

    // 暴力枚举
    public int countBeautifulPairs(int[] nums) {
        int ret = 0;
        int len = nums.length;
        for (int i = 0; i < len-1; i++) {
            int a = nums[i];
            while (a >= 10){
                a /= 10;
            }
            for (int j = i+1; j < len-1; j++) {
                if(gcd(a, (nums[j]%10))==1){
                    ret++;
                }
            }
        }
        return ret;
    }


    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
