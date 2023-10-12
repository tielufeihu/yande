package game605.test.qs;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 三角形最小路径和
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/11 15:23
 **/
public class Q120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        // Q: 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
        // A: 可以， 明显滚动数组
        // 初始状态
        List<Integer> currList = new ArrayList<>();
        currList.add(triangle.get(0).get(0));
        // dp
        for (int i = 1; i < triangle.size(); i++) {
            int currRange = currList.size();
            List<Integer> currTemp = triangle.get(i);
            List<Integer> newList = new ArrayList<>();
            for (int j = 0; j < currTemp.size(); j++) {
                int currt = currTemp.get(j);
                int dpt = Math.min(currList.get(i), i+1<currRange?currList.get(i+1):Integer.MAX_VALUE) + currt;
                newList.add(dpt);
            }
            currList = newList;
        }
        return currList.stream().sorted().collect(Collectors.toList()).get(0);
    }

}
