package game605.test.qs;

import java.util.HashMap;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q290
 * @description TODO
 * @since 2024/7/8 15:59
 */
public class Q290 {

    public boolean wordPattern(String pattern, String s) {
        char[] patterns = pattern.toCharArray();
        String[] strings = s.split(" ");
        // 特判
        if (strings.length != patterns.length)  {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            if(!map.containsKey(patterns[i])){
                if(map.containsValue(strings[i])){
                    return false;
                }
                map.put(patterns[i],strings[i]);
            }else {
                if(!map.get(patterns[i]).equals(strings[i])){
                    return false;
                }
            }
        }
        return true;
    }

}
