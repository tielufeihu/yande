package game605.test.qs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q690
 * @description
 * 给定一个整数 id 表示一个员工的 ID，返回这个员工和他所有下属的重要度的 总和。
 * 我下属的下属应该也是我的下属
 * @since 2024/8/21 11:45
 */
public class Q690 {

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> data = employees.stream().collect(Collectors.toMap(e->e.id , e -> e));
        int sum = 0;
        // 广搜吧
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(id);
        while(!queue.isEmpty()){
            // 取出当前节点
            Queue<Integer> next = new ArrayDeque<>();
            while (!queue.isEmpty()){
                int cur = queue.poll();
                List<Integer> subList = data.get(cur).subordinates;
                next.addAll(subList);
                sum += data.get(cur).importance;
            }
            queue = next;
        }
        return sum;
    }

}








class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
