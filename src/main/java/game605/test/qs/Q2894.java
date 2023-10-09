package game605.test.qs;

import org.junit.Test;

/**
 * 分类求和并作差
 * 周赛原题
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/9 10:45
 **/
public class Q2894 {

    public int differenceOfSums(int n, int m) {
        // num1：范围 [1, n] 内所有 无法被 m 整除 的整数之和。
        // num2：范围 [1, n] 内所有 能够被 m 整除 的整数之和。
        int num1 = 0, num2 = 0;
        for (int i = 1; i <= n; i++) {
            if(i%m == 0){
                num2+=i;
            }else
                num1+=i;
        }

        return num1-num2;
    }

    @Test
    public void t1(){
        System.out.println(differenceOfSums(10,3));
    }

}
