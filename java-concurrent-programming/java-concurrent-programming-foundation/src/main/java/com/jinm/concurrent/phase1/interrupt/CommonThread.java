package com.jinm.concurrent.phase1.interrupt;

public class CommonThread extends Thread{

    @Override
    public void run() {
        System.out.println("CommonThread.run()");
    }

    public static void main(String[] args) {

        Thread commonThread = new CommonThread();
        commonThread.start();

    }
}
