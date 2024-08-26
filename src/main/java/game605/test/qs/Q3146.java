package game605.test.qs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3146
 * @description TODO
 * @since 2024/8/26 17:10
 */
public class Q3146 {

    public int findPermutationDifference(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        int ret = 0;
        for (int i = 0; i < t.length(); i++) {
            // 计算距离
            ret += Math.abs(map.get(t.charAt(i)) - i);
        }
        return ret;
    }

}
