package game605.test.qs;


import org.junit.Test;

import java.util.Arrays;

/**
 * 除自身以外数组的乘积
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/18 20:14
 **/
public class Q238 {

    public int[] productExceptSelf(int[] nums) {
        // 我的直观思路就是 用两个数组分别 从前往后，从后往前算乘机
        int len = nums.length;
        int[] rProduct = Arrays.copyOf(nums, len);
        int[] lProduct = Arrays.copyOf(nums, len);
        for (int i = 1; i < len; i++) {
            rProduct[i] = rProduct[i-1] * rProduct[i];
        }
        for (int i = len-2; i >= 0; i--) {
            lProduct[i] = lProduct[i+1] * lProduct[i];
        }

        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            ret[i] = (i-1>=0?rProduct[i-1]:1) * (i+1<len?lProduct[i+1]:1);
        }
        return ret;
    }

    @Test
    public void t1(){
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));
    }

}
