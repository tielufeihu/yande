package game605.test.qs;

import org.junit.Test;

import java.util.*;

/**
 * 避免洪水泛滥
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/13 11:22
 **/
public class Q1488 {

    // 这种题应该就是搜索
    public int[] avoidFlood(int[] rains) {
        int len = rains.length;
        if(len == 0)
            return new int[0];
        // bfs
        Deque<NodeQ1488> setDeque = new LinkedList<>();
        // 把第一天加入到辅助队列
        Set<Integer> firstDayFullSet = new HashSet<>();
        List<Integer> firstDayPath = new ArrayList<>();
        if (rains[0]==0){
            firstDayPath.add(1);
        }else {
            firstDayFullSet.add(rains[0]);
            firstDayPath.add(-1);
        }

        setDeque.add(new NodeQ1488(firstDayFullSet,firstDayPath));
        // bfs逐层遍历
        for (int i = 1; i < len; i++) {
            int currRain = rains[i];
            List<NodeQ1488> prevNodes = new ArrayList<>(setDeque);
            setDeque.clear(); // 清空队列
            for (NodeQ1488 prevNode : prevNodes) {
                Set<Integer> prevLake = prevNode.currFullLake;
                List<Integer> prevPath = prevNode.currPath;
                if(currRain == 0){
                    if(!prevLake.isEmpty()){
                        for (Integer integer : prevLake) {
                            Set<Integer> currLake = new HashSet<>(prevLake);
                            currLake.remove(integer);
                            List<Integer> currPath = new ArrayList<>(prevPath);
                            currPath.add(integer);
                            setDeque.add(new NodeQ1488(currLake, currPath));
                        }
                    }else {
                        // 昨天没有满的湖泊直接下一个
                        Set<Integer> currLake = new HashSet<>(prevLake);
                        List<Integer> currPath = new ArrayList<>(prevPath);
                        currPath.add(1);
                        setDeque.add(new NodeQ1488(currLake, currPath));
                    }
                }else {
                    // 如今今天下雨的湖泊有水则剪枝
                    if (prevLake.contains(currRain)) {
                        continue;
                    }
                    // 今天下雨了 加入到路径，不能抽干
                    Set<Integer> currLake = new HashSet<>(prevLake);
                    currLake.add(currRain);
                    List<Integer> currPath = new ArrayList<>(prevPath);
                    currPath.add(-1);
                    setDeque.add(new NodeQ1488(currLake, currPath));
                }
            }
        }
        if(setDeque.isEmpty()){
            return new int[0];
        }else {
            NodeQ1488 node = setDeque.peek();
            int[] ret = new int[node.currPath.size()];
            int i = 0;
            for (Integer integer : node.currPath) {
                ret[i++] = integer;
            }
            return ret;
        }
    }


    @Test
    public void t1(){
        int[] ret  = avoidFlood(new int[]{1,2,0,0,2,1});
        System.out.println(Arrays.toString(ret));
    }

}

class NodeQ1488{
    Set<Integer> currFullLake;
    List<Integer> currPath;

    NodeQ1488(Set<Integer> lake, List<Integer> path){
        this.currFullLake = lake;
        this.currPath = path;
    }
}
