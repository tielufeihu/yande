package game605.test.qs;

import org.junit.Test;

/**
 * 找出数组的串联值
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/12 9:47
 **/
public class Q2562 {

    /**
     * 10.12 的每日一题， 简单题
     */
    public long findTheArrayConcVal(int[] nums) {

        int len = nums.length;
        int begin=0, end=len-1;
        long ret = 0;
        while (begin<end){
            ret +=  Integer.parseInt(String.valueOf(nums[begin]) + String.valueOf(nums[end]));
            begin++;
            end--;
        }
        if(len % 2 == 1){
            ret += nums[len/2];
        }
        return ret;
    }

    @Test
    public void t1(){
        System.out.println(findTheArrayConcVal(new int[]{7,52,2,4}));
    }

}
