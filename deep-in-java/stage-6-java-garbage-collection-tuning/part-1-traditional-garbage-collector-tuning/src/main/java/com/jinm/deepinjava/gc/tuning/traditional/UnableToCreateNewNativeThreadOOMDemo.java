package com.jinm.deepinjava.gc.tuning.traditional;

public class UnableToCreateNewNativeThreadOOMDemo {

    public static void main(String[] args) {

        /**
         *
         * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
         * 	at java.lang.Thread.start0(Native Method)
         * 	at java.lang.Thread.start(Thread.java:714)
         * 	at com.jinm.deepinjava.gc.tuning.traditional.UnableToCreateNewNativeThreadOOMDemo.main(UnableToCreateNewNativeThreadOOMDemo.java:22)
         */
        int size = 10_1000;
        Thread[] threads = new Thread[size];

        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(()-> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hello,world");
            });
        }

        for (int i = 0; i < size; i++) {
            threads[i].start();
        }

    }

}
