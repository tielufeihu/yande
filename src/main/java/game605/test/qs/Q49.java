package game605.test.qs;

import java.util.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q49
 * @description TODO
 * @since 2024/7/11 9:57
 */
public class Q49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String code = code(str);
            List<String> list = map.getOrDefault(code, new ArrayList<>());
            list.add(str);
            map.put(code, list);
        }
        return new ArrayList<>(map.values());
    }

    private String code(String str) {
        int[] counts = new int[26];
        Arrays.fill(counts, 0);
        for (char c : str.toCharArray()) {
            counts[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < counts.length; i++) {
            sb.append(counts[i]+'0');
        }
        return sb.toString();
    }

}
