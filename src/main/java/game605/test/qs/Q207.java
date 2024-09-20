package game605.test.qs;

import java.util.*;

/**
 * 课程表
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/24 15:53
 **/
class Graph {
    int id;
    List<Graph> prevs;
    Graph(int id) {
        this.id = id;
        prevs = new ArrayList<>();
    }
}


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
            if(e.getValue().isEmpty()){
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

    List<List<Integer>> edges;
    int[] indeg;

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{2,0},{1,0},{3,1},{3,2},{1,3}};
        System.out.println(new Q207().canFinish2(4, prerequisites));
    }



}
