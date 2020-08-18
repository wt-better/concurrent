package com.mtdp.countdownlatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/4/24
 */
public class CountDownLatch2 {

    private int count;

    private final Lock LOCK = new ReentrantLock();

    private final Condition awaitCondition = LOCK.newCondition();

    public CountDownLatch2(int count) {
        this.count = count;
    }

    public void await() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }

        LOCK.lock();
        try {
            while (count > 0) {
                awaitCondition.await();
            }
        } finally {
            LOCK.unlock();
        }
    }


    public void countdown() {
        LOCK.lock();

        try {
            count--;
            if (count == 0) {
                awaitCondition.signal();
            }
        } finally {
            LOCK.unlock();
        }
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        final CountDownLatch2 countDownLatch = new CountDownLatch2(5);

        for (int i = 0; i < 5; i++) {
            final int c = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(c * 1000);
                } catch (InterruptedException e) {

                }
                countDownLatch.countdown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {

        }
        executorService.shutdown();
    }

}

