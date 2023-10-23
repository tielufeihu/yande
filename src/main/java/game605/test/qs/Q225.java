package game605.test.qs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/19 20:29
 **/
public class Q225 {

    Deque<Integer> queue1;
    Queue<Integer> queue2;

    public Q225() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.offerLast(x);
    }

    public int pop() {
        return queue1.pollFirst();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }

}
