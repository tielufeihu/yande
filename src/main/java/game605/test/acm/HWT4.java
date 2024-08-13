package game605.test.acm;

import java.util.Scanner;

/**
 * @author Koyou
 * @version 1.0.0
 * @className HWT4
 * @description 计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
 * @since 2024/8/13 16:11
 */
public class HWT4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] ss = str.split(" ");
        System.out.println(ss[ss.length-1].length());
    }

}
