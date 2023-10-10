package game605.test.qs;

/**
 * 颠倒二进制位
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/10 14:24
 **/
public class Q190 {

    // 官方解
    public int reverseBits(int n) {
        int rev = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return rev;
    }

}
