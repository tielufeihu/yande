package game605.test.acm;

import java.util.Scanner;

/**
 * @author Koyou
 * @version 1.0.0
 * @className HJ7
 * @description
 * @since 2024/8/13 18:22
 */
public class HJ7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        float n = in.nextFloat();
        // 四舍五入
        int result = Math.round(n);
        System.out.println(result);
    }
}
