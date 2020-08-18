package com.mtdp.wait_notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition -> a b c 循环打印
 *
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/4/13
 */
public class ConditionDemo {

    private static final Lock LOCK = new ReentrantLock();

    private int signal = 0;

    private final Condition aCondition = LOCK.newCondition();
    private final Condition bCondition = LOCK.newCondition();
    private final Condition cCondition = LOCK.newCondition();

    private void a() {
        LOCK.lock();
        try {
            while (signal != 0) {
                aCondition.await();
            }

            System.out.println("a");
            signal = 1;
            bCondition.signal();
        } catch (Exception ignored) {

        } finally {
            LOCK.unlock();
        }
    }

    private void b() {
        LOCK.lock();
        try {
            while (signal != 1) {
                bCondition.await();
            }

            System.out.println("b");
            signal = 2;
            cCondition.signal();
        } catch (Exception ignored) {

        } finally {
            LOCK.unlock();
        }
    }

    private void c() {
        LOCK.lock();
        try {
            while (signal != 2) {
                cCondition.await();
            }

            System.out.println("c");

            signal = 0;
            aCondition.signal();
        } catch (Exception ignored) {

        } finally {
            LOCK.unlock();
        }
    }

    public static void main(String[] args) {
        final ConditionDemo conditionDemo = new ConditionDemo();

        new Thread(() -> {
            for (; ; ) {
                conditionDemo.a();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                conditionDemo.b();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                conditionDemo.c();
            }
        }).start();
    }
}
