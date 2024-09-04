package game605.test.qs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

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

    //public static void main(String[] args) {
    //    Q3131 q3131 = new Q3131();
    //    System.out.println(q3131.addedInteger(new int[]{1,2,3,4,5,6},new int[]{6,7,8,9,10,11}));
    //}

    public static void main(String [] args) {
        int x = 11 & 9;
        int y = x ^ 3;
        System.out.println( y | 12 );
    }

    public int[] distinct(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }


}
