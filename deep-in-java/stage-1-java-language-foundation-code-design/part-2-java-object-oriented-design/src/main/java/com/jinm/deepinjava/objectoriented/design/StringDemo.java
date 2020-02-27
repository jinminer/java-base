package com.jinm.deepinjava.objectoriented.design;

import java.lang.reflect.Field;

public class StringDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        //常量化是 原生类型支持的赋值方式
        int a = 1;

        //常量（语法特性） ---> 对象类型常量化
        String value1 = "hello";

        //面向对象规则：一切对象都需要 new

        //合法写法（会被视作异类）
        String value2 = new String("hello");

        System.out.println(value1);
        System.out.println(value2);

        /**
         * 从 java 1.5 开始对象属性（不是类属性）可以通过反射修改
         * 所以 String 不是真正意义上的不可变类，因为从 java 1.5 以后，它的值可以通过反射被修改
         */
        char[] chars = "world".toCharArray();

        //获取 String 类中的 value 字段
        Field valueField = String.class.getDeclaredField("value");
        //设置 private 字段可以被修改
        valueField.setAccessible(true);
        //把 chars 设置到 value 字段的内容
        valueField.set(value2,chars);
        System.out.println(value2);

    }

}
