package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 只出现过一次的数字
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/7 14:26
 **/
public class Q136 {

    // 这个费劲且慢
    public int singleNumber(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Map<Integer, Long> result = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        List<Integer> list1 = result.entrySet().stream().filter(e-> e.getValue()==1).map(e -> e.getKey()).collect(Collectors.toList());
        return list1.size()==1?list1.get(0):0;
    }

    // 我的排序做法（满足题意的 o(1) 的空间复杂度）
    public int singleNumber2(int[] nums) {
        int len = nums.length;
        if(len==0){
            return -1;
        }
        if(len==1){
            return nums[0];
        }
        // 排序后找到第一个不重复的返回即可
        Arrays.sort(nums);  // 排序
        if(nums[0] != nums[1])
            return nums[0];
        for (int i = 1; i < len-1; i++) {
            if(nums[i-1] != nums[i] && nums[i] != nums[i+1]){
                return nums[i];
            }
        }
        return nums[len-1] != nums[len-2]?nums[len-1]:-1;
    }


    //官方解的 位运算异或, 这题明显先射箭再画靶。
    // 时间 ： o(n)
    // 空间 ： o(1)
    public int singleNumber3(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    @Test
    public void t1(){
        singleNumber(new int[]{1,2,3,4,5,6,1,1,1,1});
    }

}
