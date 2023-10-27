package game605.test.qs;

import org.junit.Test;

/**
 * 岛屿数量
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/24 14:34
 **/
public class Q200 {

    public int numIslands(char[][] grid) {
        // 原地搜索
        int m = grid.length;
        int n = grid[0].length;
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '-'){
                    // 已经被记录过的陆地直接过
                    continue;
                }
                if(grid[i][j] == '1'){
                    // 把整块岛屿都找出来，然后置为'-'
                    landExtends(grid, i,j);
                    ret++;
                }
            }
        }
        return ret;
    }

    // 把这个位置上的岛屿整个置'-'
    public void landExtends(char[][] grid, int i, int j){
        // 无非就是四个方向
        char up = i-1>=0 ? grid[i-1][j] : '#';
        char down = i+1< grid.length ? grid[i+1][j] : '#';
        char left = j-1>=0?grid[i][j-1] : '#';
        char right = j+1<grid[0].length ? grid[i][j+1] : '#';
        if(up == '1'){
            grid[i-1][j] = '-';
            landExtends(grid,i-1,j);
        }
        if(down == '1'){
            grid[i+1][j] = '-';
            landExtends(grid,i+1,j);
        }
        if(left == '1'){
            grid[i][j-1] = '-';
            landExtends(grid,i,j-1);
        }
        if(right == '1'){
            grid[i][j+1] = '-';
            landExtends(grid,i,j+1);
        }
    }


    @Test
    public void t1(){
        System.out.println(numIslands(new char[][]{}));
    }

}
