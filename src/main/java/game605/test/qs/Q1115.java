package game605.test.qs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1115
 * @description 交替打印
 * @since 2024/7/17 15:24
 */
public class Q1115 {

    private int n;
    private AtomicInteger s = new AtomicInteger(0);
    private Object lock1 = new Object();
    private Object lock2 = new Object();


    public Q1115(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            if(s.get() != 0){
                // 阻塞
                synchronized (lock1){
                    lock1.wait();
                }
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            s.set(1);
            // 唤醒
            synchronized (lock2){
                lock2.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            if(s.get() != 1){
                // 阻塞
                synchronized (lock2){
                    lock2.wait();
                }
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            s.set(0);
            // 唤醒
            synchronized (lock1){
                lock1.notify();
            }
        }
    }

}

/**
 *  * 使用LockSupport实现
 */
class Q1115TWO {

    private int n;
    private volatile boolean s = true;
    private Map<String, Thread> map = new HashMap<>();


    public Q1115TWO(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        map.put("foo", Thread.currentThread());
        for (int i = 0; i < n; i++) {
            while (s){
                // 阻塞当前线程
                LockSupport.park();
            }
            printFoo.run();
            s = false;
            // 解锁foo线程
            LockSupport.unpark(map.get("bar"));
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        map.put("bar", Thread.currentThread());
        for (int i = 0; i < n; i++) {
            while (!s){
                // 阻塞当前线程
                LockSupport.park();
            }
            printBar.run();
            s = true;
            // 解锁foo线程
            LockSupport.unpark(map.get("foo"));
        }
    }

}
