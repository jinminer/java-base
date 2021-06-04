package com.jinm.concurrent.phase6.aqs.threadsignal.juc;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerDemo {

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Queue<String> queue = new ArrayDeque<>();

        Producer producer = new Producer(queue, 10, lock, condition);
        Consumer consumer = new Consumer(queue, 10, lock, condition);

        new Thread(producer).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(consumer).start();

    }

}
