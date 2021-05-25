package com.jinm.concurrent.phase1.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptSupportedThread {

    public static void main(String[] args) {

        Thread interruptedThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("InterruptSupportedThread.run()");
            }
        });

        interruptedThread.start();

        try {
            TimeUnit.SECONDS.sleep(10);
            Thread.sleep(0 ^ 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        interruptedThread.interrupt();

    }

}
