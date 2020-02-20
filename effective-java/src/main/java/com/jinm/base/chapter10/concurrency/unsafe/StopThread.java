package com.jinm.base.chapter10.concurrency.unsafe;

import java.util.concurrent.TimeUnit;

class StopThread {
    private static Boolean stopRequested = false;
    public static void main(String[] args)
            throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
                System.out.println(i);
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}