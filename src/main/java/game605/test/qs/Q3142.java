package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3142
 * @description
 * 如果它下面的格子存在，那么它需要等于它下面的格子，也就是 grid[i][j] == grid[i + 1][j] 。
 * 如果它右边的格子存在，那么它需要不等于它右边的格子，也就是 grid[i][j] != grid[i][j + 1] 。
 * @since 2024/8/29 15:26
 */
public class Q3142 {

    public boolean satisfiesConditions(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i+1 < m && grid[i][j] != grid[i+1][j]){
                    return false;
                }
                if(j+1 < n && grid[i][j] == grid[i][j+1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
