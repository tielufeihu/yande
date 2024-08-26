package game605.test.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Koyou
 * @version 1.0.0
 * @className JUC3
 * @description 使用LockSupport
 * @since 2024/6/21 15:42
 */
public class JUCT3 {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{

            System.out.println("t1 start");
            System.out.println("t1 pack before");
            LockSupport.park();
            System.out.println("t1 pack after");
        });

        Thread t2 = new Thread(()->{
            System.out.println("t2 start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t2 unpark before");
            LockSupport.unpark(t1);
            System.out.println("t2 unpark after");
        });

        t1.start();
        t2.start();

    }

}
