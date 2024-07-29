package game605.test.qs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q167
 * @description TODO
 * @since 2024/7/26 14:39
 */
public class Q167 {

    // 哈希表
    public int[] twoSum(int[] numbers, int target) {
        // 记录 数值K 的下标 V
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int diff = target - numbers[i];
            if(map.containsKey(diff)){
                return new int[]{map.get(diff)+1, i+1};
            }
            map.put(numbers[i],i);
        }
        return null;
    }

    // 双指针
    public int[] twoSum2(int[] numbers, int target) {
        int left=0,right=numbers.length-1;
        while (left<right){
            int sum = numbers[left]+numbers[right];
            if(sum==target){
                return new int[]{left+1,right+1};
            }else if(sum<target){
                left++;
            }else {
                right--;
            }
        }
        return null;
    }

}
