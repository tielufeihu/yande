package game605.test.qs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q398
 * @description TODO
 * @since 2024/7/9 18:08
 */
public class Q398 {

    // hash计数
    public char findTheDifference(String s, String t) {
        Map<Character,Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else {
                map.put(c,1);
            }
        }

        for (char c : t.toCharArray()) {
            if(!map.containsKey(c)){
                return c;
            }else {
                if(map.get(c)==1){
                    map.remove(c);
                }else {
                    map.put(c,map.get(c)-1);
                }
            }
        }
        return ' ';
    }

    // 数组计数
    public char findTheDifference2(String s, String t){
        int[] map = new int[26];

        for (char c : s.toCharArray()) {
            map[c-'a']++;
        }

        for (char c : t.toCharArray()) {
            if(map[c-'a']==0){
                return c;
            }else {
                map[c-'a']--;
            }
        }
        return ' ';
    }

    // 异或
    public char findTheDifference3(String s, String t) {
        int ret = 0;
        for (char ch: s.toCharArray()) {
            ret ^= ch;
        }
        for (char ch: t.toCharArray()) {
            ret ^= ch;
        }
        return (char) ret;
    }

    public static void main(String[] args) {
        Q398 q398 = new Q398();
        System.out.println(q398.findTheDifference3("abcd","abcde"));
    }

}
