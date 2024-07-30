package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q242
 * @description TODO
 * @since 2024/7/29 14:47
 */
public class Q242 {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        int[] counts = new int[26];
        int len = s.length();
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        for (int i = 0; i < len; i++) {
            counts[c1[i]-'a']++;
            counts[c2[i]-'a']--;
        }
        // 判断是否覆盖
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0){
                return false;
            }
        }
        return true;
    }

}
