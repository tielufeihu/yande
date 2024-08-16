package game605.test.nc;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC1
 * @description 大数加法
 * @since 2024/8/16 10:46
 */
public class NC1 {

    public String solve (String s, String t) {
        // write code here
        int p1=0,p2=0;
        s = new StringBuilder(s).reverse().toString();
        t = new StringBuilder(t).reverse().toString();
        int max = Math.max(s.length(),t.length());
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (p1<s.length()||p2<t.length()||carry!=0){
            int temp = carry;
            if (p1<s.length()){
                temp += s.charAt(p1)-'0';
                p1++;
            }
            if (p2<t.length()){
                temp += t.charAt(p2)-'0';
                p2++;
            }
            carry = temp/10;
            sb.append(temp%10);
        }
        return sb.reverse().toString();
    }

}
