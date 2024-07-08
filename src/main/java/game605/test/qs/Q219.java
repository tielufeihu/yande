package game605.test.qs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q219
 * @description TODO
 * @since 2024/7/8 13:54
 */
public class Q219 {

    // hash表 + 记录索引
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // int[0] = inx, int[1] = val
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 判断是否存在
            if (map.containsKey(nums[i])) {
                List<int[]> nodes = map.get(nums[i]);
                for (int[] node : nodes) {
                    if(i-node[0]<=k){
                        return true;
                    }
                }
                nodes.add(new int[]{i, nums[i]});
            }else {
                List<int[]> nodes = new ArrayList<>();
                nodes.add(new int[]{i, nums[i]});
                map.put(nums[i], nodes);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Q219 q219 = new Q219();
        System.out.println(q219.containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
    }

}
