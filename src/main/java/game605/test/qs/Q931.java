package game605.test.qs;

import java.util.Arrays;

/**
 * 下降路径最小和
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/12 14:13
 **/
public class Q931 {
    // 线性dp太容易了
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        if(n == 1)
            return matrix[0][0];
        int[][] dp = new int[n][n];
        // 初始状态 第一行
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i];
        }
        // dp
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int prev1 = j-1>=0?dp[i-1][j-1]:Integer.MAX_VALUE;
                int prev = dp[i-1][j];
                int prev3 = j+1<n?dp[i-1][j+1]:Integer.MAX_VALUE;
                if(j-1>=0)
                    prev = Math.min(prev, dp[i-1][j-1]);
                if (j+1<n)
                    prev = Math.min(prev, dp[i-1][j+1]);
                dp[i][j] = prev + matrix[i][j];
            }
        }
        //int[] endLevel = dp[n-1];
        //Arrays.sort(endLevel);
        //return endLevel[0];
        return Arrays.stream(dp[n-1]).min().getAsInt();
    }


}
