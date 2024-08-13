package game605.test.acm;

import java.util.Scanner;

/**
 * @author Koyou
 * @version 1.0.0
 * @className HWT3
 * @description
 * 进制转换
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 * @since 2024/8/13 11:57
 */
public class HWT3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        s = s.replace("0x","");
        int i = Integer.parseInt(s, 16);
        System.out.println(i);
    }

}
