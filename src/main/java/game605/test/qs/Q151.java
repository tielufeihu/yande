package game605.test.qs;

import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q151
 * @description TODO
 * @since 2024/7/26 14:21
 */
public class Q151 {

    public String reverseWords(String s) {
        String[] strings = s.trim().split(" ");
        StringBuilder ret = new StringBuilder();
        for (int i = strings.length-1; i >=0; i--) {
            String temp = strings[i].trim();
            if(!temp.isEmpty()){
                ret.append(strings[i].trim());
                ret.append(" ");
            }
        }
        return ret.toString().trim();
    }

    public static void main(String[] args) {
        Q151 q151 = new Q151();
        System.out.println(q151.reverseWords("  hello             world  "));
    }

}
