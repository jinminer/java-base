package com.jinm.concurrent.phase2.classlayout;

import org.openjdk.jol.info.ClassLayout;

public class HeavyweightLockDemo {

    public static void main(String[] args) {

        HeavyweightLockDemo demo = new HeavyweightLockDemo();

        Thread thread1 = new Thread(() -> {
            synchronized (demo){
                System.out.println("thread1 locking .....");
                System.out.println(ClassLayout.parseInstance(demo).toPrintable());
            }
        });

        thread1.start();

        synchronized (demo){
            System.out.println("main locking .....");
            System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        }

    }

}
