package com.jinm.deepinjava.gc.tuning.cenozoic;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceDemo {

    public static void main(String[] args) throws InterruptedException {

        // SoftReference -> WeakReference -> FinalReference -> PhantomReference

        /**
         * value -> new Integer(9527) 强引用
         *
         */
        Integer value = new Integer(9527);
        ReferenceQueue<Integer> referenceQueue = new ReferenceQueue<>();


        /**
         * 弱引用，gc 后发现 该对象未被引用，
         * 所以会进入 弱引用队列，referenceQueue.poll() 不为 null
         * 运行结果：
         *  Reference 对象 GC 前的被引用对象：9527
         *  ReferenceQueue 对象 GC 前的入队对象：null
         *  Reference 对象 GC 后的被引用对象：null
         *  ReferenceQueue 对象 GC 后的入队对象：java.lang.ref.WeakReference@154617c
         * 弱可达不一定存在
         */
        Reference<Integer> reference = new WeakReference<Integer>(new Integer(9527), referenceQueue);

        /**
         * 强引用，gc 后发现 该对象仍然被引用（value -> new Integer(9527)），
         * 所以不会进入 弱引用队列，referenceQueue.poll() 为 null
         *  运行结果：
         *   Reference 对象 GC 前的被引用对象：9527
         *   ReferenceQueue 对象 GC 前的入队对象：null
         *   Reference 对象 GC 后的被引用对象：9527
         *   ReferenceQueue 对象 GC 后的入队对象：null
         *
         */
        //Reference<Integer> reference = new WeakReference<Integer>(value, referenceQueue);

        System.out.println("Reference 对象 GC 前的被引用对象：" + reference.get());
        System.out.println("ReferenceQueue 对象 GC 前的入队对象：" + referenceQueue.poll());

        System.gc();
        Thread.sleep(10000L);

        System.out.println("Reference 对象 GC 后的被引用对象：" + reference.get());
        System.out.println("ReferenceQueue 对象 GC 后的入队对象：" + referenceQueue.poll().get());

    }

}
