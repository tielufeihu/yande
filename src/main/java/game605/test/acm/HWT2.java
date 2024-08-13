package game605.test.acm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Koyou
 * @version 1.0.0
 * @className HWT2
 * @description 明明的随机数
 * 明明生成了NN个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，
 * 把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 * 数据范围： 1≤n≤1000 1≤n≤1000  ，输入的数字大小满足 1≤val≤500 1≤val≤500
 * @since 2024/8/13 11:43
 */
public class HWT2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < count; i++) {
            set.add(in.nextInt());
        }
        set.stream().sorted().forEach(System.out::println);
    }

}
