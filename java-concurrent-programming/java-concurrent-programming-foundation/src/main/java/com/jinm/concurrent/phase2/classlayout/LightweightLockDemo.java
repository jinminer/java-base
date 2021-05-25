package com.jinm.concurrent.phase2.classlayout;

import org.openjdk.jol.info.ClassLayout;

public class LightweightLockDemo {
    Object o=new Object();
    public static void main(String[] args) {
        LightweightLockDemo demo=new LightweightLockDemo();
        //o这个对象，在内存中是如何存储和布局的。
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        synchronized (demo){
            System.out.println(ClassLayout.parseInstance(demo).toPrintable());
        }
    }
}

