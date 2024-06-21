package game605.test.juc;


/**
 * @author Koyou
 * @version 1.0.0
 * @className JUCT2
 * @description wait()和notify()：
 * @since 2024/6/20 17:42
 */
public class JUCT2 {

    public static void main(String[] args) {
        Object lock = new Object();

        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(100);
                System.out.println("A被lock锁住前");
                synchronized (lock){
                    lock.wait();
                }
                System.out.println("A被唤醒");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"A");

        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 唤醒A
            synchronized (lock){
                lock.notify();
            }
        }, "B");

        t2.start();
        t1.start();
    }

}
