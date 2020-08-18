package com.mtdp.thread_state;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date Created At 2019/10/11
 */
public class ThreadSleepTest {

    /**
     * "main" #1 prio=5 os_prio=31 tid=0x00007fc35d808000 nid=0x1c03 waiting on condition [0x0000700000c8e000]
     * java.lang.Thread.State: TIMED_WAITING (sleeping)
     * at java.lang.Thread.sleep(Native Method)
     * at com.mtdp.thread_state.ThreadSleepTest.main(ThreadSleepTest.java:11)
     * <p>
     * 状态：TIMED_WAITING
     * 特征：waiting on condition
     */
    public static void main(String[] args) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
        System.out.println("Hello world!");
    }
}
