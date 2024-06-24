package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q520
 * @description TODO
 * @since 2024/6/24 17:18
 */
public class Q520 {

    public boolean detectCapitalUse(String word) {
        char[] chars = word.toCharArray();
        int count = 0;
        for (char aChar : chars) {
            if(aChar>='A' && aChar<='Z'){
                count++;
            }
        }
        if(count == word.length() || count == 0){
            return true;
        }
        return count == 1 && chars[0] >= 'A' && chars[0] <= 'Z';
    }

}
