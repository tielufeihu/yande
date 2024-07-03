package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1790
 * @description 仅交换一次就相等的字符串
 * @since 2024/7/1 17:25
 */
public class Q1790 {

    public boolean areAlmostEqual(String s1, String s2) {
        if(s1.equals(s2)){
            return true;
        }
        int count = 0;
        char c1 = 0, c2 = 0;
        boolean flag = false;
        if(s1.length() > s2.length()){
            String t = s1;
            s1 = s2;
            s2 = t;
        }
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if(count == 2){
                    return false;
                }
                if (count == 0) {
                    c1 = s1.charAt(i);
                    c2 = s2.charAt(i);
                }else if(count == 1){
                    if(c1 == s2.charAt(i) && c2 == s1.charAt(i)){
                        flag = true;
                    }
                }
                count++;
            }
        }
        return flag;
    }

}
