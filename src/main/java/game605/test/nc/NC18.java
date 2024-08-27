package game605.test.nc;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC18
 * @description TODO
 * @since 2024/8/26 17:31
 */
public class NC18 {

    public int[][] rotateMatrix (int[][] mat, int n) {
        int[][] reverse = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                reverse[j][n - i - 1] = mat[i][j];
            }
        }
        return reverse;
    }

    public static void main(String[] args) {
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        NC18 nc18 = new NC18();
        nc18.rotateMatrix(mat, 3);
    }

}
