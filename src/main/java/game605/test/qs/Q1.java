package game605.test.qs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/17 15:38
 **/
public class Q1 {

    // 这个题使用hashmap，可以达到O(n)的复杂度
    public int[] twoSum(int[] nums, int target) {
        // 生成hash表 <值,下标>
        Map<Integer,Integer> numMap = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int diff = target - nums[i];
            if(numMap.containsKey(diff))
                return new int[]{i,numMap.get(diff)};
            numMap.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }

}
