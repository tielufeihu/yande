package game605.test.qs;

import java.util.Arrays;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2860
 * @description TODO
 * @since 2024/9/4 16:58
 */
public class Q2860 {

    public int countWays(List<Integer> nums) {
        int[] a = nums.stream().mapToInt(i -> i).toArray();
        Arrays.sort(a);
        int ans = a[0] > 0 ? 1 : 0; // 一个学生都不选
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] < i && i < a[i]) {
                ans++;
            }
        }
        return ans + 1; // 一定可以都选
    }

}
