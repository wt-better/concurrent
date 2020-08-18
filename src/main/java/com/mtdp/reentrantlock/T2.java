package com.mtdp.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/4/22
 */
public class T2 {

    private static Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            LOCK.lock();
            try {
                try {
                    System.out.println("start ... ");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                }
            } finally {
                LOCK.unlock();
            }

        }).start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        Thread thread = new Thread(() -> doAction());
        thread.start();

        thread.interrupt();

    }

    private static void doAction() {
        boolean locked = false;
        try {
            locked =  LOCK.tryLock(5, TimeUnit.SECONDS);
            System.out.println(locked);
        } catch (InterruptedException e) {

            System.out.println(e);

        } finally {
            //在使用非锁持有线程释放锁时，会抛出java.lang.IllegalMonitorStateException异常
            //针对于tryLock可以使用lock flag去判断是否需要释放锁
            if(locked){
                LOCK.unlock();
            }
        }
    }


}
