package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;

/**
 * 只出现一次的数2
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/15 22:30
 **/
public class Q137 {

    public int singleNumber(int[] nums) {
        // 只记录一个数，如果又出现了则替换
        int len = nums.length;
        Arrays.sort(nums);
        int curr = nums[0];
        boolean flag = true;

        for (int i = 1; i < len; i++) {
            if(curr == nums[i]){
                flag = false;
            }
            if(!flag){
                curr = nums[i];
            }
        }

        return curr;
    }

    @Test
    public void t1(){
        int ret = singleNumber(new int[]{0,1,0,1,0,1,99});
        System.out.println(ret);
    }

}
