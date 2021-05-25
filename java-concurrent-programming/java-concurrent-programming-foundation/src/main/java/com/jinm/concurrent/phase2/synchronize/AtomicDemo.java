package com.jinm.concurrent.phase2.synchronize;

public class AtomicDemo {

    private int num;

    public void incr(){
        num++;
    }

    public static void main(String[] args) {
        AtomicDemo demo = new AtomicDemo();
        Thread[] threads = new Thread[2];
        for (int i = 0; i < threads.length; i ++){
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++){
                    demo.incr();
                }
            });
            threads[i].start();
        }

        try {
            threads[0].join();
            threads[1].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(demo.num);

    }

}
