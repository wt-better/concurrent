package com.mtdp.wait_notify;

/**
 * 两个线程一个打印奇数，一个打印偶数，循环打印，直到等于100为止
 *
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/6/7
 */
public class WaitNotifyDemo3 {

    private static final int MAX_VALUE = 99;
    private int num = 1;

    private synchronized void printA() {
        while (num % 2 == 0) {
            try {
                wait();
            } catch (InterruptedException ignore) {

            }

        }

        System.out.println(Thread.currentThread().getName() + " " + num);
        num++;
        notifyAll();
    }

    private synchronized void printB() {
        while (num % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException ignore) {

            }

        }

        System.out.println(Thread.currentThread().getName() + " " + num);
        num++;
        notifyAll();
    }


    public static void main(String[] args) {
        WaitNotifyDemo3 waitNotifyDemo3 = new WaitNotifyDemo3();
        new Thread(() -> {
            while (waitNotifyDemo3.getNum() <= MAX_VALUE) {
                waitNotifyDemo3.printA();
            }
        }).start();

        new Thread(() -> {
            while (waitNotifyDemo3.getNum() <= MAX_VALUE) {
                waitNotifyDemo3.printB();
            }
        }).start();

        //System.in.read();
    }

    private int getNum() {
        return num;
    }
}
