package com.jinm.deepinjava.functionaldesign.scfp;

public class ActionDemo {

    public static void main(String[] args) {

        // 匿名内置类
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("月白");
            }
        };

        // invokedynamic 指令 @since JDK 1.7
        // 请记住 java.lang.invoke.MethodHandle
        // java.lang.invoke.InvokeDynamic
        Runnable runnable2 = () -> {

        };
    }
}
