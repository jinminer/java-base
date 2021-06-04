package com.jinm.concurrent.phase6.aqs.threadsignal.synchronize;

import java.util.Queue;

public class Producer implements Runnable{

    private Queue<String> boxes;
    private int capacity;

    public Producer(Queue<String> boxes, int capacity) {

        this.boxes = boxes;
        this.capacity = capacity;

    }

    @Override
    public void run() {
        int num = 0;
        while (true){
            num ++;
            synchronized (boxes){

                if (boxes.size() == this.capacity){

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

                System.out.println("生产者生产：box"+num);
                boxes.add("box"+num); //生产bag

                boxes.notify();

            }
        }

    }
}
