package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2390
 * @description TODO
 * @since 2024/9/14 9:54
 */
public class Q2390 {

    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '*' && sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Q2390 q2390 = new Q2390();
        System.out.println(q2390.removeStars("leet**cod*e"));
    }

}
