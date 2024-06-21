package game605.test.juc;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className jucT1
 * @description 写个生产者消费者的反例
 * @since 2024/6/20 15:48
 */
public class JUCT1 {

    public static Queue<String> queue = new ArrayDeque<>();

    // 生产者
    static class Producer implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                JUCT1.queue.add("data" + i);
                // 生产者生产数据后，线程sleep 100ms
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("produce data" + i);
            }
        }
    }

    // 消费者
    static class Consumer implements Runnable{
        @Override
        public void run() {
            while (true){
                String data = JUCT1.queue.poll();
                if (data == null){
                    break;
                }
                System.out.println("consumer data" + data);
            }
        }
    }


    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new Producer()).start();
        }

    }
}
