package game605.test.qs;

/**
 *  x 的平方根
 *  给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 */
public class Q69 {

    public long mySqrt(int x) {
        for (long i = 1; i <= x; i++) {
            if(i*i==x)
                return i;
            if(i*i>x)
                return i-1;
        }
        return 0;
    }

}
