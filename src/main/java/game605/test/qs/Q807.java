package game605.test.qs;

import java.util.Arrays;

/**
 * 保护城市天际线
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/12 17:19
 **/
public class Q807 {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] ms = new int[m];
        int[] ns = new int[n];
        for (int i = 0; i < m; i++) {
            ms[i] = Arrays.stream(grid[i]).max().getAsInt();
        }
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < m; j++) {
                max = Math.max(grid[j][0], max);
            }
            ns[i] = max;
        }
        System.out.println(Arrays.toString(ms));
        System.out.println(Arrays.toString(ns));
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = Math.min(ms[i], ns[j]) - grid[i][j];
                ret += Math.max(t, 0);
            }
        }
        return ret;
    }

}
