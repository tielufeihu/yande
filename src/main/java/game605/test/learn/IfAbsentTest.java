package game605.test.learn;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author Koyou
 * @version 1.0.0
 * @className IfAbsentTest
 * @description TODO
 * @since 2024/8/20 16:46
 */
public class IfAbsentTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        Vector v = new Vector<>();
        int a = map.computeIfAbsent("a", k -> 2);
        int b = map.computeIfAbsent("b", k -> 3);

        System.out.println(a);
        System.out.println(b);
        System.out.println(map);
    }

}
