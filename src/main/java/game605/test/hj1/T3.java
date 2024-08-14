package game605.test.hj1;

import java.util.Scanner;

/**
 * @author Koyou
 * @version 1.0.0
 * @className T3
 * @description TODO
 * @since 2024/8/13 22:05
 */
public class T3 {

    public static int[][] ret;
    public static boolean f = false;

    // 搜索
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 读取明文
        int mLen = in.nextInt();
        int[] ming = new int[mLen];
        for (int i = 0; i < mLen; i++) {
            ming[i] = in.nextInt();
        }
        // 读取密码本
        int pSize = in.nextInt();
        int[][] pwd = new int[pSize][pSize];
        for (int i = 0; i < pSize; i++) {
            for (int j = 0; j < pSize; j++) {
                pwd[i][j] = in.nextInt();
            }
        }
        // ret
        ret = new int[mLen][2];
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[0].length; j++) {
                ret[i][j] = -1;
            }
        }
        // bfs
        int[][] pwdt = pwd.clone();
        // 寻找起点
        for (int i = 0; i < pSize; i++) {
            for (int j = 0; j < pSize; j++) {
                if(pwdt [i][j] == ming[0]){
                    // 可以作为起点
                    find(pwdt, ming, i, j, 0);
                    if (f) break;
                }
            }
            if (f) break;;
        }

        // 判断是否找到
        boolean flag = true;
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[0].length; j++) {
                if(ret[i][j] == -1){
                    flag = false;
                }
            }
        }
        // 输出结果
        if(flag){
            for (int[] ints : ret) {
                System.out.print(ints[0] + " " + ints[1] + " ");
            }
        }else {
            System.out.println("error");
        }

    }


    public static void find(int[][] pwd, int[] ming, int x, int y, int curr) {
        // 出口
        if(curr == ming.length){
            f = true;
            return;
        }
        // 判断边界
        if(x < 0 || x >= pwd.length || y < 0 || y >= pwd.length){
            return;
        }
        if(pwd[x][y] == ming[curr]){
            int t = pwd[x][y];
            // 做选择
            pwd[x][y] = -1;
            ret[curr][0] = x;
            ret[curr][1] = y;
            find(pwd, ming, x-1, y, curr+1);
            if(f) {
                return;
            }
            find(pwd, ming, x, y-1, curr+1);
            if(f) {
                return;
            }
            find(pwd, ming, x, y+1, curr+1);
            if(f) {
                return;
            }
            find(pwd, ming, x+1, y, curr+1);
            if(f) {
                return;
            }
            // 撤销选择
            pwd[x][y] = t;
            ret[curr][0] = -1;
            ret[curr][1] = -1;
        }
    }


}
