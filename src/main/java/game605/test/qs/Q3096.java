package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3096
 * @description TODO
 * @since 2024/7/19 9:44
 */
public class Q3096 {

    public int minimumLevels(int[] possible) {

        int sum = 0;
        for (int i = 0; i < possible.length; i++) {
            sum += possible[i]==0?-1:1;
        }
        int curr = 0;
        // 要给人留几关
        for (int i = 0; i < possible.length - 1; i++){
            // 过一关
            curr += possible[i]==0?-1:1;
            sum -= possible[i]==0?-1:1;
            if(curr > sum){
                return i+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q3096 q3096 = new Q3096();
        System.out.println(q3096.minimumLevels(new int[]{1,1}));
    }
}
