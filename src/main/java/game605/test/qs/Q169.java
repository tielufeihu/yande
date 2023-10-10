package game605.test.qs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/10 10:52
 **/
public class Q169 {

    // 哈希表
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int target = nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            int t = map.getOrDefault(nums[i], 0) + 1;
            if(t>target)
                return nums[i];
            else
                map.put(nums[i], t);
        }
        return 0;
    }

    // 排序( 这个确实没想到， 如果有众数数量大于 n/2 则中间位置的元素，一定是这个众数 )
    public int majorityElement2(int[] nums){
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

}
