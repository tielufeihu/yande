package game605.test.qs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1700
 * @description TODO
 * @since 2024/7/19 17:02
 */
public class Q1700 {

    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new ArrayDeque<>();
        Deque<Integer> stack = new ArrayDeque<>();
        // 入队
        for (int i = 0; i < students.length; i++) {
            queue.add(students[i]);
        }
        // 入栈
        for (int i = 0 ; i < sandwiches.length; i++) {
            stack.add(sandwiches[i]);
        }
        int flag = 0;
        while (!queue.isEmpty() && !stack.isEmpty()) {
            if(flag == queue.size()){
                break;
            }
            int stu = queue.poll();
            if(stu == stack.peek()){
                stack.pop();
                flag = 0;
            }else {
                queue.offer(stu);
            }
            flag++;
        }
        return queue.size();
    }

    public static void main(String[] args) {
        int[] students = {1,1,0,0};
        int[] sandwiches = {0,1,0,1};
        Q1700 q1700 = new Q1700();
        System.out.println(q1700.countStudents(students,sandwiches));
    }

}
