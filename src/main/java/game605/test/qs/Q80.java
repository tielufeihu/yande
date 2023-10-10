package game605.test.qs;

/**
 * 删除有序数组中的重复项 II
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/10 14:57
 **/
public class Q80 {

    // 空间复杂度要求 O（1）  一开始没搞清楚原地删除的含义
    public int removeDuplicates(int[] nums) {
        int diff = 0;
        // 两两遍历
        for (int i = 0; i < nums.length-diff; i++) {
            int j = i + 1;
            if(nums[i] == nums[j]){
                // 删除 下标j 以后所有与j相同的
                int k = j + 1;
                while (nums[k] == nums[j]){
                    // 后移覆盖
                    for (int l = k; l < nums.length; l++) {
                        nums[l] = nums[l+1];
                    }
                    k++;
                    i++;
                    diff++;
                }
            }
        }
        return nums.length-diff;
    }

}
