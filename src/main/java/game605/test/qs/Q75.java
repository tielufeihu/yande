package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;

/**
 * 颜色分类
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/26 20:59
 **/
public class Q75 {

    // 要求原地算法  这个简单的是两趟遍历
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }
        for (int i = 0; i < nums.length; i++) {
            if(count0>0){
                nums[i] = 0;
                count0--;
            }else if(count1>0){
                nums[i] = 1;
                count1--;
            }else {
                nums[i] = 2;
            }
        }
    }

    // 一趟遍历，双指针
    public void sortColors1(int[] nums){
        int begin = 0;
        int end = nums.length-1;
        for (int i = 0; i <= end; i++) {
            if(nums[i] == 0){
                // 放在前面
                int t = nums[i];
                nums[i] = nums[begin];
                nums[begin] = t;
                begin++;
            }else if(nums[i] == 2){
                // 放在后面
                int t = nums[i];
                nums[i] = nums[end];
                nums[end] = t;
                end--;
            }
        }
    }

    @Test
    public void t1(){
        //nums = [2,0,2,1,1,0]
        int[] nums = new int[]{2,0,2,1,1,0};
        sortColors1(nums);
        System.out.println(Arrays.toString(nums));
    }
}
