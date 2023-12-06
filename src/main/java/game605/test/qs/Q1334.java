package game605.test.qs;

import org.junit.Test;

import java.util.*;

/**
 * 阈值距离内邻居最少的城市
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/14 21:37
 **/
public class Q1334 {

    // 我的存图+dfs
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] map = new int[n][n];
        for (int[] ints : map) {
            // 初始化数组 -1 表示不可达
            Arrays.fill(ints, -1);
        }
        // 构建图
        for (int[] edge : edges) {
            //edges[i] = [fromi, toi, weighti]
            map[edge[0]][edge[1]] = edge[2];
            map[edge[1]][edge[0]] = edge[2];
        }
        int ret = -1;
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 阈值内乱走， 然后再看看经历了多少节点
            int remain = distanceThreshold;
            Set<Integer> passingCitySet = new HashSet<>();
            passingCitySet.add(i); // 本身经过了
            dfs(map, remain, passingCitySet, i);
            if(passingCitySet.size()<=minCount){
                minCount = passingCitySet.size();
                ret = i;
            }
        }
        return ret;
    }

    private void dfs(int[][] map, int remain, Set<Integer> passingCitySet, int currCity) {
        // 寻找下一个节点
        for (int i = 0; i < map.length; i++) {
            if(map[i][currCity]!=-1 && !passingCitySet.contains(i) && map[i][currCity]<=remain){
                passingCitySet.add(i);
                dfs(map,remain-map[i][currCity],passingCitySet,i);
            }
            if(map[currCity][i]!=-1 && !passingCitySet.contains(i) && map[currCity][i]<=remain){
                passingCitySet.add(i);
                dfs(map,remain-map[currCity][i],passingCitySet,i);
            }
        }
    }

    @Test
    public void ttt(){
        System.out.println(findTheCity(5,new int[][]{{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}},2));
    }

}
