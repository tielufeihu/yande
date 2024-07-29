package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q392
 * @description TODO
 * @since 2024/7/26 14:34
 */
public class Q392 {

    public boolean isSubsequence(String s, String t) {
        int ps = 0, pt = 0;
        while (ps < s.length() && pt < t.length()) {
            if (s.charAt(ps) == t.charAt(pt)) {
                ps++;
            }
            pt++;
        }
        return ps==s.length();
    }

}
