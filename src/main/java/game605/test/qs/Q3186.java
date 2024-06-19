package game605.test.qs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3186
 * @description TODO
 * @since 2024/6/19 11:04
 */
public class Q3186 {

    // hash计数+数组dp
    public long maximumTotalDamage(int[] power) {
        // 用hashmap 计数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : power) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        // 排序
        List<int[]> list = new ArrayList<>();
        for (int i : map.keySet()) {
            list.add(new int[]{i, map.get(i)*i});
        }
        list.sort((a, b) -> b[0] - a[0]);
        int[] dp = new int[list.size()];
        // 初始状态
        dp[0] = list.get(0)[1];
        dp[1] = list.get(1)[0] - list.get(0)[0] > 2?dp[0]+list.get(1)[1]:list.get(1)[1];
        for (int i = 2; i < list.size(); i++) {
            int[] t = list.get(i);
            // 是否能取前一项
            if(t[0] - list.get(i-1)[0] > 2){
                dp[i] = dp[i-1] + t[1];
            }else {
                int tn = t[1] + (t[0]-list.get(i-2)[0]>2?dp[i-2]:(i>=3?dp[i-3]:0));
                dp[i] = Math.max(dp[i-1], tn);
            }
        }
        return dp[list.size()-1];
    }

}
