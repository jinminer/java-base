package com.jinm.concurrent.phase6.aqs.threadsignal.juc;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable{

    private Queue<String> boxes;

    private int capacity;

    private Lock lock;

    private Condition condition;

    public Producer(Queue<String> boxes, int capacity, Lock lock, Condition condition) {
        this.boxes = boxes;
        this.capacity = capacity;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        int num = 0;
        while (true){

            lock.lock();
            num++;

            if (boxes.size() == capacity){
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

            System.out.println("生产者生产：box - "+num);
            boxes.add("box - " + num);

            condition.signal();

            /**
             * 手动释放锁：
             *      1.如果不进行手动释放锁，只能等生产者循环执行业务逻辑，在满足boxes.size() == capacity条件时才释放锁，即 接连生产10个，
             *              才调用lock.unlock();逻辑释放锁，在此动作之前，因为生产者并未释放锁，所以被唤醒的消费者会由等待队列进入阻塞队列等待生产者释放锁
             */
            lock.unlock();

        }

    }
}
