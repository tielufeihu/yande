package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q598
 * @description TODO
 * @since 2024/7/18 10:10
 */
public class Q598 {

    public int maxCount(int m, int n, int[][] ops) {
        // 判断最大数的面积
        int minM = m;
        int minN = n;
        for (int[] op : ops) {
            minM = Math.min(minM, op[0]);
            minN = Math.min(minN, op[1]);
        }
        return minM * minN;
    }

}
