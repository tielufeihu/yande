package game605.test.qs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/23 15:45
 **/
public class Q46 {

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> ret = new ArrayList<>();
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
        ret.add(l1);
        ret.add(l2);
        for (int i = 2; i < len; i++) {
            int curr = nums[i];
            List<List<Integer>> temp = new ArrayList<>();
            for (List<Integer> list : ret) {
                // 在每个位置插入
                for (int j = 0; j <= list.size(); j++) {
                    ArrayList<Integer> tempList = new ArrayList<>(list);
                    tempList.add(j, curr);
                    temp.add(tempList);
                }
            }
            ret = temp;
        }
        return ret;
    }

    @Test
    public void t1(){
        System.out.println(permute(new int[]{1,2,3}));
    }


}
