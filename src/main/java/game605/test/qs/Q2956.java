package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2956
 * @description TODO
 * @since 2024/7/17 14:05
 */
public class Q2956 {

    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        // 哈希表 给点范围[1,100]使用数组替代
        int[] hash1 = new int[101];
        int[] hash2 = new int[101];
        for (int i : nums1) {
            hash1[i]++;
        }
        for (int i : nums2) {
            hash2[i]++;
        }
        int[] ret = new int[2];
        for (int i : nums1) {
            if(hash2[i] != 0){
                ret[0]++;
            }
        }
        for (int i : nums2) {
            if(hash1[i] != 0){
                ret[1]++;
            }
        }
        return ret;
    }

}
