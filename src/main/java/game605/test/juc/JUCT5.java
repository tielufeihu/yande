package game605.test.juc;

import java.util.concurrent.Semaphore;

/**
 * @author Koyou
 * @version 1.0.0
 * @className JUC5
 * @description Semaphore
 * @since 2024/6/24 17:53
 */
public class JUCT5 {



    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(()-> JUCT5.task(semaphore, finalI)).start();
        }
        System.out.println("11111111111111111111111");
    }


    public static void task(Semaphore semaphore,int num){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " 执行任务,编号[" + num + "]");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }


}
