package game605.test.qs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2766
 * @description TODO
 * @since 2024/7/24 13:45
 */
public class Q2766 {

    // 暴力模拟超时
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        int n = moveFrom.length;
        for (int i = 0; i < n; i++) {
            for (int i1 = 0; i1 < nums.length; i1++) {
                if(nums[i1] == moveFrom[i]){
                    nums[i1] = moveTo[i];
                }
            }
        }
        Arrays.sort(nums);
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        List<Integer> ret = new ArrayList<>(set);
        return ret.stream().sorted().collect(Collectors.toList());
    }


    // 哈希表记录
    public List<Integer> relocateMarbles2(int[] nums, int[] moveFrom, int[] moveTo) {
        List<Integer> ans = new ArrayList<Integer>();
        Map<Integer, Boolean> mp = new HashMap<Integer, Boolean>();

        for (int i = 0; i < nums.length; i++) {
            mp.put(nums[i], true);
        }

        for (int i = 0; i < moveFrom.length; i++) {
            mp.remove(moveFrom[i]);
            mp.put(moveTo[i], true);
        }

        for (Map.Entry<Integer, Boolean> entry : mp.entrySet()) {
            ans.add(entry.getKey());
        }
        Collections.sort(ans);
        return ans;
    }

}
