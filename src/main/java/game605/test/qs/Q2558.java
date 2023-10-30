package game605.test.qs;

import org.junit.Test;
import org.python.antlr.ast.Str;

import java.util.PriorityQueue;

/**
 *
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/28 3:43
 **/
public class Q2558 {

    // 一眼优先队列堆排序
    public long pickGifts(int[] gifts, int k) {
        // 构造大顶堆
        long ret = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);
        for (int gift : gifts) {
            queue.offer(gift);
            ret+=gift;
        }
        for (int i = 0; i < k; i++) {
            int temp =  queue.poll();
            int sqrtTemp = (int) Math.sqrt(temp);
            int diff = temp - sqrtTemp;
            ret -= diff;
            // 重新加入队列
            queue.offer(sqrtTemp);
        }
        return ret;
    }

    @Test
    public void t1(){
        String s1 = "REQ123123";
        System.out.println(s1.substring(0,3).toLowerCase());
    }

}
