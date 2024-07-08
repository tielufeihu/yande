package game605.test.qs;


import java.util.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q47
 * @description 全排列2
 * @since 2024/7/4 10:39
 */
public class Q47 {

    // 我的插空法，非递归回溯
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> ret = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        // 特判
        if(len==1){
            List<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            ret.add(list);
            return ret;
        }
        // 起始
        List<Integer> l1 = new ArrayList<>();
        l1.add(nums[0]);
        l1.add(nums[1]);
        List<Integer> l2 = new ArrayList<>();
        l2.add(nums[1]);
        l2.add(nums[0]);
        set.add(l1);
        set.add(l2);
        for (int i = 2; i < len; i++) {
            int curr = nums[i];
            Set<List<Integer>> temp = new HashSet<>();
            for (List<Integer> list : set) {
                // 在每个位置插入
                for (int j = 0; j <= list.size(); j++) {
                    ArrayList<Integer> tempList = new ArrayList<>(list);
                    tempList.add(j, curr);
                    temp.add(tempList);
                }
            }
            set = temp;
        }
        ret = new ArrayList<>(set);
        return ret;
    }


    // 回溯
    public List<List<Integer>> permuteUnique2(int[] nums){
        Set<List<Integer>> ret = new HashSet<>();
        boolean[] flags = new boolean[nums.length];
        bfs(0,new ArrayList<>(),flags,nums,ret);
        return new ArrayList<>(ret);
    }

    public void bfs(int count, List<Integer> output,boolean[] flags, int[] nums, Set<List<Integer>> ret){
        // 出口
        if(count == nums.length){
            ret.add(output);
            return;
        }
        // 找下一个元素
        for (int i = 0; i < nums.length; i++) {
            if(!flags[i]){
                // 做选择
                flags[i] = true;
                output.add(nums[i]);
                // 拷贝
                List<Integer> temp = new ArrayList<>(output);
                bfs(count+1,temp,flags,nums,ret);
                // 撤销选择
                output.remove(output.size()-1);
                flags[i] = false;
            }
        }
    }


    public static void main(String[] args) {
        Q47 q47 = new Q47();
        System.out.println(q47.permuteUnique2(new int[]{1,1,2}));
    }


}
