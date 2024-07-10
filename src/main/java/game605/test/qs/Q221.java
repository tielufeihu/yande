package game605.test.qs;



/**
 * 最大正方形
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/13 16:37
 **/
public class Q221 {

    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        // dp 填充0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1'){
                    dp[i][j] = 1;
                    max = 1;
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return max*max;
    }

}
