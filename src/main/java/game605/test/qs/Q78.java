package game605.test.qs;

import org.junit.Test;

import java.util.*;

/**
 * 子集
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/8 19:55
 **/
public class Q78 {

    // 层序，我最骄傲的信仰
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);  // 先排序
        int len = nums.length;
        Deque<NodeDetail> currLevel = new LinkedList<>();  // 存当前层
        List<List<Integer>> retList = new ArrayList<>();
        retList.add(new ArrayList<>());
        // 存第一层
        for (int i = 0; i < len; i++) {
            ArrayList<Integer> tlist = new ArrayList<>();
            tlist.add(nums[i]);
            currLevel.add(new NodeDetail(tlist, i+1));
        }

        while (!currLevel.isEmpty()){
            NodeDetail tNode = currLevel.poll();
            // 加入 tNode 的子节点
            int tRange = tNode.range;
            retList.add(tNode.vals);
            for (int i = tNode.range; i < len; i++) {
                List<Integer> tlist  = new ArrayList<>(tNode.vals);
                tlist.add(nums[i]);
                currLevel.add(new NodeDetail(tlist, ++tRange));
            }
        }
        return retList;
    }

    @Test
    public void t01(){
        List<List<Integer>> ret = subsets(new int[]{1,2,3});
        System.out.println(ret);
    }

}

class NodeDetail{
    List<Integer> vals;
    int range;  // 从range开始（包括range）

    NodeDetail(List<Integer> vals, int range){
        this.vals = vals;
        this.range = range;
    }
}
