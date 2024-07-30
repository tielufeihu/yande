package game605.test.qs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q128
 * @description 最长连续序列
 * @since 2024/7/29 15:02
 */
public class Q128 {

    // 排序
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        int ret = 1;
        int temp = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i-1] == nums[i]){
                continue;
            }
            if(nums[i-1] + 1 == nums[i]){
                temp ++;
                ret = Math.max(ret, temp);
            }else {
                temp = 1;
            }
        }
        return ret;
    }


    // 哈希
    public int longestConsecutive2(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ret = 0;
        for (int num : nums) {
            if(!set.contains(num-1)){
                int temp = 1;
                while (set.contains(num+1)){
                    temp ++;
                    num ++;
                }
                ret = Math.max(ret, temp);
            }
        }
        return ret;
    }

    public static void main(String[] args) {

        Q128 q128 = new Q128();
        System.out.println(q128.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

}
