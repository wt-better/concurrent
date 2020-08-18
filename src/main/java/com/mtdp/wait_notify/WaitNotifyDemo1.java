package com.mtdp.wait_notify;

/**
 * Wait Notify -> 锁
 *
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/4/14
 */
public class WaitNotifyDemo1 {

    /**
     * OK
     */
    private void test1() {
        synchronized (this) {
            try {
                wait(3000);
            } catch (InterruptedException ignore) {

            }
        }
    }

    /**
     * java.lang.IllegalMonitorStateException
     */
    private void test2() {
        synchronized (WaitNotifyDemo1.class) {
            try {
                wait(3000);
            } catch (InterruptedException ignore) {

            }
        }
    }


    private void test3() {
        synchronized (WaitNotifyDemo1.class) {
            try {
                WaitNotifyDemo1.class.wait(3000);
            } catch (InterruptedException ignore) {

            }
        }
    }


    public static void main(String[] args) {
        WaitNotifyDemo1 waitNotifyDemo1 = new WaitNotifyDemo1();
        waitNotifyDemo1.test3();
    }
}
