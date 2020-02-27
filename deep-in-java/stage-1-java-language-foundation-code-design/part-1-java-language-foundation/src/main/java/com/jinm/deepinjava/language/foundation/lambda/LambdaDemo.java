package com.jinm.deepinjava.language.foundation.lambda;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LambdaDemo {

    /**
     *
     * SCFP :   Suplier + Consumer + Function + Predicate
     * 四种模式（缺少 Action 模式）
     * 结论：所有的函数式接口都是一段可执行代码
     */

    // Action 模式
    private static void showAction(){

        // 传统模式
        Runnable runnable1 = new Runnable(){

            @Override
            public void run() {

            }
        };

        // lambda 模式
        Runnable runnable2 = () -> {

        };

        Runnable runnable3 = LambdaDemo::showConsumer;

    }

    // Suplier 模式：无输入，有输出
    private static void showSuplier(){

        String string = "hello, world";

        Supplier<String> string1 = () -> {
            return "hello, world";
        };

        Supplier<String> string2 = () -> "hello, world";

        Supplier<String> string3 = () -> {
            return new Integer(0).toString();
        };

        Supplier<String> string4 = () -> new Integer(0).toString();

    }

    // Consumer 模式：有输入，无输出
    private static void showConsumer(){

        // lambda 简略写法，方法引用特性，使得方法调用更加简洁
        //      PropertyChangeListener#propertyChange(PropertyChangeEvent),
        //      与println(Object)从方法签名上来讲属于同一类型：有入参、无返回值、入参只有一个，

        PropertyChangeListener listener1 = LambdaDemo ::println;

        // lambda 基本写法
        PropertyChangeListener listener2 = evt -> {
            println(evt);
        };

        // 传统写法
        // 方法调用链过长时写法较为麻烦
        PropertyChangeListener listener = new PropertyChangeListener(){

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                println(evt);
            }
        };

    }

    // Function 模式：有输入，有输出，相当于函数计算
    private static void showFunction(){

        Function<String, Integer> function = LambdaDemo::compareTo;

    }
    private static Integer compareTo(String s){
        return Integer.valueOf(s);
    }

    public static void main(String[] args) {

        stream();

        // 实例 a 变成了一段代码
        Action a = () -> {

        };

    }

    private static void println(Object object){
        System.out.println(object);
    }

    private static void stream() {

        Stream.of(1,2,3,4,5)
                .map(String::valueOf)
        ;

    }

    /**
     * @FunctionalInterface 注解可写，可不写，它是一个信息化注解，
     * 用来标注是否是一个 lambda 接口，可有可无，只要接口遵守了 lambda 语法规范即可
     */
    @FunctionalInterface
    public interface Action{

        // lambda 语法规范：有且仅有一个 未实现的抽象方法；
        //                可以定义 default 方法
        void excute();

        // 不符合 lambda 语法规范，接口只能有一个
        //void doExcute();

        // 符合 lambda 语法规范
        default void defaultExcute(){
            excute();
        }

        default void defaultExcute1(){
            excute();
        }

        /**
         *
         * 写法正确，因为 接口的父类是 Object,可以对 父类方法进行重写，
         * 具体的语义可以看做是：
         *  default int hashCode(){
         *      super.hashCode();
         *  }
         *
         */
        @Override
        int hashCode();

    }


}
