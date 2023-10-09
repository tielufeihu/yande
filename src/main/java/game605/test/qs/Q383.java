package game605.test.qs;

import java.util.HashMap;

/**
 * 赎金信
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/9 11:33
 **/
public class Q383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            if(!map.containsKey(c)){
                map.put(c, 1);
            }else {
                map.put(c, map.get(c)+1);
            }
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if(!map.containsKey(c))
                return false;
            int num = map.get(c) - 1;
            map.put(c, num);
            if(num < 0)
                return false;
        }
        return true;
    }

}
