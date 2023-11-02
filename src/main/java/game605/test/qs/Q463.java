package game605.test.qs;

import org.junit.Test;

/**
 * 岛屿的周长
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/2 17:19
 **/
public class Q463 {

    // 模拟
    public int islandPerimeter(int[][] grid) {
        // 模拟， 计算增加的边数
        int ret = 0;
        int h = grid.length;
        int w = grid[0].length;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(grid[i][j] == 1){
                    ret += 4;
                    // 如果是连续的周长--
                    if(j-1>=0 && grid[i][j-1]==1) ret-=2;
                    // 如果上面也是岛屿周长--
                    if(i-1>=0 && grid[i-1][j]==1) ret-=2;
                }
            }
        }
        return ret;
    }
    @Test
    public void t1(){
        /**
         * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
         * 输出：16
         */
        System.out.println(islandPerimeter(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}}));
    }

}
