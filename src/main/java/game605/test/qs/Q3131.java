package game605.test.qs;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3131
 * @description TODO
 * @since 2024/8/8 10:02
 */
public class Q3131 {

    public int addedInteger(int[] nums1, int[] nums2) {
        // 排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int diff = nums2[0]-nums1[0];
        for (int i = 1; i < nums2.length; i++) {
            if(nums2[i]-nums1[i]!=diff){
                return -1;
            }
        }
        return diff;
    }

}
