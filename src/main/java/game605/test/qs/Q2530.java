package game605.test.qs;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 执行 K 次操作后的最大分数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/18 10:56
 **/
public class Q2530 {

    public long maxKelements(int[] nums, int k) {
        // 使用优先队列降序。最大的出队，计算分数后再入队。 这个过程执行K次
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);
        long sum=0;
        for (int num : nums) {
            queue.add(num);
        }
        for (int i = 0; i < k; i++) {
            int num = queue.poll();
            sum += num;
            queue.add(computeNext(num));
        }
        return sum;
    }

    public int computeNext(int num){
        return (int) Math.ceil((double) num /3); // 官方的上取整 (num + 2) / 3;  //我的方案 (num/3)+(num%3>0?1:0);
    }

    @Test
    public void t1(){
        System.out.println(maxKelements(new int[]{1,10,3,3,3}, 3));
    }

}
