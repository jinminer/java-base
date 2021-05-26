package com.jinm.concurrent.phase3.volatiledata;

public class VolatileDemo {

    public static volatile boolean stop = false;

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            int i = 0;
            while (!stop){
                i++;
            }
        });

        thread1.start();
        System.out.println("begin start thread");

        try {
            Thread.sleep(1 ^ 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stop = true;

    }

}
