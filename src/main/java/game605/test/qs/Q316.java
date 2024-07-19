package game605.test.qs;

import java.util.TreeSet;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q316
 * @description 去除重复字幕
 * @since 2024/7/18 17:58
 */
public class Q316 {

    public String removeDuplicateLetters(String s) {
        TreeSet<Character> characters = new TreeSet<>();
        for (char c : s.toCharArray()) {
            characters.add(c);
        }
        StringBuilder sb = new StringBuilder();
        characters.forEach(sb::append);
        return sb.toString();
    }

}
