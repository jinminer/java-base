package com.jinm.deepinjava.functionaldesign.functional;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class SupplierDemo {

    public static void main(String[] args) {

        Supplier<Long> supplier = getLong();
        System.out.println(supplier.get());

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getHelloWorld();
            }
        };

        Callable<String> callable2 = SupplierDemo::getHelloWorld;


    }

    public static String getHelloWorld() {
        return "Hello,World";
    }

    public static Supplier<Long> getLong() {
//        return () -> {
//            return System.currentTimeMillis();
//        };
        return System::currentTimeMillis;
    }
}
