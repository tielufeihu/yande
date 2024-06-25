package game605.test.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Koyou
 * @version 1.0.0
 * @className JUCT6
 * @description ReentrantLock
 * ReentrantLock 默认是非公平锁，传入 true 表示公平锁，传入 false 表示非公平锁。
 * ReentrantLock 基于AQS实现
 * @since 2024/6/25 15:06
 */
public class JUCT6 {

    public static void main(String[] args) {
        ReentrantLock r = new ReentrantLock(true);

    }

}
