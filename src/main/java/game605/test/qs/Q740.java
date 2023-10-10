package game605.test.qs;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 删除并获得点数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/9 13:59
 **/
public class Q740 {

    // 一个直观的思路是构建一颗 treeMap， 记录个个节点的个数
    public int deleteAndEarn(int[] nums) {
        // 边界
        if (nums.length == 0)
            return 0;
        // 生成 treeMap 树
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0)+1);
        }
        // 特判
        if (countMap.size() == 1)
            return countMap.firstKey() * countMap.firstEntry().getValue();
        // dp数组
        int[] dp = new int[countMap.size()];
        Iterator<Map.Entry<Integer, Integer>> iterator = countMap.entrySet().iterator();
        Map.Entry<Integer, Integer> it1 = iterator.next();
        dp[0] = it1.getKey() * it1.getValue();
        Map.Entry<Integer, Integer> it2 = iterator.next();
        if(it2.getKey() - it1.getKey() >= 2)
            dp[1] = dp[0] + it2.getKey()*it2.getValue();
        else
            dp[1] = Math.max(dp[0], it2.getKey()*it2.getValue());

        for (int i = 2; i < countMap.size(); i++) {
            it1 = it2;
            it2 = iterator.next();
            if(it2.getKey() - it1.getKey() >=  2)
                dp[i] = dp[i-1] + it2.getKey()*it2.getValue();
            else
                dp[i] = Math.max(dp[i-1], dp[i-2] + it2.getKey()*it2.getValue());
        }
        return Math.max(dp[dp.length-1], dp[dp.length-2]);
    }

    // 可以不使用treeMap， 直接使用一个长度10的数组，下标n的值 代表n的个数
    // TODO ......

    @Test
    public void t1(){
        System.out.println(deleteAndEarn(new int[]{3,1}));
    }

}
