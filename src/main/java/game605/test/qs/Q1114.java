package game605.test.qs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1114
 * @description TODO
 * @since 2024/7/12 17:47
 */
public class Q1114 {

    AtomicInteger count = new AtomicInteger(1);
    final Object lock2 = new Object();
    final Object lock3 = new Object();

    public Q1114() {

    }

    public void first(Runnable printFirst) {
        if(count.get() == 1){
            printFirst.run();
            count.incrementAndGet();
        }
        // 唤醒2
        synchronized(lock2){
            lock2.notify();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        if(count.get() != 2){
            // 阻塞
            synchronized(lock2){
                lock2.wait();
            }
        }
        printSecond.run();
        count.incrementAndGet();
        // 唤醒三
        synchronized(lock3){
            lock3.notify();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        if(count.get() != 3){
            // 阻塞
            synchronized(lock3){
                lock3.wait();
            }
        }
        printThird.run();
    }


    public static void main(String[] args) throws InterruptedException {
        Q1114 q1114 = new Q1114();
        Thread t1 = new Thread(() -> System.out.println("first"));
        Thread t2 = new Thread(() -> System.out.println("Second"));
        Thread t3 = new Thread(() -> System.out.println("Third"));
        q1114.first(t1);
        q1114.second(t2);
        q1114.third(t3);
    }

}
