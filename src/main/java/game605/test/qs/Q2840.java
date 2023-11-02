package game605.test.qs;

import java.util.HashMap;

/**
 * 判断通过操作能否让字符串相等 II
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/30 17:48
 **/
public class Q2840 {

    // 纯纯脑筋急转弯
    public boolean checkStrings(String s1, String s2) {
        HashMap<Character, Integer> s1Map = new HashMap<>();
        HashMap<Character, Integer> s2Map = new HashMap<>();

        int len = s1.length();
        for (int i = 0; i < len; i+=2) {
            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i), 0)+1);
            s2Map.put(s2.charAt(i), s2Map.getOrDefault(s2.charAt(i), 0)+1);
        }
        if(!s1Map.equals(s2Map)) return false;
        s1Map.clear();
        s2Map.clear();
        for (int i = 1; i < len; i+=2) {
            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i), 0)+1);
            s2Map.put(s2.charAt(i), s2Map.getOrDefault(s2.charAt(i), 0)+1);
        }
        return s1Map.equals(s2Map);
    }

}
