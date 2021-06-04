package com.jinm.concurrent.phase6.aqs.threadsignal.juc;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable{

    private Queue<String> boxes;

    private int capacity;

    private Lock lock;

    private Condition condition;

    public Consumer(Queue<String> boxes, int capacity, Lock lock, Condition condition) {
        this.boxes = boxes;
        this.capacity = capacity;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        while (true){

            lock.lock();
            if (boxes.isEmpty()){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费者消费：" + boxes.remove());
            condition.signal();

        }

    }
}
