package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两个数组的交集
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/30 18:07
 **/
public class Q349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        nums1 = Arrays.stream(nums1).distinct().toArray();
        nums2 = Arrays.stream(nums2).distinct().toArray();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) map.put(i, map.getOrDefault(i,0)+1);
        for (int i : nums2) map.put(i, map.getOrDefault(i,0)+1);
        return map.entrySet().stream()
                .filter(e -> e.getValue() == 2)
                .mapToInt(Map.Entry::getKey).toArray();
    }

    @Test
    public void t1(){
        System.out.println(Arrays.toString(intersection(new int[]{1, 2, 3, 4}, new int[]{2, 3, 4, 5})));
    }


}
