package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q58
 * @description TODO
 * @since 2024/7/19 18:19
 */
public class Q658 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 找到位置后，双指针扩展
        int idx = Arrays.binarySearch(arr, x);
        List<Integer> ret = new ArrayList<>();
        ret.add(arr[idx]);
        

    }

}
