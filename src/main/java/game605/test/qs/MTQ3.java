package game605.test.qs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 应该可以用搜索算法解决
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/21 10:39
 **/
public class MTQ3 {

    public int bfs(char[][] map){
        Deque<int[]> deque = new LinkedList<>();
        char[] seqList = new char[]{'A','B','C','D','E'};
        // 加入根节点(载入起点)
        int n = map.length;
        int m = map[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == 'A'){
                    deque.add(new int[]{i,j,0});
                }
            }
        }
        int ret = 0;
        while (!deque.isEmpty() && ret<10000){
            int[] curr = deque.poll();
            int x = curr[0];
            int y = curr[1];
            int deep = curr[2];
            ret = Math.max(deep, ret);
            char currChar = map[x][y];
            char target = nextChar(currChar,seqList);
            // 寻找他周围有没有下一个点位
            List<int[]> nexts = searchNext(map,target,deep+1,x,y);
            deque.addAll(nexts);
        }
        return ret==10000?-1:ret;
    }

    public List<int[]> searchNext(char[][] map,char target,int deep, int i, int j){
        int n = map.length;
        int m = map[0].length;
        List<int[]> ret = new ArrayList<>();
        if(i-1>=0 && map[i-1][j]==target){
            ret.add(new int[]{i-1,j,deep});
        }
        if(i+1<n && map[i+1][j]==target){
            ret.add(new int[]{i+1,j,deep});
        }
        if(j-1>=0 && map[i][j-1]==target){
            ret.add(new int[]{i,j-1,deep});
        }
        if(j+1<m && map[i][j+1]==target){
            ret.add(new int[]{i,j+1,deep});
        }
        return ret;
    }

    public char nextChar(char curr, char[] seq){
        int idx = -1;
        for (int i = 0; i < seq.length; i++) {
            if(curr == seq[i]){
                idx = i;
                break;
            }
        }
        return seq[(idx+1)%seq.length];
    }

    @Test
    public void t1(){
        /**
         * {'A','B','C','E'},
         * {'A','B','D','E'},
         * {'C','A','B','A'},
         * {'E','A','C','D'}
         */
        System.out.println(bfs(new char[][]{{'A','B','C','E'},
                {'A','B','D','E'},
                {'C','A','B','A'},
                {'E','A','C','D'}}));
    }

}
