package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q551
 * @description TODO
 * @since 2024/8/20 11:02
 */
public class Q551 {

    public boolean checkRecord(String s) {
        int a = 0;
        int l = 0;
        for (char c : s.toCharArray()) {
            if(c == 'A'){
                a++;
                l=0;
            }else if(c == 'L'){
                l++;
            }else {
                l=0;
            }

            if(a == 2 || l == 3){
                return false;
            }
        }
        return true;
    }

}
