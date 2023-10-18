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
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == 1){
                    max = 1;
                }
            }
        }
        return 0;
    }

}
