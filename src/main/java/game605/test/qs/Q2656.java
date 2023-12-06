package game605.test.qs;

import java.util.Arrays;

/**
 * K 个元素的最大和
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/15 1:35
 **/
public class Q2656 {

    public int maximizeSum(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        return (int) (((max+(max+k))/2.0)*k);
    }

}
