package game605.test.qs;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 统计无向图中无法互相到达点对数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/21 15:47
 **/
public class Q2316 {

    // 图是真不熟
    // 我是记得图可以用二维数组表示关系
    public long countPairs(int n, int[][] edges) {
        boolean[][] relaN = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                relaN[i][j] = false;
            }
        }
        for (int[] edge : edges) {
            relaN[edge[0]][edge[1]] = true;
            relaN[edge[1]][edge[0]] = true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(relaN[i][j] + " ");

            }
            System.out.println("");
        }
        return 0;
    }

    @Test
    public void t1(){
        //n = 3, edges = [[0,1],[0,2],[1,2]]
        // countPairs(3, new int[][]{{0,1},{0,2},{1,2}})
        System.out.println(countPairs(7, new int[][]{{0,2},{0,5},{2,4},{1,6},{5,4}}));
    }

}
