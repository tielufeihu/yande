package game605.test.qs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q90
 * @description TODO
 * @since 2024/7/10 10:17
 */
public class Q90 {

    // set+回溯
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Set<List<Integer>> ans = new HashSet<>();
        ans.add(new ArrayList<>());
        List<Integer> temp;
        for (int i = 0; i < nums.length; i++) {
            temp = new ArrayList<>();
            backTrace(ans, temp, nums, i);

        }
        return new ArrayList<>(ans);
    }

    public void backTrace(Set<List<Integer>> ans, List<Integer> temp, int[] nums, int start) {
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            List<Integer> ret = temp.stream().sorted().collect(Collectors.toList());
            ans.add(ret);
            backTrace(ans, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }


    // 排序剪枝
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        // 排序
        Arrays.sort(nums);
        // 结果集
        List<List<Integer>> ans = new ArrayList<>();
        // 回溯
        backTrace2(ans, new ArrayList<>(), nums, 0);
        return ans;
    }

    public void backTrace2(List<List<Integer>> ans, List<Integer> temp, int[] nums, int start) {
        // 出口
        if (start == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[start]);
        backTrace2(ans, temp, nums, start + 1);
        temp.remove(temp.size() - 1);
        // 跳过重复
        while (start < nums.length - 1 && nums[start] == nums[start + 1]) {
            start++;
        }
        backTrace2(ans, temp, nums, start + 1);
    }

}
