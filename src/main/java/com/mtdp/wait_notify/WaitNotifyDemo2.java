package com.mtdp.wait_notify;

/**
 * WaitNotify -> a b c 循环打印
 *
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/4/14
 */
public class WaitNotifyDemo2 {

    private int signal = 0;

    private synchronized void a() {
        while (signal != 0) {
            try {
                wait();
            } catch (InterruptedException ignored) {

            }
        }

        System.out.println("a");
        signal = 1;
        notifyAll();
    }

    private synchronized void b() {
        while (signal != 1) {
            try {
                wait();
            } catch (InterruptedException ignored) {

            }
        }

        System.out.println("b");
        signal = 2;
        notifyAll();
    }

    /**
     * 方法必须使用Synchronized修饰
     * 使用wait或者notify方法必须使用synchronized
     */
    private synchronized void c() {
        while (signal != 2) {
            try {
                wait();
            } catch (InterruptedException ignored) {

            }
        }

        System.out.println("c");
        signal = 0;
        notifyAll();
    }

    public static void main(String[] args) {
        final WaitNotifyDemo2 waitNotifyDemo2 = new WaitNotifyDemo2();

        new Thread(() -> {
            for (; ; ) {
                waitNotifyDemo2.a();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                waitNotifyDemo2.b();
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                waitNotifyDemo2.c();
            }
        }).start();
    }
}
