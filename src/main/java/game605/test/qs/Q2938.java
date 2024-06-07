package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2938
 * @description 移动黑白球
 * @since 2024/6/7 15:59
 */
public class Q2938 {

    public static void main(String[] args) {
        Q2938 q2938 = new Q2938();
        System.out.println(q2938.minimumSteps("01001111"));
    }


    public long minimumSteps(String s) {
        char[] chars = s.toCharArray();
        int p0 = 0;  // 代表左边第一个1的位置
        int p1 = s.length()-1;  // 代表右边第一个0的位置
        // 寻找p0
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '1'){
                p0 = i;
                break;
            }
        }
        // 寻找p1
        for (int i = chars.length-1; i >= 0; i--) {
            if(chars[i] == '0'){
                p1 = i;
                break;
            }
        }
        // 是否相遇
        if(p0-p1 == 1){
            return 0;
        }
        int k = chars.length-1-(chars.length-1-p1);
        int ret = 0;
        // 遍历直到相遇
        while (p0 < p1){
            if(chars[p0] == '1'){
                ret += k;
            }
            k--;
            p0++;
        }
        return ret;
    }

}
