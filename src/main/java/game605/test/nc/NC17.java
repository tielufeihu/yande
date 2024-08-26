package game605.test.nc;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC17
 * @description 最大回文子串长度
 * @since 2024/8/26 10:58
 */
public class NC17 {

    public int getLongestPalindrome (String A) {
        if (A == null || A.isEmpty()) {
            return 0;
        }
        int len = 1;
        char[] chars = A.toCharArray();
        // 两端扩散
        for (int i = 0; i < chars.length; i++) {
            int loopNum = 1, temp = 1;
            while (i-loopNum>=0 && i+loopNum<chars.length && chars[i-loopNum] == chars[i+loopNum]) {
                temp += 2;
                loopNum++;
            }
            len = Math.max(len, temp);
            // 如果前一个相同则以两个为核心再扩散一遍
            if (i-1>=0 && chars[i-1] == chars[i]) {
                loopNum = 1;
                temp = 2;
                while (i-loopNum-1>=0 && i+loopNum<chars.length && chars[i-loopNum-1] == chars[i+loopNum]) {
                    temp += 2;
                    loopNum++;
                }
            }
            len = Math.max(len, temp);
        }
        return len;
    }

    public static void main(String[] args) {
        // "baabccc"
        NC17 nc17 = new NC17();
        int i = nc17.getLongestPalindrome("baabccc");
        System.out.println(i);
    }

}
