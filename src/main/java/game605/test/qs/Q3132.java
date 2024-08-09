package game605.test.qs;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3132
 * @description TODO
 * @since 2024/8/9 1:52
 */
public class Q3132 {

    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // 枚举
        for (int i = 2; i > 0; i--) {
            // 差值
            int diff = nums1[i] - nums2[0];
            // 双指针
            int left = i + 1, right = 1;
            while (left < m && right < n) {
                if (nums1[left] - nums2[right] == diff) {
                    ++right;
                }
                ++left;  // 左指针必定移动
            }
            if (right == n) {
                return nums2[0] - nums1[i];
            }
        }
        return 0;
    }


}
