package game605.test.nc;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC10
 * @description 大数乘法
 * @since 2024/8/23 14:35
 */
public class NC10 {

    public String solve (String s, String t) {
        // write code here
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        StringBuilder sb = new StringBuilder();
        int [] res = new int[sArr.length + tArr.length];
        for (int i = sArr.length - 1; i >= 0; i--) {
            for (int j = tArr.length - 1; j >= 0; j--) {
                res[i+j+1] += (sArr[i] - '0') * (tArr[j] - '0');
            }
            for (int k = res.length - 1; k > 0; k--) {
                res[k-1] += res[k] / 10;
                res[k] %= 10;
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] != 0) {
                for (int j = i; j < res.length; j++) {
                    sb.append(res[j]);
                }
                break;
            }
        }
        return sb.length()==0?"0":sb.toString();
    }

}
