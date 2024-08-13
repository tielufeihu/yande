package game605.test.acm;

import java.util.Scanner;

/**
 * @author Koyou
 * @version 1.0.0
 * @className HJ2
 * @description TODO
 * @since 2024/8/13 16:21
 */
public class HJ2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        // 按照8为单位拆分
        int len = s.length() / 8;
        for (int i = 0; i < len; i++) {
            System.out.println(s.substring(i*8,(i+1)*8));
        }
        // 最后一个串补零
        if(s.length()%8 != 0) {
            System.out.println((s.substring(len*8)+"0000000").substring(0,8));
        }
    }


}
