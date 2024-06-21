package game605.test.qs;

import org.python.antlr.op.In;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q525
 * @description TODO
 * @since 2024/6/20 11:00
 */
public class Q525 {

    public static void main(String[] args) {
        Q525 q = new Q525();
        int[] nums = {0,1,0};
        System.out.println(q.findMaxLength(nums));
    }

    // 连续数组
    public int findMaxLength(int[] nums) {
        // 哈希表存放前置和key 出现的最小下标val
        HashMap<Integer,Integer> map = new HashMap<>();
        int max = 0;
        int count = 0;
        map.put(count, -1);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0){
                count--;
            }else {
                count++;
            }

            if(map.containsKey(count)){
                max = Math.max(max,i-map.get(count));
            }else {
                map.put(count,i);
            }
        }
        return max;
    }


}
