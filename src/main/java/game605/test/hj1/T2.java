package game605.test.hj1;

import java.util.Scanner;

/**
 * @author Koyou
 * @version 1.0.0
 * @className T2
 * @description TODO
 * @since 2024/8/13 21:34
 */
public class T2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();  // 数字的个数
        int m = in.nextInt();  // 行数
        if(m == 1){
            for(int i=0;i<n;i++){
                System.out.print(i+1);
                System.out.println(" ");
            }
            return;
        }

        if(n<=m){
            for(int i=0;i<n;i++){
                System.out.println(i+1);
            }
            for(int i=n;i<m;i++){
                System.out.println("*");
            }
        }else{
            // 上取整
            int col = n/m;
            col += n%m==0?0:1;
            // 构造数组
            int[][] arr = new int[m][col];
            // 螺旋填数
            int num = 1;
            int i=0,j=0;
            while (num<=n) {
                while (j < col && arr[i][j] == 0 && num <= n)  {
                    arr[i][j] = num++;
                    j++;
                }
                j--;
                i++;
                while (i < m && arr[i][j] == 0 && num <= n) {
                    arr[i][j] = num++;
                    i++;
                }
                i--;
                j--;
                while (j >= 0 && arr[i][j] == 0 && num <= n) {
                    arr[i][j] = num++;
                    j--;
                }
                j++;
                i--;
                while (i >= 0 && arr[i][j] == 0 && num <= n) {
                    arr[i][j] = num++;
                    i--;
                }
                // 进入下一个内圈
                i++;
                j++;
            }
            // 输出
            for(int k=0;k<m;k++){
                for(int l=0;l<col;l++){
                    if(arr[k][l]==0){
                        System.out.print("* ");
                    }else{
                        System.out.print(arr[k][l]);
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
