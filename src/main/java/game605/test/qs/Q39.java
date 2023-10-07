package game605.test.qs;

import org.junit.Test;
import org.python.antlr.op.In;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 组合总和
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/6 17:35
 **/
public class Q39 {

    // dfs
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // TODO
        return null;
    }

    // bfs 击败 5%
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        Queue<NodeInfo> queue = new LinkedList<>();
        List<List<Integer>> retList = new ArrayList<>();
        // 将第一层结点加入队列
        final int[] idx = {0};
        Arrays.stream(candidates).boxed().forEach(e->{
            // 条件
            if(e>target)
                return;
            List<Integer> root = new ArrayList<>();
            root.add(e);
            queue.add(new NodeInfo(root, idx[0]));
            idx[0]++;
        });

        while (!queue.isEmpty()){
            NodeInfo node = queue.poll(); // 出队
            // 检查这个是否符合要求
            if(sumList(node.currentList) == target){
                retList.add(node.currentList);
                continue;  // 如果符合条件直接退出
            }
            if(sumList(node.currentList)>target)
                continue;
            // 如果不符合target 以此结点为基础加入下一层的结点
            queue.addAll(getChildrenNodes(candidates, node, target));
        }

        return retList;
    }

    private int sumList(List<Integer> list){
        final int[] sum = {0};
        list.forEach(e->{
            sum[0] += e;
        });
        return sum[0];
    }

    private List<NodeInfo> getChildrenNodes(int[] candidates, NodeInfo currentNode, int target){

        List<NodeInfo> retList = new ArrayList<>();
        int idx = currentNode.start;
        int idxd = 0;
        int diff = target-sumList(currentNode.currentList);
        for (int i = idx; i < candidates.length; i++) {
            if(candidates[i]>diff){
                // 如果这个大于target那么以后都大于target，剪枝
                break;
            }
            List<Integer> tlist = new ArrayList<>(currentNode.currentList);
            tlist.add(candidates[i]);
            retList.add(new NodeInfo(tlist, currentNode.start+idxd));
            idxd++;
        }
        return retList;
    }

    @Test
    public void t01(){
        List<List<Integer>> retList = combinationSum(new int[]{5,4,6,17,2,10,15,18,24,40,32,30,19,7}, 26);
        System.out.println(retList.size());
        retList.forEach(System.out::println);
        Set<List<Integer>> retSet = new HashSet<>();
        retList.forEach(e->{
            List<Integer> t = e.stream().sorted().collect(Collectors.toList());
            //if (retSet.contains(t)){
            //    System.out.println(t);
            //}
            retSet.add(t);
        });
        retList = new ArrayList<>(retSet);
        //System.out.println(retList.size());
    }

}



// 一个节点包含可选子节点列表
class NodeInfo{
    List<Integer> currentList;
    int start;  // 记录起始位置
    // 第一个子节点不多偏移，第二个多偏移1 ......

    public NodeInfo(List<Integer> list, int start){
        this.currentList = list;
        this.start = start;
    }
}
