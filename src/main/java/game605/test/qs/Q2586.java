package game605.test.qs;

import java.util.HashSet;
import java.util.Set;

/**
 * 统计范围内的元音字符串数
 */
public class Q2586 {

    public int vowelStrings(String[] words, int left, int right) {
        int ret = 0;
        Set<Character> ocharSet = new HashSet<>();
        ocharSet.add('a');
        ocharSet.add('e');
        ocharSet.add('i');
        ocharSet.add('o');
        ocharSet.add('u');
        for (int i = left; i <= right ; i++) {
            Character head = words[i].charAt(0);
            Character tail = words[i].charAt(words[i].length()-1);
            if(ocharSet.contains(head) && ocharSet.contains(tail)) ret++;
        }
        return ret;
    }

}
