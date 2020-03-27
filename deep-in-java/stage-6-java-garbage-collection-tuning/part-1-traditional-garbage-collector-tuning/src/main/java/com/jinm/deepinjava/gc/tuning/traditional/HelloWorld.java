package com.jinm.deepinjava.gc.tuning.traditional;

import java.io.IOException;
import java.lang.ref.SoftReference;

public class HelloWorld {

    public static void main(String[] args) throws IOException {

        // 强引用   message 对象是 GC root
        // 引用值 = #1
        String message = new String("hello, world");

        String msg = message;
        echo(msg);

        // 软引用: SoftReference       = 内存不够时，返回 null
        // Reference 无 root
        SoftReference<String> softReference = new SoftReference<>(message);

        echo(softReference);
        // G1 RS: Remember Set
        //  Reference
        //  Java Object

        // 弱引用: WeakReference       = Weak 可达时，返回 null
        // 虚引用: PhantomReference    = null
        // Final 引用: FinalReference

        // 引用 约等于 对象“代理”

        System.out.println("hello, world");

        System.in.read();

    }

    private static void echo(SoftReference<String> softReference) {
        // softReference == "hello, world" or null
        String referenceValue = softReference.get();

        System.out.println(referenceValue);
    }

    private static void echo(String msg) { // #1

    }

}
