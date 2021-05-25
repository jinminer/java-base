package com.jinm.concurrent.phase1.interrupt;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class InterruptSupportedBlockedThread {

    public static void main(String[] args) {

        Thread interruptedBlockedThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){

                try {
                    System.out.println(LocalDateTime.now());
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {

                    /**
                     * 中断复位：
                     *                    捕捉到抛出的异常后，唤醒当前 waiting 状态的线程，让当前线程自行判断线程中断条件，自行停止；
                     *                    抛出 InterruptedException 异常后，中断标识会自动清除，所以需要在 catch 代码块中重新设置 中断标识
                     * 异常：  线程外部的某个线程调用该线程的 .interrupt() 方法，在 该线程 run方法 内的 join、sleep、wait 等方法上会抛出异常
                     * 自行中断：通过判断 .isInterrupted() 方法的值判断代码逻辑是否继续执行
                     * 结论：
                     *          InterruptedException异常的抛出并不意味着线程必须终止，而是提醒当前线程有中断的操作发生，至于接下来怎么处理取决于线程本身：
                     *                  1. 直接捕获异常不做任何处理（线程继续运行）
                     *                  2.将异常往外抛出，由run 方法内部的其他逻辑处理
                     *                  3.调用Thread.currentThread().interrupt()方法停止当前线程，并打印异常信息
                     */
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }

                System.out.println("InterruptSupportedBlockedThread.run()");
            }
        });

        interruptedBlockedThread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
            Thread.sleep(0 ^ 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        interruptedBlockedThread.interrupt();

    }

}
