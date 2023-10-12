package game605.test.qs;

/**
 * 两数相除， 通过率仅22 ,比困难题还低
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/12 10:03
 **/
public class Q29 {

    // 这题直接模拟超时而且难以判断边界
    public int divide(int dividend, int divisor) {
        int ret=0;
        int sum=0;
        int k=1;
        if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0))
            k = -1;
        int tdividend = Math.abs(dividend);
        int tdivisor = Math.abs(divisor);
        while (tdividend>sum){
            sum+=tdivisor;
            ret++;
        }
        return (ret-1) * k;
    }

}
