package game605.test.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Koyou
 * @version 1.0.0
 * @className JUCT4
 * @description 测试lock.condition
 * @since 2024/6/21 15:51
 */
public class JUCT4 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t1 = new Thread(()->{
            System.out.println("t1阻塞前");
            try {
                lock.lock();
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            System.out.println("t2唤醒后");
        });

        Thread t2 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t2启动，一秒后唤醒t1");
                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        t1.start();
        // 等待一段时间，确保t1先执行，t2后执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t2.start();

    }

}
