package com.mtdp.synchonized;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/5/10
 */
public class SynchronizedTest {

    private int result;

    SynchronizedTest(int result) {
        this.result = result;
    }

    int getResult() {
        return result;
    }

    synchronized void setResult(int result) {
        this.result = result;
    }

    /**
     * 并发 synchronized 的一道陷阱
     * 对set方法进行synchronized，对get方法未进行同步，导致之前的线程感知不到后续对共享变量的修改
     */
    public static void main(String[] args) throws InterruptedException {
        final SynchronizedTest synchronizedTest = new SynchronizedTest(1);
        new Thread(() -> {
            while (synchronizedTest.getResult() == 1) {

            }
        }).start();

        Thread.sleep(1000);

        synchronizedTest.setResult(2);
    }
}
