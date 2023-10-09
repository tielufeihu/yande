package game605.test.qs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 股票的最大跨度， 单调栈
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/8 15:52
 **/
public class Q901C {

    Deque<int[]> deque;
    int idx = -1;

    public Q901C(){
        deque = new LinkedList<>();
        // 单调栈一般都要先插入一个保底元素，保证栈内不空
        deque.add(new int[]{-1, Integer.MAX_VALUE});

    }

    public int next(int price){
        // 单调递减栈， 就是栈内元素从底到顶是递减的
        while (price >= deque.peek()[1]){
            deque.pop();
        }
        // 接下来显而易见的是，会直接找到比当前元素大的第一个元素。
        int ret = idx - deque.peek()[0] + 1;
        deque.push(new int[]{++idx, price});
        return ret;
    }

}
