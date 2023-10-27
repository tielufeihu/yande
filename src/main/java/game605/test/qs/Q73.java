package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;

/**
 * 矩阵置零
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/27 15:47
 **/
public class Q73 {

    public final int sign = Integer.MAX_VALUE;

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) setOne(matrix,i,j);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == sign) matrix[i][j]=0;
            }
        }
    }

    public void setOne(int[][] m, int x,int y){
        // 下
        for (int i = x+1; i < m.length; i++) {
            if(m[i][y]==0) break;
            m[i][y] = sign;
        }
        // 右
        for (int i = y+1; i < m[0].length; i++) {
            if(m[x][i]==0) break;
            m[x][i] = sign;
        }
        // 上
        for (int i = x-1; i >= 0; i--) {
            if(m[i][y]==0) break;
            m[i][y] = sign;
        }
        // 左
        for (int i = y-1; i >= 0; i--) {
            if(m[x][i]==0) break;
            m[x][i] = sign;
        }
    }

    @Test
    public void t1(){
        //matrix = [[1,1,1],[1,0,1],[1,1,1]]
        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

}
