package game605.test.acm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Koyou
 * @version 1.0.0
 * @className HJ6
 * @description TODO
 * @since 2024/8/13 17:30
 */
public class HJ6 {

    long[] ssTable;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long num = Long.parseLong(scan.next());
        long temp = num;
        // 从2开始试
        for (long i = 2; i <= temp; i++) {
            if(temp % i == 0){
                temp = temp / i;
                System.out.print(i + " ");
                // 从2重新开始试试
                i = 1;
            }else if (i*i > temp){
                // i等于本身结束循环
                System.out.print(temp);
                break;
            }
        }
    }

    // 筛法素数优化一下
    public void getPrime(long n) {
        List<Integer> ss = new ArrayList<>();
        for (int i = 2; i <= n; i++) {

        }
    }


}
