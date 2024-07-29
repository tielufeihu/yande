package game605.test.qs;

import java.util.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q682
 * @description 棒球比赛
 * @since 2024/7/29 9:31
 */
public class Q682 {

    public int calPoints(String[] operations) {
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];
            if ("+".equals(op)) {
                // 前两次
                scores.add(scores.get(scores.size() - 1) + scores.get(scores.size() - 2));
            }else if ("D".equals(op)) {
                // 前一次得分的两倍
                scores.add(scores.get(scores.size() - 1) * 2);
            }else if ("C".equals(op)) {
                // 删除前一次
                scores.remove(scores.size() - 1);
            }else {
                scores.add(Integer.parseInt(op));
            }
        }
        return scores.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        String[] operations = {"5","2","C","D","+"};
        System.out.println(new Q682().calPoints(operations));
    }

}
