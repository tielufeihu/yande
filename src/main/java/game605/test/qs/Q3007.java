package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3007
 * @description TODO
 * @since 2024/8/21 9:32
 */
public class Q3007 {


    // 不知道有啥公式 模拟（暴力->二分）
    public long findMaximumNumber(long k, int x) {
        long sum = 0;
        for (int num = 1; sum <= k; num++) {
            sum += value(num, x);
            if(sum>k){
                return num-1;
            }
        }
        return -1;
    }

    // 二分的
    public long findMaximumNumber2(long k, int x) {
        long left = 0;
        long right = (k + 1) << x;
        while (left + 1 < right) {
            long mid = (left + right) >>> 1;
            if (value(mid,x) <= k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }



    // 计算价值(暴力->数位DP->公式)
    public long value(long num, int x) {
        int f = 1;
        long sum = 0;
        while (num > 0) {
            if(f % x == 0) {
                sum += (num & 1);
            }
            num = num >> 1;
            f++;
        }
        return sum;
    }

    public static void main(String[] args) {
        Q3007 q3007 = new Q3007();
        System.out.println(q3007.findMaximumNumber(7, 2));
    }

}
