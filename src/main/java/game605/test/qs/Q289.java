package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;

/**
 * 生命游戏
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/12 14:58
 **/
public class Q289 {

    // 遍历模拟
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        System.out.println(m);
        System.out.println(n);
        int[][] newBoard = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 获取 (i,j) 周围八个位置
                // 从左上角顺时针排序
                int liveCount = i-1>=0 && j-1>=0?board[i-1][j-1]:0;
                liveCount += j-1>=0?board[i][j-1]:0;
                liveCount += i+1<m && j-1>=0?board[i+1][j-1]:0;
                liveCount += i+1<m?board[i+1][j]:0;
                liveCount += i+1<m && j+1<n?board[i+1][j+1]:0;
                liveCount += j+1<n?board[i][j+1]:0;
                liveCount += i-1>=0 && j+1<n?board[i-1][j+1]:0;
                liveCount += i-1>=0?board[i-1][j]:0;
                System.out.println(liveCount);
                if(liveCount < 2){
                    newBoard[i][j] = 0;
                }else if(liveCount < 3){
                    newBoard[i][j] = board[i][j];
                }else if(liveCount < 4){
                    newBoard[i][j] = 1;
                }else {
                    newBoard[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }

    @Test
    public void t1(){
        int[][] ret = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        gameOfLife(ret);
        for (int[] ints : ret) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
