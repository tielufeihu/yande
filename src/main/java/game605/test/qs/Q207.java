package game605.test.qs;

import org.junit.Test;

import java.util.*;

/**
 * 课程表
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/24 15:53
 **/
public class Q207 {

    // dfs超时
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 0-numCourses-1课程号
        // 第一步需要先处理prerequisites，处理成hash表
        Map<Integer, List<Integer>> coursesPrevMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            coursesPrevMap.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            coursesPrevMap.get(prerequisite[0]).add(prerequisite[1]);
        }
        Queue<List<Integer>> queue = new LinkedList<>();
        // 首先找到不需要前置课程的课
        for (Map.Entry<Integer, List<Integer>> e : coursesPrevMap.entrySet()) {
            if(e.getValue().size() == 0){
                // 这门课程不需要前置课
                List<Integer> t = new ArrayList<>();
                t.add(e.getKey());
                queue.add(t);
            }
        }
        // bfs
        while (!queue.isEmpty()){
            List<Integer> currList = queue.poll();
            if(currList.size() == numCourses){
                // 学完了
                return true;
            }
            // 当前节点的可选后继有哪些
            for (Map.Entry<Integer, List<Integer>> e : coursesPrevMap.entrySet()) {
                if(currList.contains(e.getKey())){
                    // 如果学过了直接条件
                    continue;
                }
                // 没学过，判断能不能学
                if(currList.containsAll(e.getValue())){
                    // 可以学
                    List<Integer> t = new ArrayList<>(currList);
                    t.add(e.getKey());
                    queue.add(t);
                }
            }
        }
        return false;
    }

    @Test
    public void t1(){
        // numCourses =
        //20
        //prerequisites =
        //[[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]
        System.out.println(canFinish(18, new int[][]{{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}}));
    }

}
