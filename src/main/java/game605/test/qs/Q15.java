package game605.test.qs;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 三数之和
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/17 16:06
 **/
public class Q15 {

    // 一会再优化把
    public List<List<Integer>> threeSum(int[] nums) {
        // 生成哈希表
        // 这个题是返回值，不关心下标，因此我们可以不存下标
        // Map<数, 这个数出现的个数>
        Map<Integer,Integer> numCountMap = new HashMap<>();
        Set<List<Integer>> ret = new HashSet<>();
        for (int num : nums) {
            numCountMap.put(num, numCountMap.getOrDefault(num,0)+1);
        }
        // 感觉两重循环是不可避免的
        List<Integer> keyList = new ArrayList<>(numCountMap.keySet()).stream().sorted().collect(Collectors.toList());
        // 这里明显可以排序加速
        int len = keyList.size();
        for (int i = 0; i < len; i++) {
            int num1 = keyList.get(i);
            if(num1>=1)
                break;
            numCountMap.put(num1, numCountMap.get(num1)-1);  // 数量减1
            for (int j = i; j < len; j++) {
                int num2 = keyList.get(j);
                if(num1+num2>=1)
                    break;
                if(numCountMap.get(num2) >= 1){
                    numCountMap.put(num2, numCountMap.get(num2)-1);  // 数量减1
                    int diff = -num1-num2;
                    if(numCountMap.containsKey(diff) && numCountMap.get(diff)>=1){
                        // 加入到结果集
                        List<Integer> list = new ArrayList<>();
                        list.add(num1);
                        list.add(num2);
                        list.add(diff);
                        ret.add(list.stream().sorted().collect(Collectors.toList()));
                    }
                    numCountMap.put(num2, numCountMap.get(num2)+1);  // 数量+1
                }
            }
            numCountMap.put(num1, numCountMap.get(num1)+1);  // 数量加1
        }
        return new ArrayList<>(ret);
    }


    @Test
    public void t1(){
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }

}
