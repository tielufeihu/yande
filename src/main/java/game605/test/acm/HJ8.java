package game605.test.acm;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

/**
 * @author Koyou
 * @version 1.0.0
 * @className HJ8
 * @description
 * 数据表记录包含表索引index和数值value（int范围的正整数），
 * 请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
 * @since 2024/8/13 18:28
 */
public class HJ8 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Integer, Integer> map = new HashMap<>();
        while (in.hasNextLine()){
            String[] str = in.nextLine().split(" ");
            if (str.length == 2){
                int key = Integer.parseInt(str[0]);
                int value = Integer.parseInt(str[1]);
                if (map.containsKey(key)){
                    map.put(key, map.get(key) + value);
                }else {
                    map.put(key, value);
                }
            }
        }
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

    }

}
