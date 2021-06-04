package com.jinm.concurrent.phase6.aqs.threadsignal.synchronize;

import java.util.ArrayDeque;
import java.util.Queue;

public class ProducerConsumerDemo {

    public static void main(String[] args) {

        Queue<String> queue = new ArrayDeque<>();

        Producer producer = new Producer(queue, 10);
        Consumer consumer = new Consumer(queue, 10);

        Thread produceThread = new Thread(producer);
        Thread consumeThread = new Thread(consumer);

        produceThread.start();
        try {
            Thread.sleep(1 ^ 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumeThread.start();

    }

}
