package com.mtdp.interrupt;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date Created At 2020/2/7
 */
public class Main {

    /**
     * 线程会被中断吗？
     * 不会，因为虽然给线程发出了中断信号，但程序中并没有响应中断信号的逻辑，所以程序不会有任何反应。
     */
    private static void test1() {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();
            }
        });
        thread.start();
        thread.interrupt();
    }

    /**
     * 加上了响应中断的逻辑，程序接收到中断信号打印出信息后返回退出。
     */
    private static void test2() {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断");
                    return;
                }
            }
        });
        thread.start();
        thread.interrupt();
    }

    /**
     * sleep() 方法被中断后会清除中断标记，所以循环会继续运行
     *
     * @throws InterruptedException
     */
    private static void test3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断，程序退出。");
                    return;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("线程休眠被中断，程序退出。");
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

    private static void test4() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断，程序退出。");
                    return;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("线程休眠被中断，程序退出。");
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
    public static void main(String[] args) throws InterruptedException {
        test4();
    }
}
