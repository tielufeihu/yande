package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NumMatrix
 * @description TODO
 * @since 2024/8/15 16:28
 */
class NumMatrix {

    int[][] preArr;

    final int num = 100;

    public NumMatrix(int[][] matrix) {
        preArr = new int[matrix.length+1][matrix[0].length+1];
        // 计算前缀和 preArr[i][j] 表示 0,0 -> i-1,j-1的和
        for (int i = 1; i < matrix.length+1; i++) {
            for (int j = 1; j < matrix[0].length+1; j++) {
                preArr[i][j] = preArr[i-1][j] + preArr[i][j-1] - preArr[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 计算公式： preArr[row2+1][col2+1]总的 - preArr[row1][col2+1] - preArr[row2+1][col1] + preArr[row1][col1]
        return preArr[row2+1][col2+1] - preArr[row1][col2+1] - preArr[row2+1][col1] + preArr[row1][col1];
    }


    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
    }

}
