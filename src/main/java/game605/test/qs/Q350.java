package game605.test.qs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q350
 * @description TODO
 * @since 2024/7/8 18:26
 */
public class Q350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        // 计数Hash
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> ret = new ArrayList<>();
        for (int n : nums1) {
            if(map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            }else {
                map.put(n, 1);
            }
        }
        for (int n : nums2) {
            if(map.containsKey(n) && map.get(n)>0){
                ret.add(n);
                map.put(n, map.get(n) - 1);
            }
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

}
