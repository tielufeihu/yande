package game605.test.qs;

import java.util.HashMap;

/**
 * 存在重复元素
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/23 10:55
 **/
public class Q217 {

    public boolean containsDuplicate(int[] nums) {
        // 哈希表计数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num,0)+1;
            if(count>=2)
                return true;
            map.put(num,count);
        }
        return false;
    }

}
