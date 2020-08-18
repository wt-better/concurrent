package com.mtdp.thread_state;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date Created At 2019/10/11
 */
public class ThreadWaitTest {

    private void test1() {
        synchronized (this) {
            try {
                wait(10000);
            } catch (InterruptedException ignore) {

            }
        }
    }


    /**
     * "main" #1 prio=5 os_prio=31 tid=0x00007f9b76002000 nid=0x1c03 in Object.wait() [0x0000700008ac3000]
     * java.lang.Thread.State: TIMED_WAITING (on object monitor)
     * at java.lang.Object.wait(Native Method)
     * - waiting on <0x000000076abacb70> (a com.mtdp.thread_state.ThreadWaitTest)
     * at com.mtdp.thread_state.ThreadWaitTest.test1(ThreadWaitTest.java:12)
     * - locked <0x000000076abacb70> (a com.mtdp.thread_state.ThreadWaitTest)
     * at com.mtdp.thread_state.ThreadWaitTest.main(ThreadWaitTest.java:25)
     * <p>
     * 状态：TIMED_WAITING
     * 特征：on object monitor
     */
    public static void main(String[] args) {
        ThreadWaitTest threadWaitTest = new ThreadWaitTest();
        threadWaitTest.test1();
    }
}
