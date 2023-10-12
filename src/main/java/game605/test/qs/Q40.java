package game605.test.qs;

import org.junit.Test;

import javax.xml.soap.Node;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 组合总数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/12 10:16
 **/
public class Q40 {

    // 目测是个搜索题 只会 bfs , 但是不能故步自封
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(Arrays.stream(candidates).sum()<target)
            return new ArrayList<>();
        Arrays.sort(candidates); // 先排序
        Deque<NodeQ40> deque = new LinkedList<>();
        List<List<Integer>> ret = new ArrayList<>();
        int len = candidates.length;
        int range=1;
        // 加入初始节点
        for (int candidate : candidates) {
            List<Integer> list = new ArrayList<>();
            list.add(candidate);
            deque.add(new NodeQ40(candidate, range++,candidate, list));
        }
        while (!deque.isEmpty()){
            NodeQ40 node = deque.poll();
            List<Integer> prevList = node.currList;
            int prevVal = node.lastVal;
            if(node.currSum == target){
                ret.add(prevList);
            }
            if(node.currSum < target){
                // 将他的子节点加入到队列
                int tbegin = node.begin;
                for (int i = tbegin; i < len; i++) {
                    List<Integer> tList = new ArrayList<>(prevList);
                    tList.add(candidates[i]);
                    deque.add(new NodeQ40(node.currSum+candidates[i],i+1,candidates[i],tList));
                    if(prevVal == candidates[i])
                    {
                        break;
                    }
                }
            }
        }
        // 这里出现重复元素的原因就是， 数据里面有重复的元素。 这里必须对重复元素有所判断
        return ret.stream().distinct().collect(Collectors.toList());
    }

    // 研究一下 dfs的解
    public List<List<Integer>> combinationSum2DFS(int[] candidates, int target){
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(candidates);
        for (int i = 0; i < candidates.length; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(candidates[i]);
            dfs(ret,list,candidates[i],candidates,i+1,target);
        }
        return ret.stream().distinct().collect(Collectors.toList());
    }

    public void dfs(List<List<Integer>> ret,List<Integer> currList,int currSum, int[] candidates, int range, int target){
        // 根
        if(currSum == target){
            ret.add(currList.stream().sorted().collect(Collectors.toList()));
            return;
        }
        if(currSum > target){
            return;
        }
        // 子节点
        for (int i = range; i < candidates.length; i++) {
            List<Integer> nextList = new ArrayList<>(currList);
            nextList.add(candidates[i]);
            currSum += candidates[i];
            dfs(ret,nextList,currSum,candidates,range+1,target);
            if(i+2<candidates.length && candidates[i+2] == candidates[i+1])
                break;
            // 这里需要回溯
            currSum -= candidates[i];
            currList.remove(candidates[i]);
        }
    }

    @Test
    public void t1(){
        combinationSum2DFS(new int[]{3,1,3,5,1,1}, 8).forEach(System.out::println);
    }

}

class NodeQ40{
    int currSum;
    int begin;

    int lastVal;

    List<Integer> currList;

    NodeQ40(int currSum, int begin, int lastVal, List<Integer> currList){
        this.begin = begin;
        this.currSum = currSum;
        this.lastVal = lastVal;
        this.currList = currList;
    }

}
