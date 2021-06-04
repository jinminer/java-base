package com.jinm.concurrent.phase6.aqs.threadsignal.synchronize;

import java.util.Queue;
import java.util.concurrent.locks.Condition;

public class Consumer implements Runnable{

    private Queue<String> boxes;
    private int capacity;

    public Consumer(Queue<String> boxes, int capacity) {

        this.boxes = boxes;
        this.capacity = capacity;

    }

    @Override
    public void run() {

        while (true){

            synchronized (boxes){

                if (boxes.isEmpty()){

                    try {
                        boxes.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                try {
                    Thread.sleep(1 ^ 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //消费box
                System.out.println("消费者消费："+boxes.remove());

                boxes.notify();

            }
        }

    }
}
