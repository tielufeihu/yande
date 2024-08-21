package game605.test.qs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3154
 * @description TODO
 * @since 2024/8/20 14:00
 */
public class Q3154 {

    Map<Long, Integer> memory = new HashMap<>();

    // 递归搜索
    public int waysToReachStair(int k) {
        return dfs(1, 0, true, k);
    }

    // 暴搜超时， 加个记忆化
    public int dfs(int curr, int jump, boolean backward, int target) {
        if(curr > target+1){
            return 0;
        }
        long key = (long) curr << 32 | (long) jump << 1 | (backward ? 1 : 0);
        if(memory.containsKey(key)){
            return memory.get(key);
        }
        int count = curr == target ? 1 : 0;
        if(backward){
            count += dfs(curr-1, jump, false, target);
        }
        count += dfs(curr+(1 << jump), jump+1, true, target);
        // 记忆化结果
        memory.put(key, count);
        return count;
    }

    public static void main(String[] args) {
        Q3154 q3154 = new Q3154();
        System.out.println(q3154.waysToReachStair(1));
    }



}
