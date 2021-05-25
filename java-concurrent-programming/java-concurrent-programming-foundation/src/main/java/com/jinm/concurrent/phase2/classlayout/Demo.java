package com.jinm.concurrent.phase2.classlayout;

import org.openjdk.jol.info.ClassLayout;

public class Demo {
    Object o=new Object();
    public static void main(String[] args) {
        Demo demo=new Demo();
        //demo这个对象实例，在内存中是如何存储和布局的。
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
    }
}

