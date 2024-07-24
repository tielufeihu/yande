package game605.test.qs;

/**
 * 最小路径和
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/10 14:30
 **/
public class Q64 {

    // 贪心肯定是解决不了的，这个题只能dp
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 应该是个基础的二维dp
        int[][] dp = new int[m][n];
        // 在（m,n）上记录到达每一个点的最小路径和
        // 状态转移方程 f(m,n) = min( f(m-1,n) + f(m,n-1) )

        // 可以初始化 f(0,n) 和 f(0,m)
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
