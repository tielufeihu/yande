package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1404
 * @description TODO
 * @since 2024/8/20 16:34
 */
public class Q1404 {

    public int numSteps(String s) {
        // 末尾判断
        int count = 0;
        while (!"1".equals(s)) {
            if(s.charAt(s.length() - 1) == '1') {
                // 是奇数， 变成偶数，末尾加1
                s = addOne(s);
            }else {
                // 是偶数，减1
                s = s.substring(0, s.length() - 1);
            }
            count++;
        }
        return count;
    }

    private String addOne(String s) {
        // 数字字符串s数值加一
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == '1') {
            s = s.substring(0, i) + "0" + s.substring(i + 1);
            i--;
        }
        if(i >= 0) {
            s = s.substring(0, i) + "1" + s.substring(i + 1);
            return s;
        }
        return "1" + s;
    }

}
