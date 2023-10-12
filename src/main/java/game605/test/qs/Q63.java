package game605.test.qs;

import org.junit.Test;

/**
 * 不同路径2
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/11 14:57
 **/
public class Q63 {

    /**
     * 目测是一个很简单的dp
     * 初始状态   dp[0][n] = 1  and  dp[m][0] = 1
     * 状态转移方程： if 位置是个障碍物则 dp[m][n] = 0
     *             else  dp[m][n] = dp[m-1][n] + dp[m][n-1]
     */

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 初始状态
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0]==0? dp[i-1][0] :0;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = obstacleGrid[0][i]==0? dp[0][i-1] :0;
        }
        // dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = obstacleGrid[i][j]==0?dp[i-1][j] + dp[i][j-1]:0;
            }
        }
        return dp[m-1][n-1];
    }

    @Test
    public void t1(){
        System.out.println(uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
    }


}
