package com.mtdp.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试ReentrantLock的可重入性
 *
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/4/22
 */
public class T1 {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        doSynchronizedAction(T1::action1);
    }

    private static void action1() {
        doSynchronizedAction(T1::action2);
    }

    private static void action2() {
        doSynchronizedAction(T1::action3);
    }

    private static void action3() {
        System.out.println("Hello World!");
    }

    private static void doSynchronizedAction(Runnable runnable) {
        lock.lock();
        try {
            runnable.run();
        } finally {
            lock.unlock();
        }
    }
}
