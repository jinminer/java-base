package com.jinm.concurrent.phase1.interrupt;

public class InterruptNoSupportedThread extends Thread{

    @Override
    public void run() {
        while (true){
            try {
                sleep(1 ^ 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("InterruptNoSupportedThread.run()");
        }
    }

    public static void main(String[] args) {
        Thread stopNoSupportedThread = new InterruptNoSupportedThread();
        stopNoSupportedThread.start();
    }

}
