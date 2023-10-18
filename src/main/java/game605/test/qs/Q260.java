package game605.test.qs;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 只出现了一次的数字
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/16 16:46
 **/
public class Q260 {

    public int[] singleNumber(int[] nums) {
        // 上周周赛题
        // 忘了使用hash表就是线性的时间复杂度
        Map<Integer,Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num,0)+1);
        }
        int[] ret = new int[2];
        int i=0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if(entry.getValue() == 1){
                ret[i++] = entry.getKey();
            }
            if(i >= 3)
                break;
        }
        return ret;
    }

}
