package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q79
 * @description 回溯模板题吧
 * @since 2024/7/8 16:41
 */
public class Q79 {

    public boolean exist(char[][] board, String word) {
        // 特判
        if(board.length == 1){
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < board[0].length; i++) {
                str.append(board[0][i]);
            }
            return str.toString().contains(word) || str.reverse().toString().contains(word);
        }

        if(board[0].length == 1){
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                str.append(board[i][0]);
            }
            return str.toString().contains(word) || str.reverse().toString().contains(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    // 特判
                    if(word.length() == 1) {
                        return true;
                    }
                    // 可以作为起点
                    boolean[][] flag = new boolean[board.length][board[0].length];
                    flag[i][j] = true;
                    boolean ret = backtrack(board, word, 1, String.valueOf(board[i][j]), flag, new int[]{i, j});
                    flag[i][j] = false;
                    if(ret) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean backtrack(char[][] board, String word, int count, String currWord, boolean[][] flag, int[] index)  {
        // 上下左右
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : directions) {
            int x = index[0]+direction[0];
            int y = index[1]+direction[1];
            // 判断x y是否越界
            if(x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                if(!flag[x][y] && board[x][y] == word.charAt(count)) {
                    if(count == word.length() - 1) {
                        return true;
                    }
                    flag[x][y] = true;
                    boolean ret = backtrack(board, word, count + 1, currWord + board[x][y], flag, new int[]{x,y});
                    flag[x][y] = false;
                    // 回溯
                    if (ret){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Q79 q79 = new Q79();
        System.out.println(q79.exist(new char[][]{{'C','A','A'}
                                                 ,{'A','A','A'}
                                                 ,{'B','C','D'}}, "AAB"));
    }

}
