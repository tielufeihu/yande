package game605.test.qs;

import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3148
 * @description TODO
 * @since 2024/8/15 11:12
 */
public class Q3148 {

    // 思路 f[i+1][j+1] 记录 (0,0) 到 (i,j) 的最小值
    public int maxScore(List<List<Integer>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();
        int ret = Integer.MIN_VALUE;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid.get(i - 1).get(j - 1);
                ret = Math.max(ret, grid.get(i).get(j)-f[i][j]);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Q3148 q3148 = new Q3148();
    }

}
