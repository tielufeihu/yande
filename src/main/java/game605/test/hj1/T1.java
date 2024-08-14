package game605.test.hj1;

import java.util.Scanner;

/**
 * @author Koyou
 * @version 1.0.0
 * @className T1
 * @description TODO
 * @since 2024/8/13 21:18
 */
public class T1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 计数排序， 因为每个小区最多1000人
        int[] arr = new int[1001]; // arr[i] 代表i个人的小区有几个
        while (in.hasNext()) {
            int n = in.nextInt();
            arr[n+1]++; // n个人小小区数量++
        }
        int ret = 0;
        for (int i = 1; i < 1001; i++) {
            int count = arr[i] / i;
            if(arr[i] % i != 0) count++;
            ret += count * i;
        }
        System.out.println(ret);
    }

}
