package game605.test.acm;

import java.util.Scanner;

/**
 * @author Koyou
 * @version 1.0.0
 * @className HWT1
 * @description
 * 1.
 * 汽水瓶
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 * 数据范围：输入的正整数满足 1≤n≤100 1≤n≤100
 *
 * 注意：本题存在多组输入。输入的 0 表示输入结束，并不用输出结果。
 * @since 2024/8/13 11:25
 */
public class HWT1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) {
            int n = in.nextInt();
            if(n == 0) break;
            int ret = 0;
            while (n>=2){
                if(n == 2){
                    ret += 1;
                    break;
                }
                int huan = n/3;
                int sheng = n%3;
                ret += huan;
                n = huan + sheng;
            }
            System.out.println(ret);
        }
    }
}
