package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1227
 * @description 飞机座位分配概率
 * @since 2024/6/7 15:31
 */
public class Q1227 {

    public double nthPersonGetsNthSeat(int n) {
        if(n == 1)
            return 1.0;

        double ret = 1;
        for (int i = 1; i <= n; i++) {
            // 第一位做在自己位置上的概率是
            ret *= ((double) n /(n-i));
        }
        return ret;
    }

}
