package com.jinm.deepinjava.functionaldesign.scfp;

import java.util.function.Consumer;

public class ConsumerDemo {

    public static void main(String[] args) {

        Consumer consumer = System.out::println;

        consumer.accept("Hello1,World");

        Consumer<String> consumer2 = ConsumerDemo::echo;

        // Fluent API
        // consumer2 -> consumer -> consumer
        consumer2.andThen(consumer).andThen(consumer).accept("Hello2,月白");

    }

    public  static void print(Consumer<? super CharSequence> cs, String message ){
        cs.accept(message);
    }


    private static void echo(String message) {
        System.out.println("echo : " + message);
    }

}
