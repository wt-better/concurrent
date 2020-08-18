package com.mtdp.countdownlatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/4/24
 */
public class CountDownLatch1 {

    private int count;

    public CountDownLatch1(int count) {
        this.count = count;
    }

    public void countdown() {
        synchronized (this) {
            count--;
            if (count == 0) {
                notifyAll();
            }
        }
    }

    public void await() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }

        synchronized (this) {
            //avoid spurious wake up
            while (count > 0) {
                wait();
            }
        }
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        final CountDownLatch1 countDownLatch = new CountDownLatch1(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(countDownLatch::countdown);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        executorService.shutdown();
    }
}
