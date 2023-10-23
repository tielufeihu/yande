package game605.test.qs;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * 阶乘尾数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/23 15:08
 **/
public class MS1605 {

    // 使用大数，直接超时
    public int trailingZeroes(int n) {
        BigDecimal multiply = BigDecimal.valueOf(1);
        for (int i = 2; i <= n; i++) {
            multiply = multiply.multiply(BigDecimal.valueOf(i));
        }
        int ret = 0;
        String s = String.valueOf(multiply);
        for (int i = s.length()-1; i >= 0; i--) {
            if(s.charAt(i) == '0'){
                ret++;
            }else {
                break;
            }
        }
        return ret;
    }

    // 只要末尾是出现0，则会一直带着这个0
    public int trailingZeroes2(int n) {
        // TODO
        return 0;
    }

    @Test
    public void t1(){
        trailingZeroes(5);
    }

}
